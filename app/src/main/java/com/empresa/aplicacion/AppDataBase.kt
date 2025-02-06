package com.empresa.aplicacion

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Database(entities = [Animales::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animalesDao(): AnimalesDao
}
