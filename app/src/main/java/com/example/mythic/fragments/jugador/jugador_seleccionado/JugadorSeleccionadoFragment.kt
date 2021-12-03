package com.example.mythic.fragments.jugador.jugador_seleccionado

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mythic.R
import com.example.mythic.fragments.jugador.actualizar_jugador.ActualizarJugadorFragmentArgs
import com.example.mythic.viewmodel.JugadorViewModel
import kotlinx.android.synthetic.main.fragment_actualizar_jugador.view.*
import kotlinx.android.synthetic.main.fragment_jugador_seleccionado.view.*


class JugadorSeleccionadoFragment : Fragment() {

    private lateinit var mJugadorViewModel: JugadorViewModel
    private val args by navArgs<ActualizarJugadorFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_jugador_seleccionado, container, false)

        mJugadorViewModel = ViewModelProvider(this).get(JugadorViewModel::class.java)

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
        findNavController().navigate(R.id.action_jugadorSeleccionadoFragment_to_actualizarJugadorFragment)
    }


}