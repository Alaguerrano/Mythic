package com.example.mythic.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.mythic.data.jugador.JugadorBD
import com.example.mythic.data.jugador.JugadorDao
import com.example.mythic.model.Jugador
import com.example.mythic.repository.JugadorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class JugadorViewModel (application: Application) : AndroidViewModel(application) {

    val listaJugadores : LiveData<List<Jugador>>


    private val jugadorRepository : JugadorRepository
    public val jugadorDao : JugadorDao

    init{
        jugadorDao = JugadorBD.obtenerBD(application).jugadorDao()
        jugadorRepository = JugadorRepository(jugadorDao)
        listaJugadores = jugadorRepository.listaJugadores


    }

    fun crearJugador (jugador: Jugador){
        viewModelScope.launch(Dispatchers.IO) {
            jugadorRepository.crearJugador(jugador)
        }
    }

    fun actualizarJugador (jugador: Jugador){
        viewModelScope.launch(Dispatchers.IO) {
            jugadorRepository.actualizarJugador(jugador)
        }
    }

    fun borrarJugador (jugador: Jugador){
        viewModelScope.launch(Dispatchers.IO) {
            //Borrar jugador de la base de datos
            jugadorRepository.borrarJugador(jugador)
            //Borrar aventuras que tenga la id del jugador actual
        }
    }


}