package com.example.taskmanager.data.repository

import com.example.taskmanager.data.model.request.LoginRequest
import com.example.taskmanager.data.model.request.RegistrationRequest
import com.example.taskmanager.data.model.response.LoginResponse
import com.example.taskmanager.data.model.response.RegistrationResponse
import com.example.taskmanager.data.remote.ApiHelper
import com.example.taskmanager.utils.Resource

import javax.inject.Inject

/**
 * <h1>MainRepository</h1>
 * <p>
 *  A repository class to access data
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
class MainRepository @Inject constructor(private val apiHelperImpl: ApiHelper) {

    suspend fun login(loginRequest : LoginRequest) : Resource<LoginResponse> =  apiHelperImpl.login(loginRequest)

    suspend fun register(registrationRequest: RegistrationRequest) : Resource<RegistrationResponse> =  apiHelperImpl.register(registrationRequest)

}