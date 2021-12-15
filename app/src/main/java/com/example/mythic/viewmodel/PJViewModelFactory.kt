package com.example.mythic.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PJViewModelFactory(private val application: Application, private val idJugador: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PJViewModel::class.java)) {
            return PJViewModel(application, idJugador) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}