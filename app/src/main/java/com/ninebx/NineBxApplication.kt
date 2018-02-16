package com.ninebx

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.evernote.android.job.JobManager
import com.ninebx.ui.base.network.NetModule
import com.ninebx.ui.base.realm.Users
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.home.account.interfaces.IMemoryAdded
import com.ninebx.utility.FragmentOrganiser
import com.ninebx.utility.NineBxJobCreator
import com.ninebx.utility.NineBxPreferences
import com.ninebx.utility.Preferences
import io.realm.Realm
import io.realm.RealmConfiguration
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import java.security.SecureRandom


/***
 * Created by TechnoBlogger on 18/12/17.
 */
class NineBxApplication : MultiDexApplication() {

    fun getApplication(): NineBxApplication {
        return this
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

    }

    var activityInstance: HomeActivity? = null
        private set

    private var fragmentOrganiser: FragmentOrganiser? = null

    private var iMemoryAdded: IMemoryAdded? = null

    val fragmentOpener: FragmentOrganiser
        get() {
            if (fragmentOrganiser == null)
                fragmentOrganiser = FragmentOrganiser(this.activityInstance!!, R.id.frameLayout)
            return fragmentOrganiser!!
        }


    companion object {

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

        private var getUserAPI: NetModule.GetUsersAPI? = null
        fun getUserAPI(): NetModule.GetUsersAPI? {
            if (getUserAPI == null) getUserAPI = NetModule(instance).retrofit.create(NetModule.GetUsersAPI::class.java)
            return getUserAPI
        }

        lateinit var instance: NineBxApplication
            private set
    }

    fun init(_homeActivity: HomeActivity) {
        this.activityInstance = _homeActivity
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


    fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    var currentUser: Users? = null

    fun getiMemoryAdded(): IMemoryAdded? {
        return this.iMemoryAdded
    }

    fun setiMemoryAdded(iMemberAdded: IMemoryAdded) {
        this.iMemoryAdded = iMemoryAdded
    }
}