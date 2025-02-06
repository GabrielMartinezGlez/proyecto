package com.empresa.aplicacion

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    private const val BASE_URL="https://api.chucknorris.io/"

    val chuckNorrisJokeService: ChuckNorrisJokeService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChuckNorrisJokeService::class.java)
    }
}