package com.empresa.aplicacion

import javax.inject.Inject

class AppRepository @Inject constructor(
    private val animalesDao: AnimalesDao
) {
    suspend fun insertAnimal(animales: Animales) {
        animalesDao.insert(animales)
    }

    suspend fun getAllAnimales(): List<Animales> {
        return animalesDao.getAllAnimales()
    }

    suspend fun getAnimalesById(id: Int): Animales? {
        return animalesDao.getAnimalesById(id)
    }
}
