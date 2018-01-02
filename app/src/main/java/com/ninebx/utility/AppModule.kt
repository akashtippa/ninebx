package com.ninebx.utility

import com.ninebx.NineBx
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by TechnoBlogger on 18/12/17.
 */
@Module
class AppModule( val mApplication: NineBx) {

    @Provides
    @Singleton
    fun providesApplication() = mApplication
}