package com.example.mythic.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Partida::class], version = 1, exportSchema = false)
abstract class PartidaBD : RoomDatabase() {

    abstract fun partidaDao() : PartidaDao

    companion object{
        @Volatile
        private var INSTANCIA : PartidaBD? = null

        fun obtenerBD(context : Context) : PartidaBD{
            val tempInstancia = INSTANCIA
            if(tempInstancia != null){
                return tempInstancia
            }
            synchronized(this){
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    PartidaBD::class.java,
                    "partidas_tabla"
                ).build()
                INSTANCIA = instancia
                return instancia
            }

        }
    }
}