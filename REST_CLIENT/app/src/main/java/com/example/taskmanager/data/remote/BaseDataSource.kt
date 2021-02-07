package com.example.taskmanager.data.remote


import android.util.Log
import com.example.taskmanager.data.model.response.ApiErrorResponse
import com.example.taskmanager.data.model.response.LoginResponse
import com.example.taskmanager.utils.Resource
import com.google.gson.Gson
import retrofit2.Response
import timber.log.Timber

/**
 * <h1>BaseDataSource</h1>
 * <p>
 *  A wrapper for all API responses
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            val apiResponse = Gson().fromJson(
                    response.errorBody()!!.charStream(),
                    ApiErrorResponse::class.java
            )
            val errorMessage = "${apiResponse?.error}"
            return if(errorMessage.isNotEmpty()){
                error(errorMessage)
            }else{
                error("Network call has failed for : ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.d(message)
        return Resource.error(message)
    }

}