package com.example.mythic.fragments.personaje

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
import com.example.mythic.fragments.aventura.lista_aventuras.ListaAventurasAdapter
import com.example.mythic.fragments.aventura.lista_aventuras.ListaAventurasFragmentArgs
import com.example.mythic.fragments.aventura.lista_aventuras.ListaAventurasFragmentDirections
import com.example.mythic.viewmodel.AventuraViewModel
import com.example.mythic.viewmodel.AventuraViewModelFactory
import com.example.mythic.viewmodel.PJViewModel
import com.example.mythic.viewmodel.PJViewModelFactory
import kotlinx.android.synthetic.main.fragment_lista_aventuras.view.*
import kotlinx.android.synthetic.main.fragment_lista_p_j.view.*


class ListaPJFragment : Fragment() {

    private lateinit var mPJViewModel: PJViewModel
    private lateinit var mPJViewModelFactory: PJViewModelFactory
    private val args by navArgs<ListaPJFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lista_p_j, container, false)
        val listaPJAdapter = ListaPJAdapter(args.jugadorActual.id)
        val recyclerView = view.lista_PJs_rv
        recyclerView.adapter = listaPJAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mPJViewModelFactory = PJViewModelFactory(activity?.application!!, args.jugadorActual.id)
        mPJViewModel = ViewModelProvider(this,mPJViewModelFactory).get(PJViewModel::class.java)

        mPJViewModel.listaPJsJugador.observe(viewLifecycleOwner, Observer { pj ->
            listaPJAdapter.establecerDatos(pj)

            //************************************************************************************
            //Si la lista de PJs esta vacia, decir al usuario que cree un pj
            //*******************************************************************************

            Log.e("PJS JUGADOR:",mPJViewModel.listaPJsJugador.value?.size.toString())
            //Comprobar si hay alguna aventura creada con la id del Jugador
            if(listaPJAdapter.getItemCount() == 0){
                //Mostrar mensaje de Crear Aventura
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Tu Jugador no tiene ningun PJ creado.")
                builder.setMessage("Debes crear al menos un PJ para poder jugar.")
                builder.create().show()
                //Mandarlo a pantalla crear una Aventura
                val action = ListaPJFragmentDirections.actionListaPJFragmentToCrearPJFragment(args.jugadorActual, listaPJAdapter.obtenerArrayPJs())
                findNavController().navigate(action)
            }


        })

        return view
    }


}