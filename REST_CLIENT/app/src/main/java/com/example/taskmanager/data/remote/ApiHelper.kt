package com.example.taskmanager.data.remote

import com.example.taskmanager.data.model.request.LoginRequest
import com.example.taskmanager.data.model.request.RegistrationRequest
import javax.inject.Inject

/**
 * <h1>ApiHelper</h1>
 * <p>
 *  An helper class that provides access to all remote function and wrap all responses into BaseDataSource
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
class ApiHelper @Inject constructor(private val apiService: ApiService) :  BaseDataSource(){

    suspend fun login(loginRequest : LoginRequest)  = getResult {  apiService.login(loginRequest) }

    suspend fun register(registrationRequest: RegistrationRequest)  = getResult {
        apiService.register(registrationRequest)
    }

}