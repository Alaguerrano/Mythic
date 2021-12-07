package com.example.mythic.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AventuraViewModelFactory(private val application: Application, private val idJugador: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AventuraViewModel::class.java)) {
            return AventuraViewModel(application, idJugador) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}