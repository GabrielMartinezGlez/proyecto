package com.empresa.aplicacion

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MiBaseDeDatos (context: Context):SQLiteOpenHelper(context,"MiBaseDeDatos.db",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery ="""
            CREATE TABLE IF NOT EXISTS animales(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT,
            raza TEXT
            );
            """
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL("DROP TABLE IF EXISTS animales")
        onCreate(db)
    }
    fun insertarAnimales(nombre: String, raza:String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("raza", raza)
        }
        val id = db.insert("animales", null, values)
        db.close()
        return id
    }


    @SuppressLint("Range")
    fun obtenerAnimales(): List<Animales> {
        val db = readableDatabase
        val animales = mutableListOf<Animales>()

        val cursor: Cursor = db.rawQuery("SELECT * FROM animales", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex("id"))
                val nombre = cursor.getString(cursor.getColumnIndex("nombre"))
                val raza = cursor.getString(cursor.getColumnIndex("raza"))
                animales.add(Animales(id, nombre, raza))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return animales
    }

    fun obtenerAnimalesPorId(id: Long): Animales? {
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM animales WHERE id = ?", arrayOf(id.toString()))
        var animales: Animales? = null

        if (cursor.moveToFirst()) {
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
            val raza = cursor.getString(cursor.getColumnIndexOrThrow("raza"))
            animales = Animales(id, nombre, raza)
        }
        cursor.close()
        db.close()
        return animales
    }

    fun actualizarAnimales(id: Long, nombre: String, raza: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("raza", raza)
        }
        val filasAfectadas = db.update("animales", values, "id = ?", arrayOf(id.toString()))
        db.close()
        return filasAfectadas
    }
    fun eliminarAnimales(id: Long): Int {
        val db = writableDatabase
        val filasEliminadas = db.delete("animales", "id = ?", arrayOf(id.toString()))
        db.close()
        return filasEliminadas
    }

    fun eliminarTodosAnimales(): Int {
        val db = writableDatabase
        val filasEliminadas = db.delete("animales", null, null)
        db.close()
        return filasEliminadas
    }
}