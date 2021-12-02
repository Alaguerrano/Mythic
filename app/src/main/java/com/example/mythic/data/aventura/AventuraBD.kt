package com.example.mythic.data.aventura

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mythic.model.Aventura


@Database(entities = [Aventura::class], version = 1, exportSchema = false)
abstract class AventuraBD : RoomDatabase() {

    abstract fun aventuraDao(): AventuraDao

    companion object{
        @Volatile
        private var INSTANCIA : AventuraBD? = null

        fun obtenerBD(context : Context) : AventuraBD {
            val tempInstancia = INSTANCIA
            if(tempInstancia != null){
                return tempInstancia
            }
            synchronized(this){
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    AventuraBD::class.java,
                    "aventuras_tabla"
                ).build()
                INSTANCIA = instancia
                return instancia
            }

        }
    }
}
