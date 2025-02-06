package com.empresa.aplicacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class JokeViewModel : ViewModel() {

    private val _joke = MutableLiveData<String>()
    val joke: LiveData<String> get() = _joke

    fun fetchRandomJoke() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.chuckNorrisJokeService.getRandomJoke()
                _joke.value = response.value  // La propiedad "value" contiene el chiste
            } catch (e: Exception) {
                // Manejo de errores (por ejemplo, problemas de red)
                _joke.value = "Error al obtener el chiste."
            }
        }
    }
}
