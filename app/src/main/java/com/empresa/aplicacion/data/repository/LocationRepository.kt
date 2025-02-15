package com.empresa.aplicacion.data.repository

import android.content.Context
import androidx.core.content.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val context: Context
) {

    private val sharedPreferences = context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

    private val _locationNameFlow = MutableStateFlow(getLocationName())
    val locationNameFlow: MutableStateFlow<String?> get() = _locationNameFlow

    // Obtener el nombre de la ubicación desde SharedPreferences
    fun getLocationName(): String? {
        return sharedPreferences.getString("location_name", null)
    }

    // Guardar el nombre de la ubicación en SharedPreferences
    fun saveLocationName(locationName: String) {
        sharedPreferences.edit {
            putString("location_name", locationName)
        }
        _locationNameFlow.value = locationName
    }
}