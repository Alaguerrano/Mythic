package com.example.mythic.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mythic.data.jugador.JugadorBD
import com.example.mythic.model.Jugador
import com.example.mythic.repository.JugadorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JugadorViewModel (application: Application) : AndroidViewModel(application) {

    val todosDatosLeidos : LiveData<List<Jugador>>
    private val repository : JugadorRepository

    init{
        val jugadorDao = JugadorBD.obtenerBD(application).jugadorDao()
        repository = JugadorRepository(jugadorDao)
        todosDatosLeidos = repository.todosDatosLeidos
    }

    fun crearJugador (jugador: Jugador){
        viewModelScope.launch(Dispatchers.IO) {
            repository.crearJugador(jugador)
        }
    }

    fun actualizarJugador (jugador: Jugador){
        viewModelScope.launch(Dispatchers.IO) {
            repository.actualizarJugador(jugador)
        }
    }

    fun borrarJugador (jugador: Jugador){
        viewModelScope.launch(Dispatchers.IO) {
            repository.borrarJugador(jugador)
        }
    }
}