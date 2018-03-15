package com.ninebx.utility

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.AsyncTask
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
        if( params.extras.containsKey("reminder") ) {
            val extraModel = params.extras["reminder"] as String
            val reminder = gson.fromJson(extraModel, CalendarEvents::class.java)
            val title: String
            val desc: String

            title = "This is a reminder notification"
            //title = "Reminder : ${reminder.title[0]!!}"
            if( reminder.reminder.size > 0 )
                desc = reminder.title[0]!! + " event is scheduled"
            else {
                // desc = reminder.title[0]!!
                desc = reminder.title[0]!! + " event is scheduled"
            }

            AppLogger.d(TAG, "Notification : " + title + " : " + desc)
            showNotification( title, desc )

        }
        else if( params.extras.containsKey("notification") ) {

            val extraNotification = params.extras["notification"] as String
            val notify = gson.fromJson(extraNotification, Notifications::class.java)
            val notificationTitle : String
            val notificationDesc : String

            notificationTitle = "NineBx : ${notify.subTitle[0]}"
            notificationDesc =notify.message
            showNotification(notificationTitle, notificationDesc)
        }


        return Result.SUCCESS
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification(title: String, desc: String?) {
        AppLogger.d(TAG, "showNotification : " + title + " : " + desc)
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
            val mChannel = NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_HIGH)
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
                    .setPriority(NotificationManager.IMPORTANCE_HIGH)
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
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .setWhen(0)
                        .setSound(defaultSoundUri)
            } else {
                    notificationBuilder = NotificationCompat.Builder(context,channelId)
                        .setAutoCancel(true)   //Automatically delete the notification
                        .setSmallIcon(R.mipmap.ic_launcher) //NotificationModel icon
                        .setContentIntent(pendingIntent)
                        .setContentTitle(title)
                        .setContentText(desc)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .setWhen(0)
                        .setSound(defaultSoundUri)
            }
           notificationManager.notify(channelId.hashCode(), notificationBuilder.build())
    }

    companion object {

        val TAG = AlarmJob::class.java.simpleName
        @SuppressLint("StaticFieldLeak")
        fun scheduleJob( reminder: CalendarEvents, calendar: Calendar ) {
            AppLogger.d(TAG, "scheduleJob : " + reminder.toString())
            val gson = Gson()
            val reminderString = gson.toJson(reminder)
            AppLogger.d(TAG, "scheduleJob : Json : " + reminderString)
            val extras = PersistableBundleCompat()
            extras.putString("reminder", reminderString)


            object: AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    scheduleRecurringEvent(reminder, calendar, extras)
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)

                }

            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

        }

        private fun scheduleRecurringEvent(events: CalendarEvents, calendar: Calendar, extras: PersistableBundleCompat) {


            for( i in 0..events.startsDate.size -1) {

                if (!events.isAllDay[i]!!) {
                    //find the reminder
                    when( events.reminder[i] )  {
                        "At time of event" -> {
                            calendar.timeInMillis = calendar.timeInMillis
                        }
                        "5 minutes before" -> {
                            calendar.timeInMillis = calendar.timeInMillis - (5*60*1000)
                        }
                        "15 minutes before" -> {
                            calendar.timeInMillis = calendar.timeInMillis - (15*60*1000)
                        }
                        "30 minutes before" -> {
                            calendar.timeInMillis = calendar.timeInMillis - (30*60*1000)
                        }
                        "1 hour before" -> {
                            calendar.timeInMillis = calendar.timeInMillis - (60*60*1000)
                        }
                        "2 hour before" -> {
                            calendar.timeInMillis = calendar.timeInMillis - (120*60*1000)
                        }
                        "1 day before" -> {
                            calendar.timeInMillis = calendar.timeInMillis - (24*60*60*1000)
                        }
                        "2 day before" -> {
                            calendar.timeInMillis = calendar.timeInMillis - (48*60*60*1000)
                        }
                        "1 week before" -> {
                            calendar.timeInMillis = calendar.timeInMillis - (7*24*60*60*1000)
                        }
                        /*dateToFire = sDate
                        case Repeaters.k5MinutesBefore :
                        dateToFire =  Calendar.current.date(byAdding: .minute, value: -5, to: sDate)!
                        case Repeaters.k15MinutesBefore :
                        dateToFire =  Calendar.current.date(byAdding: .minute, value: -15, to: sDate)!
                        case Repeaters.k30MinutesBefore :
                        dateToFire =  Calendar.current.date(byAdding: .minute, value: -30, to: sDate)!
                        case Repeaters.k1HourBefore :
                        dateToFire =  Calendar.current.date(byAdding: .hour, value: -1, to: sDate)!
                        case Repeaters.k2HourBefore:
                        dateToFire =  Calendar.current.date(byAdding: .hour, value: -2, to: sDate)!
                        case Repeaters.k1DayBefore :
                        dateToFire =  Calendar.current.date(byAdding: .day, value: -1, to: sDate)!
                        case Repeaters.k2DayBefore :
                        dateToFire =  Calendar.current.date(byAdding: .day, value: -2, to: sDate)!
                        case Repeaters.k1WeekBefore :
                        dateToFire =  Calendar.current.date(byAdding: .day, value: -7, to: sDate)!
                        default:
                        print("")*/
                    }

                } else {
                    when( events.reminder[i]) {
                        "On day of event(9:00 AM)" -> {
                            calendar.timeInMillis = calendar.timeInMillis
                        }
                        "One day before(9:00 AM)" -> {
                            calendar.timeInMillis = calendar.timeInMillis - (24*60*60*1000)
                        }
                        "Two days before(9:00 AM)" -> {
                            calendar.timeInMillis = calendar.timeInMillis - (48*60*60*1000)
                        }
                        "1 week before" -> {
                            calendar.timeInMillis = calendar.timeInMillis - (7*24*60*60*1000)
                        }
                    }

                    /*switch calendarModel.reminder[i]  {
                        case "On day of event (9:00 AM)" :
                        dateToFire = sDate //Calendar.current.date(byAdding: .hour, value: 9, to: )!
                        case Repeaters.kOneDayBefore :
                        dateToFire = Calendar.current.date(byAdding: .day, value: -1, to: sDate)!
                        case Repeaters.kTwoDayBefore :
                        dateToFire = Calendar.current.date(byAdding: .day, value: -2, to: sDate)!
                        case Repeaters.k1WeekBeforeForAllDay :
                        dateToFire = Calendar.current.date(byAdding: .day, value: -7, to: sDate)!
                        default:
                        print("")
                    }*/
                }

                val reminderTimeInMillis: Long = calendar.timeInMillis
                val id: String = "Calendar_Event_" + events.id + "_" + calendar.timeInMillis

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
