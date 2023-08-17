package com.Kotlin.pruebaapp2.network.Responses

import com.google.gson.annotations.SerializedName

data class DatosClima(
    @SerializedName("temp") val temperatura: Double,
    @SerializedName("feels_like") val sensacionTermica: Double,
    @SerializedName("humidity") val humeda: Double
)
