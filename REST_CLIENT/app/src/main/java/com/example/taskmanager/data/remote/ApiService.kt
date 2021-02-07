package com.example.taskmanager.data.remote


import com.example.taskmanager.data.model.request.LoginRequest
import com.example.taskmanager.data.model.request.RegistrationRequest
import com.example.taskmanager.data.model.response.LoginResponse
import com.example.taskmanager.data.model.response.RegistrationResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * <h1>ApiService</h1>
 * <p>
 *  A blueprint of all remote functions
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
interface ApiService {

    @POST("/users/login")
    suspend fun login(@Body loginRequest : LoginRequest): Response<LoginResponse>

    @POST("/users/register")
    suspend fun register(@Body registrationRequest: RegistrationRequest): Response<RegistrationResponse>
}