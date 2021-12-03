package com.example.mythic.fragments.jugador.jugador_seleccionado

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mythic.R
import com.example.mythic.fragments.aventura.ListaAventurasAdapter
import com.example.mythic.fragments.jugador.actualizar_jugador.ActualizarJugadorFragmentArgs
import com.example.mythic.viewmodel.AventuraViewModel
import com.example.mythic.viewmodel.JugadorViewModel
import kotlinx.android.synthetic.main.fragment_jugador_seleccionado.view.*


class JugadorSeleccionadoFragment : Fragment() {

    private lateinit var mJugadorViewModel: JugadorViewModel
    private lateinit var mAventuraViewModel: AventuraViewModel
    private val args by navArgs<ActualizarJugadorFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_jugador_seleccionado, container, false)

        val adapter = ListaAventurasAdapter()
        val recyclerView = view.lista_aventuras_rv
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        mJugadorViewModel = ViewModelProvider(this).get(JugadorViewModel::class.java)
        mAventuraViewModel = ViewModelProvider(this).get(AventuraViewModel::class.java)

        view.nombre_jugador.setText(args.jugadorActual.nombre)
        if(args.jugadorActual.masterHumano == true){
            view.tipo_master.setText("Master: Humano")
        }else{
            view.tipo_master.setText("Master: Mythic")
        }
        if(args.jugadorActual.multijugador == true){
            view.numero_jugadores.setText("Partida multijugador")
        }else{
            view.numero_jugadores.setText("Partida en solitario")
        }
        if(args.jugadorActual.motorDistintoMythic == true){
            view.motor_juego.setText("Motor de juego: distinto a Mythic")
        }else{
            view.motor_juego.setText("Motor de juego: Mythic")
        }
        mAventuraViewModel.listaAventuras.observe(viewLifecycleOwner, Observer { aventura ->
            adapter.establecerDatos(aventura)
            //************************************************************************************
            //Si la lista de Aventuras esta vacia, decir al usuario que cree una aventura
            //*******************************************************************************
            if(adapter.getItemCount() == 0) {
               //TODO mandar directamente a crear una Aventura
            }
        })
        setHasOptionsMenu(true)
        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.editar_jugador_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.editar_menu) {
            editarJugador()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editarJugador() {
        val action = JugadorSeleccionadoFragmentDirections.actionJugadorSeleccionadoFragmentToActualizarJugadorFragment(args.jugadorActual)
        findNavController().navigate(action)

    }


}