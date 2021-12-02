package com.example.mythic.repository

import android.util.Log
import androidx.lifecycle.LiveData
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

    }

    suspend fun contarJugadoresConNombreIgualA(otroNombre : String): Int{
        val numero = jugadorDao.contarJugadoresConNombreIgualA(otroNombre)
        Log.e("REPOSITORY NUMERO:",numero.toString())
        return numero
    }


}