package com.example.mythic.fragments.lista_partidas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.example.mythic.R
import kotlinx.android.synthetic.main.fragment_lista_partidas.view.*


class ListaPartidasFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lista_partidas, container, false)

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listaPartidasFragment_to_insertarPartidaFragment)
        }
        return view
    }


}