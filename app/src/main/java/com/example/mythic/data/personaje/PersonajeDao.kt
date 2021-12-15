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

    @Query("SELECT * FROM personajes_tabla WHERE idJugador =:idJugador AND idAventura= -1 AND esNPJ= 0")
    fun obtenerPJsSinAventura(idJugador : Int): LiveData<List<Personaje>>

    @Query("SELECT * FROM personajes_tabla WHERE idJugador =:idJugador AND esNPJ= 0")
    fun obtenerPJsJugador(idJugador : Int): LiveData<List<Personaje>>

    @Query("SELECT * FROM personajes_tabla ORDER BY id ASC")
    fun obtenerPersonajes(): LiveData<List<Personaje>>

    @Query("DELETE FROM personajes_tabla WHERE idJugador =:idJugador AND esNPJ= 0")
    fun borrarPJs(idJugador: Int)
}