package com.example.taskmanager.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.taskmanager.data.model.request.LoginRequest
import com.example.taskmanager.data.model.request.RegistrationRequest
import com.example.taskmanager.data.model.response.LoginResponse
import com.example.taskmanager.data.model.response.RegistrationResponse
import com.example.taskmanager.data.repository.MainRepository
import com.example.taskmanager.utils.CommonHelper
import com.example.taskmanager.utils.Resource
import com.example.taskmanager.utils.SingleLiveEvent
import kotlinx.coroutines.launch

/**
 * <h1>LoginRegsitrationViewModel</h1>
 * <p>
 *  Login Viewmodel
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
class LoginRegistrationViewModel @ViewModelInject constructor(
        private val mainRepository: MainRepository
) : ViewModel() {

    var loginResponse: SingleLiveEvent<Resource<LoginResponse>> = SingleLiveEvent()
    var registrationResponse: SingleLiveEvent<Resource<RegistrationResponse>> = SingleLiveEvent()


    fun login(email: String, password: String) {
        loginResponse.postValue(Resource.loading())
        viewModelScope.launch {
            loginResponse.postValue(mainRepository.login(LoginRequest(email, password)))
        }
    }

    fun register(name :String, email: String, password: String){
        registrationResponse.postValue(Resource.loading())
        viewModelScope.launch {
            registrationResponse.postValue(mainRepository.register(RegistrationRequest(name, email, password)))
        }
    }
    
    
}