package com.ninebx

import android.annotation.SuppressLint
import android.app.Application
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmConfiguration
import java.security.SecureRandom


/***
 * Created by TechnoBlogger on 18/12/17.
 */
class NineBxApplication : Application() {

    companion object {
        lateinit var instance : NineBxApplication
        @SuppressLint("StaticFieldLeak")
        var realmDefaultInstance : Realm? = null
        fun getRealmInstance() : Realm {
            if( realmDefaultInstance == null ) {
                realmDefaultInstance = Realm.getDefaultInstance()
            }
            return realmDefaultInstance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Preferences.init(applicationContext)
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("com.ninebx.realm")/*.encryptionKey(getKey())*/.build()
        Realm.setDefaultConfiguration(config)
    }

    fun getKey(): ByteArray {
        val key = ByteArray(64)
        SecureRandom().nextBytes(key)
        return key
    }

    override fun onTerminate() {
        super.onTerminate()
        realmDefaultInstance!!.close()
    }
}