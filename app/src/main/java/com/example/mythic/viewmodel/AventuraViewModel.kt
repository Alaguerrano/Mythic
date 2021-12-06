package com.example.mythic.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mythic.data.aventura.AventuraBD
import com.example.mythic.data.aventura.AventuraDao
import com.example.mythic.model.Aventura
import com.example.mythic.model.Jugador
import com.example.mythic.repository.AventuraRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AventuraViewModel (application: Application) : AndroidViewModel(application) {

    val listaAventuras : LiveData<List<Aventura>>


    private val repository : AventuraRepository
    public val aventuraDao : AventuraDao

    init{
        aventuraDao = AventuraBD.obtenerBD(application).aventuraDao()
        repository = AventuraRepository(aventuraDao)
        listaAventuras = repository.listaAventuras


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

    fun borrarAventuras (aventuras : Array<Aventura>){
        viewModelScope.launch(Dispatchers.IO) {
            for(aventura in aventuras) {
                repository.borrarAventura(aventura)
            }
        }
    }

    fun obtenerAventuraIdJugador (idJugador : Int): LiveData<List<Aventura>>{
        return repository.obtenerAventuraIdJugador(idJugador)
    }

}