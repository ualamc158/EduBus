package com.alberto.edubus.data

import android.content.Context
import android.util.Log
import com.alberto.edubus.model.RutaBus
import com.google.gson.Gson
import java.io.IOException

class BusRepository(private val context: Context) {

    fun obtenerRuta(): RutaBus? {
        val jsonString: String
        try {
            jsonString = context.assets.open("rutas_almeria.json")
                .bufferedReader()
                .use { it.readText() }

            return Gson().fromJson(jsonString, RutaBus::class.java)

        } catch (ioException: IOException) {
            Log.e("EduBus", "Error al leer el JSON: ${ioException.message}")
            return null
        }
    }
}