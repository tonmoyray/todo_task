package com.example.taskmanager.data.model.request

import com.example.taskmanager.data.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * <h1>LoginRequest</h1>
 * <p>
 *  A remote login request model
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
data class LoginRequest(

    @Expose
    @SerializedName("email")
    val email : String,

    @Expose
    @SerializedName("passwordHash")
    val passwordHash : String,

)