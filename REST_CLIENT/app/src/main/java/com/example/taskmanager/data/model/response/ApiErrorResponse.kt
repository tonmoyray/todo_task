package com.example.taskmanager.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * This is the model for api response
 *
 * @owner Brotecs Technologies, LLC.
 * @version 1.0.0
 * @author Aminul Islam Rony
 * @since 6/8/2020
 * @update
 * @apiNote Please do not change any parameters without designer consent
 */
data class ApiErrorResponse(

		@Expose
		@SerializedName("timestamp")
		val timestamp : String,

		@Expose
		@SerializedName("status")
		val status : Int,

		@Expose
		@SerializedName("error")
		val error : String,
)
