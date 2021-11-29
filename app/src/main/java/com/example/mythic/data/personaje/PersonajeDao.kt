package com.example.mythic.data.personaje

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mythic.model.Personaje

@Dao
interface PersonajeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crearPersonaje(personaje: Personaje)

    @Update
    fun actualizarPersonaje(personaje: Personaje)

    @Delete
    fun borrarPersonaje(personaje: Personaje)

    @Query("SELECT * FROM personajes_tabla ORDER BY id ASC")
    fun leerTodosLosDatos(): LiveData<List<Personaje>>
}