package com.example.mythic.data.atributo

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mythic.model.Atributo


@Dao
interface AtributoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crearAtributo(atributo: Atributo)

    @Update
    fun actualizarAtributo(atributo: Atributo)

    @Delete
    fun borrarAtributo(atributo: Atributo)

    @Query("SELECT * FROM atributos_tabla WHERE idPersonaje =:idPersonaje")
    fun obtenerAtributosPersonaje(idPersonaje : Int): LiveData<List<Atributo>>


}