package com.example.taskmanager.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.taskmanager.R

/**
 * <h1>AppSharedPreference</h1>
 * <p>
 *
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
class AppSharedPreference (context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)


    /*keys*/

    private val EMAIL = "email"
    fun getEmail(): String = prefs.getString(EMAIL, "").toString()
    fun setEmail(value: String) = prefs.edit().putString(EMAIL, value).apply()

    private val IS_LOGGED_IN = "is_logged_in"
    fun getIsLoggedIn(): Boolean = prefs.getBoolean(IS_LOGGED_IN, false)
    fun setIsLoggedIn(value: Boolean) = prefs.edit().putBoolean(IS_LOGGED_IN, value).apply()


    private val USER_NAME = "user_name"
    fun getUserName(): String = prefs.getString(USER_NAME, "").toString()
    fun setUserName(value: String) = prefs.edit().putString(USER_NAME, value).apply()

}