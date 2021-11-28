package com.example.mythic.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mythic.model.Jugador

@Database(entities = [Jugador::class], version = 1, exportSchema = false)
abstract class JugadorBD : RoomDatabase() {

    abstract fun jugadorDao() : JugadorDao

    companion object{
        @Volatile
        private var INSTANCIA : JugadorBD? = null

        fun obtenerBD(context : Context) : JugadorBD{
            val tempInstancia = INSTANCIA
            if(tempInstancia != null){
                return tempInstancia
            }
            synchronized(this){
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    JugadorBD::class.java,
                    "jugadores_tabla"
                ).build()
                INSTANCIA = instancia
                return instancia
            }

        }
    }
}