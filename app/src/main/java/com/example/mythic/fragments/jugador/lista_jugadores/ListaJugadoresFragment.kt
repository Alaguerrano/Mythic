package com.example.mythic.fragments.jugador.lista_jugadores

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mythic.R

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

        val listaJugadoresAdapter = ListaJugadoresAdapter()



        val recyclerView = view.recyclerView
        recyclerView.adapter = listaJugadoresAdapter

        //Añade una division en la lista horizontal
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.HORIZONTAL
            )
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mJugadorViewModel = ViewModelProvider(this).get(JugadorViewModel::class.java)

        mJugadorViewModel.listaJugadores.observe(viewLifecycleOwner, Observer { jugador ->
            listaJugadoresAdapter.establecerDatos(jugador)
            //************************************************************************************
            //Si la lista de Jugadores esta vacia, decir al usuario que cree un perfil de Jugador
            //*******************************************************************************
            if(listaJugadoresAdapter.getItemCount() == 0) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Perfiles de Jugador vacio")
                builder.setMessage("Debes crear un Perfil de Jugador para poder jugar")
                builder.create().show()
                val action = ListaJugadoresFragmentDirections.actionListaJugadoresFragmentToCrearJugadorFragment(listaJugadoresAdapter.obtenerArrayJugadores())
                findNavController().navigate(action)
            }
        })





        //********************************************************************
        //Cuando pulsas el botón + te manda a Crear un nuevo Perfil de Jugador
        //*********************************************************************
        view.aventuras_bt.setOnClickListener{
            val action = ListaJugadoresFragmentDirections.actionListaJugadoresFragmentToCrearJugadorFragment(listaJugadoresAdapter.obtenerArrayJugadores())
            findNavController().navigate(action)

        }
        return view
    }


}