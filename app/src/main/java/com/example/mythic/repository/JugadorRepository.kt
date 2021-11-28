package com.example.mythic.repository

import androidx.lifecycle.LiveData
import com.example.mythic.data.JugadorDao
import com.example.mythic.model.Jugador

class JugadorRepository(private val jugadorDao: JugadorDao) {
    val todosDatosLeidos : LiveData<List<Jugador>> = jugadorDao.leerTodosLosDatos()

    suspend fun crearJugador (jugador: Jugador){
        jugadorDao.crearJugador(jugador)
    }

    suspend fun actualizarJugador (jugador: Jugador){
        jugadorDao.actualizarJugador(jugador)
    }

    suspend fun borrarJugador (jugador: Jugador){
        jugadorDao.borrarJugador(jugador)
    }

}