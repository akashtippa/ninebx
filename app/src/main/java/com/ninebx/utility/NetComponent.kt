package com.ninebx.utility

import com.ninebx.NineBx
import dagger.Component
import javax.inject.Singleton

/**
 * Created by TechnoBlogger on 18/12/17.
 */
@Singleton
@Component(modules = [(AppModule::class), (NetModule::class)])
interface NetComponent {
    fun inject(app: NineBx)
    fun preferenceManager() : NineBxPreferences
}
