package com.example.mythic.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.mythic.data.aventura.AventuraBD
import com.example.mythic.data.aventura.AventuraDao
import com.example.mythic.model.Aventura
import com.example.mythic.model.Jugador
import com.example.mythic.repository.AventuraRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AventuraViewModel (application: Application, idJugador: Int) : AndroidViewModel(application) {

    val listaAventuras : LiveData<List<Aventura>>
    val id = idJugador

    private val repository : AventuraRepository
    public val aventuraDao : AventuraDao

    init{
        aventuraDao = AventuraBD.obtenerBD(application).aventuraDao()
        repository = AventuraRepository(aventuraDao, id)
        listaAventuras = repository.listaAventurasJugador


    }

    fun crearAventura (aventura: Aventura){
        viewModelScope.launch(Dispatchers.IO) {
            repository.crearAventura(aventura)
        }
    }

    fun actualizarAventura (aventura: Aventura){
        viewModelScope.launch(Dispatchers.IO) {
            repository.actualizarAventura(aventura)
        }
    }

    fun borrarAventura (aventura: Aventura){
        viewModelScope.launch(Dispatchers.IO) {
            repository.borrarAventura(aventura)
        }
    }

    //TODO ver si funciona o hacerlo desde DAO con Query
    fun borrarAventuras (aventuras : Array<Aventura>){
        viewModelScope.launch(Dispatchers.IO) {
            for(aventura in aventuras) {
                repository.borrarAventura(aventura)
            }
        }
    }



}

