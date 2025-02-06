package com.empresa.aplicacion
import retrofit2.http.GET
interface ChuckNorrisJokeService {
    @GET("jokes/random")
    suspend fun getRandomJoke(): JokeResponse
}