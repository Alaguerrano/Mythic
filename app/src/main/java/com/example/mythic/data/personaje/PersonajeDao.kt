package com.example.mythic.data.personaje

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mythic.model.Aventura
import com.example.mythic.model.Personaje

@Dao
interface PersonajeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crearPersonaje(personaje: Personaje)

    @Update
    fun actualizarPersonaje(personaje: Personaje)

    @Delete
    fun borrarPersonaje(personaje: Personaje)

    @Query("SELECT * FROM personajes_tabla WHERE idJugador =:idJugador AND estaEnAventura= 0 AND esPJ= 1")
    fun obtenerPJSinAventura(idJugador : Int): LiveData<List<Personaje>>

    @Query("SELECT * FROM personajes_tabla ORDER BY id ASC")
    fun obtenerPersonajes(): LiveData<List<Personaje>>
}