package com.example.taskmanager.view.activity

import android.os.Bundle
import android.os.Handler
import com.example.taskmanager.R
import com.example.taskmanager.data.local.AppSharedPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * <h1>MainActivity</h1>
 * <p>
 *
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
@AndroidEntryPoint
class SplashScreenActivity : BaseActivity() {

    lateinit var handler: Handler
    @Inject
    lateinit var appSharedPreference : AppSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        /**
         * delay for 1 second
         * depending on the sign in status navigate to different activity
         * */
        handler = Handler()
        handler.postDelayed({
            if (appSharedPreference.getIsLoggedIn()) {
                navigateToMainActivity()
            } else {
                navigateToLoginActivity()
            }
            finish()
        }, 1000)
    }
}