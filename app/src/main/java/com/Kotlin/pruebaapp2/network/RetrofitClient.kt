package com.Kotlin.pruebaapp2.network

import com.Kotlin.pruebaapp2.core.Constantes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val webService: apiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL_TEMP)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiService::class.java)
    }

}