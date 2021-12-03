package com.example.mythic.repository

import androidx.lifecycle.LiveData
import com.example.mythic.data.personaje.PersonajeDao
import com.example.mythic.model.Personaje

class PersonajeRepository(private val personajeDao: PersonajeDao) {
    val listaPersonajes : LiveData<List<Personaje>> = personajeDao.obtenerPersonajes()



    suspend fun crearPersonaje (personaje: Personaje){
        personajeDao.crearPersonaje(personaje)
    }

    suspend fun actualizarPersonaje (personaje: Personaje){
        personajeDao.actualizarPersonaje(personaje)
    }

    suspend fun borrarPersonaje (personaje: Personaje){
        personajeDao.borrarPersonaje(personaje)

    }


}