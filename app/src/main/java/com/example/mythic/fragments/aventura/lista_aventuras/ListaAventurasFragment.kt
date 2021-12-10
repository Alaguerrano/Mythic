package com.example.mythic.fragments.aventura.lista_aventuras

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mythic.R
import com.example.mythic.fragments.jugador.jugador_seleccionado.JugadorSeleccionadoFragmentArgs
import com.example.mythic.fragments.jugador.jugador_seleccionado.JugadorSeleccionadoFragmentDirections
import com.example.mythic.viewmodel.AventuraViewModel
import com.example.mythic.viewmodel.AventuraViewModelFactory
import kotlinx.android.synthetic.main.fragment_lista_aventuras.view.*


class ListaAventurasFragment : Fragment() {

    private lateinit var mAventuraViewModel: AventuraViewModel
    private lateinit var mAventuraViewModelFactory: AventuraViewModelFactory
    private val args by navArgs<ListaAventurasFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lista_aventuras, container, false)

        val listaAventurasAdapter = ListaAventurasAdapter(args.jugadorActual.id)
        val recyclerView = view.lista_aventuras_rv
        recyclerView.adapter = listaAventurasAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mAventuraViewModelFactory = AventuraViewModelFactory(activity?.application!!, args.jugadorActual.id)
        mAventuraViewModel = ViewModelProvider(this,mAventuraViewModelFactory).get(AventuraViewModel::class.java)

        mAventuraViewModel.listaAventuras.observe(viewLifecycleOwner, Observer { aventura ->
            listaAventurasAdapter.establecerDatos(aventura)

            //************************************************************************************
            //Si la lista de Aventuras esta vacia, decir al usuario que cree una aventura
            //*******************************************************************************

            Log.e("AVENTURAS JUGADOR:",mAventuraViewModel.listaAventuras.value?.size.toString())
            //Comprobar si hay alguna aventura creada con la id del Jugador
            if(listaAventurasAdapter.getItemCount() == 0){
                //Mostrar mensaje de Crear Aventura
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Tu Jugador no tiene ninguna Aventura creada.")
                builder.setMessage("Debes crear al menos una aventura en tu Perfil de Jugador, para poder jugar.")
                builder.create().show()
                //Mandarlo a pantalla crear una Aventura
                val action = ListaAventurasFragmentDirections.actionListaAventurasFragmentToCrearAventuraFragment(args.jugadorActual, listaAventurasAdapter.obtenerArrayAventuras())

                findNavController().navigate(action)
            }


        })

        return view
    }


}