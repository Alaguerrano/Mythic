package com.example.mythic.repository

import androidx.lifecycle.LiveData
import com.example.mythic.data.aventura.AventuraDao
import com.example.mythic.model.Aventura


class AventuraRepository(private val aventuraDao: AventuraDao, idJugador : Int) {
    val listaAventurasJugador : LiveData<List<Aventura>> = aventuraDao.obtenerAventurasJugador(idJugador)

    suspend fun crearAventura (aventura: Aventura){
        aventuraDao.crearAventura(aventura)
    }

    suspend fun actualizarAventura (aventura: Aventura){
        aventuraDao.actualizarAventura(aventura)
    }

    suspend fun borrarAventura (aventura: Aventura){
        aventuraDao.borrarAventura(aventura)
    }

    suspend fun borrarAventurasJugador(idJugador : Int){
        aventuraDao.borrarAventurasJugador(idJugador)
    }

}