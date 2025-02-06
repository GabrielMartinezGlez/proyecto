package com.empresa.aplicacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//class MainViewModel : ViewModel() {
//
//    val joke = liveData(Dispatchers.IO) {
//        val result = fetchJokeFromApi()
//        emit(result)
//    }
//
//    private suspend fun fetchJokeFromApi(): String {
//        return try {
//            val response = RetrofitClient.api.getRandomJoke()
//            response.value // Retorna la broma
//        } catch (e: Exception) {
//            "Error al obtener la broma: ${e.message}"
//        }
//    }
//
//    // Función para disparar la llamada a la API
//    fun fetchJoke() {
//        // Esto ya está manejado con LiveData en `joke`
//    }
//}
