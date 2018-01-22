package com.ninebx

import android.annotation.SuppressLint
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.evernote.android.job.JobManager
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.account.interfaces.IMemberAdded
import com.ninebx.utility.FragmentOrganiser
import com.ninebx.utility.NineBxJobCreator
import com.ninebx.utility.NineBxPreferences
import com.ninebx.utility.Preferences
import io.realm.Realm
import io.realm.RealmConfiguration
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import java.security.SecureRandom
import com.amazonaws.regions.Regions
import com.amazonaws.auth.CognitoCachingCredentialsProvider




/***
 * Created by TechnoBlogger on 18/12/17.
 */
class NineBxApplication : MultiDexApplication() {

    var activityInstance: HomeActivity? = null
        private set

    private var fragmentOrganiser: FragmentOrganiser? = null

    private var iMemberAdded: IMemberAdded? = null

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
        JobManager.create(this).addJobCreator(NineBxJobCreator())
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

        val credentialsProvider = CognitoCachingCredentialsProvider(
                this, /* get the context for the application */
                "", /* Identity Pool ID */
                Regions.US_WEST_2           /* Region for your identity pool--US_EAST_1 or EU_WEST_1*/
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


    fun getiMemberAdded(): IMemberAdded? {
        return this!!.iMemberAdded
    }

    fun setiMemberAdded(iMemberAdded: IMemberAdded) {
        this.iMemberAdded = iMemberAdded
    }
}