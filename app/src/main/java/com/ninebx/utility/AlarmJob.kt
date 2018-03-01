package com.ninebx.utility

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import com.evernote.android.job.Job
import com.evernote.android.job.JobManager
import com.evernote.android.job.JobRequest
import com.evernote.android.job.util.support.PersistableBundleCompat
import com.google.gson.Gson
import com.ninebx.R
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.base.realm.CalendarEvents
import com.ninebx.ui.base.realm.Notifications
import java.util.*

/**
 * Created by Alok on 17/01/18.
 */
class AlarmJob : Job() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRunJob(params: Params): Result {
        val gson = Gson()
        val extraModel = params.extras["reminder"] as String
        val reminder = gson.fromJson(extraModel, CalendarEvents::class.java)
        val title: String
        val desc: String

        val extraNotification = params.extras["notification"] as String
        val notify = gson.fromJson(extraNotification, Notifications::class.java)
        val notificationTitle : String
        val notificationDesc : String

           /*if (reminder.action == Reminders.ACTION_PREGAME || reminder.action == Reminders.ACTION_POSTGAME) {
            val testDateTime = getGameDateTime(reminder.date + " , " + reminder.time)
            title = "Reminder : You have an event - ${reminder.title}"
            desc = "${testDateTime.toString("dd MMM ")} at ${testDateTime.toString("hh:mm a")}"
            if (!reminder.endsDate.time < )
                updateReminderForGame(reminder)
            pendingIntent = PendingIntent.getActivity(context, 0, Intent(context, HomeActivity::class.java), PendingIntent.FLAG_CANCEL_CURRENT)
        } else {
            title = "Reminder : ${reminder.title}"
            desc = reminder.reminder
            pendingIntent = PendingIntent.getActivity(context, 0, Intent(context, HomeActivity::class.java), PendingIntent.FLAG_CANCEL_CURRENT)
        }*/
        title = "Reminder : ${reminder.title[0]!!}"
        if( reminder.reminder.size > 0 )
            desc = reminder.reminder[0]!!
        else desc = reminder.title[0]!!
        
        showNotification( title, desc )

        notificationTitle = "NineBx : ${notify.subTitle[0]}"
        notificationDesc =notify.message
        showNotification(notificationTitle, notificationDesc)

        return Result.SUCCESS
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification(title: String, desc: String?) {
        val pendingIntent = PendingIntent.getActivity(context, 0, Intent(context, HomeActivity::class.java), PendingIntent.FLAG_CANCEL_CURRENT)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder: NotificationCompat.Builder
        val channelId = "Reminders"
        if( Build.VERSION.SDK_INT == Build.VERSION_CODES.O ) {
            // The id of the channel.

            // The user-visible name of the channel.
            val name = title
            // The user-visible description of the channel.
            val description = desc
            val mChannel = NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_DEFAULT)
            // Configure the notification channel.
            mChannel.description = description
            mChannel.enableLights(true)
            // Sets the notification light color for notifications posted to this
            // channel, if the device supports this feature.
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager.createNotificationChannel(mChannel)

            notificationBuilder = NotificationCompat.Builder(context, channelId)
                    .setAutoCancel(true)   //Automatically delete the notification
                    .setSmallIcon(R.mipmap.ic_launcher) //NotificationModel icon
                    .setContentIntent(pendingIntent)
                    .setContentTitle(title)
                    .setContentText(desc)
                    .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
                    .setChannelId(channelId)
                    .setWhen(0)
                    .setSound(defaultSoundUri)

        }
        else
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                notificationBuilder = NotificationCompat.Builder(context, channelId)
                        .setAutoCancel(true)   //Automatically delete the notification
                        .setSmallIcon(R.mipmap.ic_launcher) //NotificationModel icon
                        .setContentIntent(pendingIntent)
                        .setContentTitle(title)
                        .setContentText(desc)
                        .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
                        .setWhen(0)
                        .setSound(defaultSoundUri)
            } else {
                notificationBuilder = NotificationCompat.Builder(context,channelId)
                        .setAutoCancel(true)   //Automatically delete the notification
                        .setSmallIcon(R.mipmap.ic_launcher) //NotificationModel icon
                        .setContentIntent(pendingIntent)
                        .setContentTitle(title)
                        .setContentText(desc)
                        .setPriority(android.app.Notification.PRIORITY_HIGH)
                        .setWhen(0)
                        .setSound(defaultSoundUri)
            }
           notificationManager.notify(channelId.hashCode(), notificationBuilder.build())
    }

    companion object {

        val TAG = AlarmJob::class.java.simpleName

        fun scheduleJob( reminder: CalendarEvents, calendar: Calendar ) {
            AppLogger.d(TAG, "scheduleJob : " + reminder.toString())
            val gson = Gson()
            val reminderString = gson.toJson(reminder)
            AppLogger.d(TAG, "scheduleJob : Json : " + reminderString)
            val extras = PersistableBundleCompat()
            extras.putString("reminder", reminderString)
            val reminderTimeInMillis: Long = calendar.timeInMillis
            val id: String = "Calendar_Event_" + reminder.id

            var jobId: Int = -1
            val currentCalendar = Calendar.getInstance()
            if (reminderTimeInMillis > currentCalendar.timeInMillis ) {
                /*if (repeatDaily)
                    jobId = JobRequest.Builder(id)
                            .setPeriodic(TimeUnit.DAYS.toDays(1))
                            //.setExact(reminderTimeInMillis)
                            .setExecutionWindow(reminderTimeInMillis - currentCalendar.timeInMillis, (reminderTimeInMillis + 300) - currentCalendar.timeInMillis)
                            .setRequiresDeviceIdle(false)
                            .setExtras(extras)
                            .build()
                            .schedule()
                else*/
                    jobId = JobRequest.Builder(id)
                            .setExact((reminderTimeInMillis - currentCalendar.timeInMillis))
                            //.setExecutionWindow(reminderTimeInMillis - currentCalendar.timeInMillis, (reminderTimeInMillis + 300) - currentCalendar.timeInMillis)
                            .setRequiresDeviceIdle(false)
                            .setExtras(extras)
                            .build()
                            .schedule()
            }
        }

        fun cancelJob(remindJobId: String) {
            JobManager.instance().cancelAllForTag(remindJobId)
        }

        fun scheduleNotificaiton(notification : Notifications , calendar: Calendar ){
            AppLogger.d("NotificationScheduled", "scheduleNotification : " + notification.toString())
            val gson = Gson()
            val notificationString = gson.toJson(notification)
            AppLogger.d("NotificationScheduled", "scheduleNotification : JSON" + notificationString)
            var extras = PersistableBundleCompat()
            extras.putString("notification", notificationString)
            val reminderTimeInMillis: Long = calendar.timeInMillis
            val id: String = "Notification_" + notification.id
            val calendar = Calendar.getInstance()
            var jobId: Int = -1
            if (reminderTimeInMillis > calendar.timeInMillis ){
                jobId = JobRequest.Builder(id)
                        .setExact((reminderTimeInMillis - calendar.timeInMillis))
                        .setRequiresDeviceIdle(false)
                        .setExtras(extras)
                        .build()
                        .schedule()
            }
            }
        }
    }
