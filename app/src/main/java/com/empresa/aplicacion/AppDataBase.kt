package com.empresa.aplicacion

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Animales::class],version=1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun animalesDao(): AnimalesDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context:Context):AppDatabase{
            return INSTANCE ?:synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "animales_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}