package com.empresa.aplicacion.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AutobusDao {

    @Insert
    suspend fun insert(autobus: AutobusEntity)

    @Query("SELECT * FROM autobuses")
    suspend fun getAll(): List<AutobusEntity>
    @Query ("SELECT * FROM autobuses WHERE id=:id" )
    suspend fun getById(id: Int):AutobusEntity?
    @Delete
    suspend fun deleteAutobus(autobus: AutobusEntity)
}


