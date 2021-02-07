package com.example.taskmanager.utils

import android.util.Base64
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import com.example.taskmanager.BuildConfig
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.logging.Level
import java.util.logging.Logger
import java.util.regex.Matcher
import java.util.regex.Pattern




/**
 * <h1>CommonHelper</h1>
 * <p>
 *  All common function are kept here
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
object CommonHelper {

    val TAG = "CommonHelper"

    val VALID_EMAIL_ADDRESS_REGEX: Pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
    val PASSWORD_PATTERN = Pattern.compile(
            "^" +  //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +  //any letter
                    //"(?=.*[@#$%^&+=])" +  //at least 1 special character
                    "(?=\\S+$)" +  //no white spaces
                    ".{6,}" +  //at least 4 characters
                    "$"
    )

    fun isValidEmail(emailStr: String): Boolean {
        val matcher: Matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr)
        return matcher.find()
    }


    fun hashedPassword(password: String): String {
        var md: MessageDigest? = null
        return try {
            md = MessageDigest.getInstance("SHA-256")
            md.update(password.toByteArray())
            val digest: ByteArray = md.digest()

            return String(Base64.encode(digest, Base64.DEFAULT));
        } catch (e: NoSuchAlgorithmException) {
            password
        }
    }

    /**
     * Method for Edit text shake animation
     * @param
     * @return shake animation -> TranslateAnimation
     * */
    fun shakeError(): TranslateAnimation {
        val shake = TranslateAnimation(0f, 10f, 0f, 0f)
        shake.duration = 300
        shake.interpolator = CycleInterpolator(7f)
        return shake
    }

    private lateinit var log: Logger
    fun initLogger() {
        log = Logger.getLogger("DEBUG")
        if (BuildConfig.DEBUG) {
            this.log.level = Level.ALL
        } else {
            log.level = Level.OFF
        }
    }
    fun printLog(TAG: String, message: String) {
        log.info("$TAG $message")
    }

    fun warningLog(TAG: String, message: String) {
        log.log(Level.WARNING, "$TAG $message")
    }

    fun warningLog(TAG: String, message: String, t: Throwable?) {
        log.log(Level.WARNING, "$TAG $message", t)
    }

}