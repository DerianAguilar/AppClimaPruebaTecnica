package com.Kotlin.pruebaapp2.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.Kotlin.pruebaapp2.R
import com.Kotlin.pruebaapp2.core.Constantes
import com.Kotlin.pruebaapp2.network.Responses.ApiResponseModel
import com.Kotlin.pruebaapp2.network.RetrofitClient
import com.Kotlin.pruebaapp2.network.apiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var txtCiudad: EditText
    private lateinit var btnConsultar: Button
    private lateinit var txtPais: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnConsultar = findViewById<AppCompatButton>(R.id.btnConsultar)
        txtCiudad = findViewById<AppCompatEditText>(R.id.txtBuscar)
        txtPais = findViewById<AppCompatEditText>(R.id.txtBuscarPais)


        btnConsultar.setOnClickListener{
            var ciudad = txtCiudad.text.toString()
            ciudad = ciudad.trim()
            var pais = txtPais.text.toString()
            pais = pais.trim()

            if(ciudad.isNotEmpty() && pais.isNotEmpty()){
                getLatLon(ciudad, pais)
            }else {
                Toast.makeText(this, "Los dos campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getLatLon (nameCiudad: String, pais: String){

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = RetrofitClient.webService.getDatosClima("$nameCiudad, $pais", Constantes.API_KEY)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful){
                        val datosRespuesta = response.body()
                        mostrarResultados(datosRespuesta, nameCiudad)
                    }else {
                        Toast.makeText(this@MainActivity, "No se encontro la ciudad", Toast.LENGTH_SHORT).show()
                    }
                }

            }catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error en la peticion", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    private fun mostrarResultados(resultados: ApiResponseModel?, ciudad: String) {
        val intent = Intent(this, DatosClimaActivity::class.java)
        intent.putExtra("temperatura", resultados?.datosclima?.temperatura)
        intent.putExtra("sensacionT", resultados?.datosclima?.sensacionTermica)
        intent.putExtra("humedad", resultados?.datosclima?.humeda)
        intent.putExtra("ciudad", ciudad)
        startActivity(intent)
    }

}