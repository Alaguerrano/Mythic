package com.example.mythic.fragments.aventura

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.mythic.R
import com.example.mythic.fragments.jugador.jugador_seleccionado.JugadorSeleccionadoFragmentArgs
import kotlinx.android.synthetic.main.fragment_aventura_seleccionada.view.*
import kotlinx.android.synthetic.main.fragment_jugador_seleccionado.view.*


class AventuraSeleccionadaFragment : Fragment() {

    private val args by navArgs<AventuraSeleccionadaFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_aventura_seleccionada, container, false)
        view.nombre_aventura.setText("Nombre de la Aventura: " + args.aventuraSeleccionada.nombre)
        return view
    }


}