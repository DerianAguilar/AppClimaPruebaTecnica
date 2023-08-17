package com.Kotlin.pruebaapp2.network

import com.Kotlin.pruebaapp2.network.Responses.ApiResponseModel
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface apiService {

    @GET("weather")
    suspend fun getDatosClima (
        @Query("q") ciudad: String,
        @Query("appid") apiKey: String
    ): Response<ApiResponseModel>

}