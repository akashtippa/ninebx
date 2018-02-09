package com.ninebx.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ninebx.R
import com.ninebx.ui.auth.AuthActivity
import com.ninebx.ui.home.HomeActivity
import com.ninebx.ui.tutorial.activity.WalkThroughActivity
import com.ninebx.utility.NineBxPreferences
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    val disposables: CompositeDisposable = CompositeDisposable()
    val prefrences = NineBxPreferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /**
         * Checking if the user is opening for the first time.
         *
         * If FIRST_TIME, then goto WalkThroughActivity.class
         *
         * @WalkThroughActivity
         * In WalkThroughActivity Screen, if GetStarted Button is clicked, then take user to SignInActivity.class
         *
         * @SignInActivity, if user is logged in then goto HomeActivity Screen.
         */

        val disposable = Flowable.interval(2000L, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                    when {
                        !prefrences.firstRun -> {
                            val intent = Intent(this@SplashActivity, WalkThroughActivity::class.java)
                            startActivity(intent)
                            disposables.clear()
                            finish()
                        }

                        !prefrences.isLogin -> {
                            val homeIntent = Intent(this@SplashActivity, AuthActivity::class.java)
//                            val homeIntent = Intent(this@SplashActivity, HomeActivity::class.java)
                            startActivity(homeIntent)
                            disposables.clear()
                            finish()
                        }

                        else -> {
                            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                            startActivity(intent)
                            disposables.clear()
                            finish()
                        }
                    }
                }
        disposables.add(disposable)
    }
}
