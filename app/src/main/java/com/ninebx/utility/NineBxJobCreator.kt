package com.ninebx.utility

import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator

/**
 * Created by Alok on 17/01/18.
 */
class NineBxJobCreator : JobCreator {

    override fun create(tag: String): Job? {
        when( tag ) {
            AlarmJob.TAG -> {
                return AlarmJob()
            }
            else -> {
                if( tag.startsWith("Calendar_Event") ) {
                    return AlarmJob()
                }
                return null
            }
        }
    }
}