package com.example.mythic.data.atributo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mythic.data.aventura.AventuraBD
import com.example.mythic.data.aventura.AventuraDao
import com.example.mythic.model.Atributo


@Database(entities = [Atributo::class], version = 1, exportSchema = false)
abstract class AtributoBD : RoomDatabase(){
    abstract fun atributoDao() : AtributoDao

    companion object{
        @Volatile
        private var INSTANCIA : AtributoBD? = null

        fun obtenerBD(context : Context) : AtributoBD {
            val tempInstancia = INSTANCIA
            if(tempInstancia != null){
                return tempInstancia
            }
            synchronized(this){
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    AtributoBD::class.java,
                    "atributos_tabla"
                ).build()
                INSTANCIA = instancia
                return instancia
            }

        }
    }

}
