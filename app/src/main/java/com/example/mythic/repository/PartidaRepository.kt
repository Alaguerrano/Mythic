package com.example.mythic.repository

import androidx.lifecycle.LiveData
import com.example.mythic.data.PartidaDao
import com.example.mythic.model.Partida

class PartidaRepository(private val partidaDao: PartidaDao) {
    val todosDatosLeidos : LiveData<List<Partida>> = partidaDao.leerTodosLosDatos()

    suspend fun insertarPartida (partida: Partida){
        partidaDao.insertarPartida(partida)

    }

    suspend fun actualizarPartida (partida: Partida){
        partidaDao.actualizarPartida(partida)
    }

}