package com.example.taskmanager

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.taskmanager.utils.CommonHelper
import dagger.hilt.android.HiltAndroidApp

/**
 * <h1>App</h1>
 * <p>
 *
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
@HiltAndroidApp
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        CommonHelper.initLogger()
    }
}