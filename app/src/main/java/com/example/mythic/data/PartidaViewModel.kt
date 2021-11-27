package com.example.mythic.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mythic.model.Partida
import com.example.mythic.repository.PartidaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartidaViewModel (application: Application) : AndroidViewModel(application) {

    val todosDatosLeidos : LiveData<List<Partida>>
    private val repository : PartidaRepository

    init{
        val partidaDao = PartidaBD.obtenerBD(application).partidaDao()
        repository = PartidaRepository(partidaDao)
        todosDatosLeidos = repository.todosDatosLeidos
    }

    fun insertarPartida (partida: Partida){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertarPartida(partida)
        }
    }


}