package com.example.taskmanager.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * <h1>User</h1>
 * <p>
 *  A model representing single User data
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
data class User (
    @Expose
    @SerializedName("userName")
    val userName: String = "",

    @Expose
    @SerializedName("email")
    val email: String = "",

    @Expose
    @SerializedName("passwordHash")
    val passwordHash: String = ""
)