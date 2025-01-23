package com.empresa.aplicacion

import android.content.Context
import androidx.room.Room

class AppRepository(private val animalesDao: AnimalesDao){
    suspend fun insertAnimal(animales: Animales){
       animalesDao.insert(animales)
    }

    suspend fun getAllAnimales(): List<Animales>{
        return animalesDao.getAllAnimales()
    }
    suspend fun getAnimalesById(id:Int):Animales?{
        return animalesDao.getAnimalesById(id)
    }
}
