package com.example.manuelroa_20240909_programacionmovil

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class EntradasDataBaseHelper(context: Context) : SQLiteOpenHelper(
    context , DATA_BASE , null , DATA_VERSION
) {
    companion object {
        private const val DATA_BASE = "entradas.db"
        private const val DATA_VERSION = 1
        private const val TABLE_NAME = "entradas"
        private const val COLUMN_ID = "id"
        private const val COLUMN_RESPONSABLE = "responsable"
        private const val COLUMN_NUMERO = "numero"
        private const val COLUMN_FECHA = "fecha"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_RESPONSABLE TEXT, $COLUMN_NUMERO INTEGER, $COLUMN_FECHA TEXT)"
        db?.execSQL(createTableQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }


    fun insertEntrada(entrada: Entrada){
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_RESPONSABLE , entrada.responsable)
            put(COLUMN_NUMERO , entrada.numero)
            put(COLUMN_FECHA , entrada.fecha)
        }

        db.insert(TABLE_NAME , null , values)
        db.close()
    }

    fun getAllEntry(): List<Entrada>{
        val listaEntradas = mutableListOf<Entrada>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query , null)

        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val responsable = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESPONSABLE))
            val numero = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NUMERO))
            val fecha = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA))

            val entrada = Entrada(id,  responsable , numero , fecha)

            listaEntradas.add(entrada)
        }

        cursor.close()
        db.close()

        return listaEntradas


    }

}