package com.example.mythic.data.PJ

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mythic.model.Jugador
import com.example.mythic.model.Personaje

@Dao
interface PJDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crearPJ(personaje: Personaje)

    @Update
    fun actualizarPJ(personaje: Personaje)

    @Delete
    fun borrarPJ(personaje: Personaje)

    @Query("SELECT * FROM personajes_tabla ORDER BY id ASC")
    fun leerTodosLosDatos(): LiveData<List<Personaje>>
}