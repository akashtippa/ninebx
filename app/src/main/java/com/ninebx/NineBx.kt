package com.ninebx

import android.app.Application
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmConfiguration

/***
 * Created by TechnoBlogger on 18/12/17.
 */
class NineBx : Application() {

    companion object {
        lateinit var instance : NineBx
        lateinit var mNetComponent: NetComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Preferences.init(applicationContext)
        mNetComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this@NineBx))
                .netModule(NetModule("http://18.221.67.220:9080/", this@NineBx))
                .build()
        NineBx.mNetComponent.inject(this@NineBx)

        Realm.init(this);
    }

}