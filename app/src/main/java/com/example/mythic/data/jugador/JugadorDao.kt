package com.example.mythic.data.jugador


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mythic.model.Jugador



@Dao
interface JugadorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crearJugador(jugador: Jugador)

    @Update
    fun actualizarJugador(jugador: Jugador)

    @Delete
    fun borrarJugador(jugador: Jugador)

    @Query("SELECT * FROM jugadores_tabla ORDER BY id ASC")
    fun obtenerJugadores(): LiveData<List<Jugador>>




}