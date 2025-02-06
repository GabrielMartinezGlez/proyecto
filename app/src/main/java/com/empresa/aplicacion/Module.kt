package com.empresa.aplicacion

import android.content.Context
import androidx.room.Room
import com.empresa.aplicacion.AppDatabase
import com.empresa.aplicacion.AnimalesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Proveer la instancia de la base de datos
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "animales_database" // Nombre de la base de datos
        ).build()
    }

    // Proveer el DAO
    @Provides
    fun provideAnimalesDao(database: AppDatabase): AnimalesDao {
        return database.animalesDao()
    }
}
