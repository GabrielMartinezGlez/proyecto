package com.empresa.aplicacion.data.repository


import com.empresa.aplicacion.data.preferences.PreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PreferenceRepository @Inject constructor(
    private val preferencesManager: PreferencesManager
) {
    // Guardar nombre de la ubicación
    suspend fun saveLocationName(locationName: String) {
        withContext(Dispatchers.IO) {
            preferencesManager.saveLocation(locationName)
        }
    }

    // Obtener nombre de la ubicación
    suspend fun getLocationName(): String? {
        return withContext(Dispatchers.IO) {
            preferencesManager.getLocation()
        }
    }
}