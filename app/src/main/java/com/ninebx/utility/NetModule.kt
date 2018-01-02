package com.ninebx.utility

import android.support.constraint.solver.Cache
import com.ninebx.NineBx
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by TechnoBlogger on 18/12/17.
 */

@Module
class NetModule(baseUrl: String, application: NineBx) {


    val baseUrl = baseUrl
    val application = application


    // Dagger will only look for methods annotated with @Provides


    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    fun providesSharedPreferences(): NineBxPreferences {
        // Application reference must come from AppModule.class
        return NineBxPreferences()
    }


}
