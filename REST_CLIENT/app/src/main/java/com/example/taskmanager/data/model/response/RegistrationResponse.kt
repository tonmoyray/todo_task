package com.example.taskmanager.data.model.response

import com.example.taskmanager.data.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * <h1>LoginResponse</h1>
 * <p>
 *  A remote response model
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
data class RegistrationResponse(

    @Expose
    @SerializedName("timestamp")
    val timestamp : String,

    @Expose
    @SerializedName("status")
    val status : Int,

    @Expose
    @SerializedName("message")
    val message : String,

    @Expose
    @SerializedName("user")
    var user : User? = null
)