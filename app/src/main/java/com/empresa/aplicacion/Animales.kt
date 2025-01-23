package com.empresa.aplicacion

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "animales")
data class Animales(
    @PrimaryKey(autoGenerate = true)val id: Long=0,
    val name :String,
    val raza: String
)
