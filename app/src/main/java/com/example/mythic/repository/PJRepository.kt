package com.example.mythic.repository

import androidx.lifecycle.LiveData
import com.example.mythic.data.personaje.PersonajeDao
import com.example.mythic.model.Jugador
import com.example.mythic.model.Personaje

class PJRepository(private val personajeDao: PersonajeDao, idJugador : Int) {
    val listaPJSinAventura : LiveData<List<Personaje>> = personajeDao.obtenerPJSinAventura(idJugador)



    suspend fun crearPJ (personaje: Personaje){
        personajeDao.crearPersonaje(personaje)
    }

    suspend fun actualizarPJ (personaje: Personaje){
        personajeDao.actualizarPersonaje(personaje)
    }

    suspend fun borrarPJ (personaje: Personaje){
        personajeDao.borrarPersonaje(personaje)
    }

    suspend fun borrarPJs (idJugador: Int){
        personajeDao.borrarPJs(idJugador)

    }


}