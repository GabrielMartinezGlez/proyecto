package com.empresa.aplicacion.domain.useCase

import com.empresa.aplicacion.data.network.ChuckNorrisResponse
import com.empresa.aplicacion.data.repository.JokeRepository
import javax.inject.Inject

class obtenerChisteUseCase @Inject constructor(
    private val jokeRepository: JokeRepository
) {
    suspend fun execute(): ChuckNorrisResponse {
        // Lógica para obtener el chiste de Chuck Norris desde la API
        return jokeRepository.obtenerChiste()
    }
}