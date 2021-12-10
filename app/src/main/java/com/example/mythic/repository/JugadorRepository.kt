package com.example.mythic.repository

import androidx.lifecycle.LiveData
import com.example.mythic.data.aventura.AventuraDao
import com.example.mythic.data.jugador.JugadorDao
import com.example.mythic.model.Jugador

class JugadorRepository(private val jugadorDao: JugadorDao) {
    val listaJugadores : LiveData<List<Jugador>> = jugadorDao.obtenerJugadores()



    suspend fun crearJugador (jugador: Jugador){
        jugadorDao.crearJugador(jugador)
    }

    suspend fun actualizarJugador (jugador: Jugador){
        jugadorDao.actualizarJugador(jugador)
    }

    suspend fun borrarJugador (jugador: Jugador){
        jugadorDao.borrarJugador(jugador)
        //Por cada aventura que tenga el jugador


    }



}