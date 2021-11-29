package com.example.mythic.data.personaje

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mythic.model.Personaje

@Database(entities = [Personaje::class], version = 1, exportSchema = false)
abstract class PersonajeBD : RoomDatabase() {
    abstract fun PersonajeDao() : PersonajeDao

    companion object{
        @Volatile
        private var INSTANCIA : PersonajeBD? = null

        fun obtenerBD(context : Context) : PersonajeBD {
            val tempInstancia = INSTANCIA
            if(tempInstancia != null){
                return tempInstancia
            }
            synchronized(this){
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    PersonajeBD::class.java,
                    "personajes_tabla"
                ).build()
                INSTANCIA = instancia
                return instancia
            }

        }
    }
}