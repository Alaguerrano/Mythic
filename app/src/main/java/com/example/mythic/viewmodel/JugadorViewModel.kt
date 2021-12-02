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
    var numeroJugadoresIgualNombre = MutableLiveData<Int>()

    private val repository : JugadorRepository
    public val jugadorDao : JugadorDao

    init{
        jugadorDao = JugadorBD.obtenerBD(application).jugadorDao()
        repository = JugadorRepository(jugadorDao)
        listaJugadores = repository.listaJugadores


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


    fun contarJugadoresConNombreIgualA(otroNombre : String){


        viewModelScope.launch(Dispatchers.IO){
            numeroJugadoresIgualNombre.postValue (repository.contarJugadoresConNombreIgualA(otroNombre))

            Log.e("NUMERO:",numeroJugadoresIgualNombre.value.toString() )
        }
    }




}