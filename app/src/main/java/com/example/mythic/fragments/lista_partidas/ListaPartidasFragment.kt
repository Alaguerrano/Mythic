package com.example.mythic.fragments.lista_partidas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mythic.R
import com.example.mythic.viewmodel.PartidaViewModel
import kotlinx.android.synthetic.main.fragment_lista_partidas.view.*


class ListaPartidasFragment : Fragment() {

    private lateinit var mPartidaViewModel : PartidaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lista_partidas, container, false)

        val adapter = ListaPartidaAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mPartidaViewModel = ViewModelProvider(this).get(PartidaViewModel::class.java)
        mPartidaViewModel.todosDatosLeidos.observe(viewLifecycleOwner, Observer { partida ->
            adapter.establecerDatos(partida)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listaPartidasFragment_to_insertarPartidaFragment)
        }
        return view
    }


}