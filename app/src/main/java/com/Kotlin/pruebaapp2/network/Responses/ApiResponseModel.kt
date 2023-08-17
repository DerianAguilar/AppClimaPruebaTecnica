package com.Kotlin.pruebaapp2.network.Responses

import com.google.gson.annotations.SerializedName

data class ApiResponseModel(
    @SerializedName("main") val datosclima: DatosClima
)
