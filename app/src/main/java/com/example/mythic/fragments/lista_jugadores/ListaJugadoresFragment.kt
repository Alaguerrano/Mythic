package com.example.mythic.fragments.lista_jugadores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mythic.R
import com.example.mythic.R.id.action_listaJugadoresFragment_to_crearJugadorFragment
import com.example.mythic.viewmodel.JugadorViewModel
import kotlinx.android.synthetic.main.fragment_lista_jugadores.view.*


class ListaJugadoresFragment : Fragment() {

    private lateinit var mJugadorViewModel : JugadorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lista_jugadores, container, false)

        val adapter = ListaJugadoresAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mJugadorViewModel = ViewModelProvider(this).get(JugadorViewModel::class.java)

        mJugadorViewModel.todosDatosLeidos.observe(viewLifecycleOwner, Observer { partida ->
            adapter.establecerDatos(partida)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(action_listaJugadoresFragment_to_crearJugadorFragment)
        }
        return view
    }


}