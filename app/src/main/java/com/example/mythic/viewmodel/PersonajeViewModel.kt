package com.example.mythic.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import com.example.mythic.data.personaje.PersonajeBD
import com.example.mythic.data.personaje.PersonajeDao
import com.example.mythic.model.Jugador
import com.example.mythic.model.Personaje

import com.example.mythic.repository.PersonajeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonajeViewModel (application: Application) : AndroidViewModel(application) {

    val listaPersonajes : LiveData<List<Personaje>>


    private val repository : PersonajeRepository
    public val personajeDao : PersonajeDao

    init{
        personajeDao = PersonajeBD.obtenerBD(application).personajeDao()
        repository = PersonajeRepository(personajeDao)
        listaPersonajes = repository.listaPersonajes


    }

    fun crearPersonaje (personaje: Personaje){
        viewModelScope.launch(Dispatchers.IO) {
            repository.crearPersonaje(personaje)
        }
    }

    fun actualizarPersonaje (personaje: Personaje){
        viewModelScope.launch(Dispatchers.IO) {
            repository.actualizarPersonaje(personaje)
        }
    }

    fun borrarPersonaje (personaje: Personaje){
        viewModelScope.launch(Dispatchers.IO) {
            repository.borrarPersonaje(personaje)
        }
    }
}