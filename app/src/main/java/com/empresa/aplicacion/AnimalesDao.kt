package com.empresa.aplicacion

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase

@Dao
interface AnimalesDao{
    @Insert
    suspend fun insert(animales: Animales)

    @Query("SELECT * from animales")
    suspend fun getAllAnimales(): List<Animales>
    @Query("Select* from animales where id=:id")
    suspend fun getAnimalesById(id:Int): Animales
}


