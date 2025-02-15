package com.empresa.aplicacion.data.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesManager @Inject constructor(
    context: Context
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("location_preferences", Context.MODE_PRIVATE)

    // Guardar el location
    fun saveLocation(Location: String) {
        sharedPreferences.edit().putString("location", Location).apply()
    }

    // Obtener el location
    fun getLocation(): String? {
        return sharedPreferences.getString("location", null)
    }


}