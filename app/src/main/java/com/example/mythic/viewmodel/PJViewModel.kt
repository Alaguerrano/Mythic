package com.example.mythic.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import com.example.mythic.data.personaje.PersonajeBD
import com.example.mythic.data.personaje.PersonajeDao
import com.example.mythic.model.Jugador
import com.example.mythic.model.Personaje

import com.example.mythic.repository.PJRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PJViewModel (application: Application, idJugador: Int) : AndroidViewModel(application) {

    val listaPJsSinAventura : LiveData<List<Personaje>>
    val listaPJsJugador : LiveData<List<Personaje>>
    val id = idJugador


    private val repository : PJRepository
    public val personajeDao : PersonajeDao

    init{
        personajeDao = PersonajeBD.obtenerBD(application).personajeDao()
        repository = PJRepository(personajeDao, idJugador)
        listaPJsSinAventura = repository.listaPJsSinAventura
        listaPJsJugador = repository.listaPJsJugador

    }

    fun crearPJ (personaje: Personaje){
        viewModelScope.launch(Dispatchers.IO) {
            repository.crearPJ(personaje)
        }
    }

    fun actualizarPJ (personaje: Personaje){
        viewModelScope.launch(Dispatchers.IO) {
            repository.actualizarPJ(personaje)
        }
    }

    fun borrarPJ (personaje: Personaje){
        viewModelScope.launch(Dispatchers.IO) {
            repository.borrarPJ(personaje)
        }
    }

    fun borrarPJsJUgador (){
        viewModelScope.launch(Dispatchers.IO) {
            repository.borrarPJs(id)
        }
    }


}