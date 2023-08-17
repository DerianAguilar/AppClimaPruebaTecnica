package com.Kotlin.pruebaapp2.View

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.Kotlin.pruebaapp2.R
import java.util.*

class DatosClimaActivity : AppCompatActivity() {


    private lateinit var tvTemperatura: TextView
    private lateinit var tvSensacionT: TextView
    private lateinit var tvHumedad: TextView
    private lateinit var tvCiudad: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_clima)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvTemperatura = findViewById(R.id.tvTemperatura)
        tvSensacionT = findViewById(R.id.tvSensacionT)
        tvHumedad = findViewById(R.id.tvHumedad)

        var temperatura = intent.getDoubleExtra("temperatura", 0.0)
        var sensacionT = intent.getDoubleExtra("sensacionT", 0.0)
        val humedad = intent.getDoubleExtra("humedad", 0.0)
        var ciudad = intent.getStringExtra("ciudad")

        temperatura -= 273.15
        sensacionT -= 273.15
        ciudad = ciudad?.split(" ")?.joinToString(" ") { it.capitalize(Locale.ROOT) }

        tvCiudad.text = "$ciudad"
        tvTemperatura.text = "${temperatura.toInt()}°C"
        tvSensacionT.text = "${sensacionT.toInt()}°C"
        tvHumedad.text = "${humedad.toInt()}%"

    }
}