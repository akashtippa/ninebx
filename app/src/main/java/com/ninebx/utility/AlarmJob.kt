package com.ninebx.utility

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.evernote.android.job.Job
import com.evernote.android.job.JobManager
import com.evernote.android.job.JobRequest
import com.evernote.android.job.util.support.PersistableBundleCompat
import com.google.gson.Gson
import com.ninebx.R
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.calendar.model.CalendarEvents
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Alok on 17/01/18.
 */
class AlarmJob : Job() {

    override fun onRunJob(params: Params): Result {
        val gson = Gson()
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val extraModel = params.extras["reminder"] as String
        val reminder = gson.fromJson(extraModel, CalendarEvents::class.java)
        val title: String
        val desc: String


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
        title = "Reminder : ${reminder.title}"
        desc = reminder.reminder
        val pendingIntent = PendingIntent.getActivity(context, 0, Intent(context, HomeActivity::class.java), PendingIntent.FLAG_CANCEL_CURRENT)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NotificationCompat.Builder(context, "Reminders")
                    .setContentTitle(title)
                    .setContentText(desc)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
                    .setSound(defaultSoundUri)
                    .build()
        } else {
            NotificationCompat.Builder(context, "Reminders")
                    .setContentTitle(title)
                    .setContentText(desc)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
                    .setSound(defaultSoundUri)
                    .build()
        }
        notificationManager.notify(1, notification)
        return Result.SUCCESS
    }


    companion object {

        val TAG = AlarmJob::class.java.simpleName

        fun scheduleJob(reminder: CalendarEvents, calendar: Calendar, repeatDaily: Boolean) {

            val gson = Gson()
            val reminderString = gson.toJson(reminder)
            val extras = PersistableBundleCompat()
            extras.putString("reminder", reminderString)
            val reminderTimeInMillis: Long = calendar.timeInMillis
            val id: String = "Calendar_Event_" + reminder.id

            var jobId: Int = -1
            val currentCalendar = Calendar.getInstance()
            if (reminderTimeInMillis > currentCalendar.timeInMillis )
                if (repeatDaily)
                    jobId = JobRequest.Builder(id)
                            .setPeriodic(TimeUnit.DAYS.toDays(1))
                            .setExecutionWindow(reminderTimeInMillis - currentCalendar.timeInMillis, (reminderTimeInMillis + 300) - currentCalendar.timeInMillis)
                            .setRequiresDeviceIdle(false)
                            .setExtras(extras)
                            .build()
                            .schedule()
                else
                    jobId = JobRequest.Builder(id)
                            .setExecutionWindow(reminderTimeInMillis - currentCalendar.timeInMillis, (reminderTimeInMillis + 300) - currentCalendar.timeInMillis)
                            .setRequiresDeviceIdle(false)
                            .setExtras(extras)
                            .build()
                            .schedule()


        }

        fun cancelJob(remindJobId: String) {
            JobManager.instance().cancelAllForTag(remindJobId)
        }


    }


}