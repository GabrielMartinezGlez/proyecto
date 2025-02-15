package com.empresa.aplicacion.data.repository


import com.empresa.aplicacion.data.network.ChuckNorrisResponse
import com.empresa.aplicacion.data.network.ChuckNorrisService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JokeRepository @Inject constructor(
    private val apiService: ChuckNorrisService
) {

    suspend fun obtenerChiste(): ChuckNorrisResponse {
        return withContext(Dispatchers.IO) {
            apiService.getChuckNorrisJoke()
        }
    }
}