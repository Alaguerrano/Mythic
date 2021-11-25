package com.example.mythic.data

import androidx.lifecycle.LiveData

class PartidaRepository(private val partidaDao: PartidaDao) {
    val todosDatosLeidos : LiveData<List<Partida>> = partidaDao.leerTodosLosDatos()

    suspend fun insertarPartida (partida: Partida){
        partidaDao.insertarPartida(partida)
    }
}