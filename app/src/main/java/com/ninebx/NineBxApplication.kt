package com.ninebx

import android.annotation.SuppressLint
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.ninebx.ui.home.HomeActivity
import com.ninebx.utility.*
import io.realm.Realm
import io.realm.RealmConfiguration
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import java.security.SecureRandom


/***
 * Created by TechnoBlogger on 18/12/17.
 */
class NineBxApplication : MultiDexApplication() {

    var activityInstance: HomeActivity? = null
        private set

    private var fragmentOrganiser: FragmentOrganiser? = null

    val fragmentOpener: FragmentOrganiser
        get() {
            if (fragmentOrganiser == null)
                fragmentOrganiser = FragmentOrganiser(this.activityInstance!!, R.id.frameLayout)
            return fragmentOrganiser!!
        }


    companion object {
        lateinit var instance: NineBxApplication

        @SuppressLint("StaticFieldLeak")
        var realmDefaultInstance: Realm? = null

        fun getRealmInstance(): Realm {
            if (realmDefaultInstance == null) {
                realmDefaultInstance = Realm.getDefaultInstance()
            }
            return realmDefaultInstance!!
        }

        @SuppressLint("StaticFieldLeak")
        var nineBxPreferences: NineBxPreferences? = null

        fun getPreferences(): NineBxPreferences {
            if (nineBxPreferences == null)
                nineBxPreferences = NineBxPreferences()
            return nineBxPreferences!!
        }

        var autoTestMode = true
    }

    fun init(_homeActivity: HomeActivity) {
        this.activityInstance = _homeActivity
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        instance = this
        Preferences.init(applicationContext)
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("com.ninebx.realm")/*.encryptionKey(getKey())*/.build()
        Realm.setDefaultConfiguration(config)

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AvenirNextLTPro-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

    fun getKey(): ByteArray {
        val key = ByteArray(64)
        SecureRandom().nextBytes(key)
        return key
    }

    override fun onTerminate() {
        super.onTerminate()
        realmDefaultInstance!!.close()
        activityInstance = null
        fragmentOrganiser = null
    }
}