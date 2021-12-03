package com.example.mythic.fragments.jugador.jugador_seleccionado

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mythic.R
import com.example.mythic.fragments.jugador.actualizar_jugador.ActualizarJugadorFragmentArgs


class JugadorSeleccionadoFragment : Fragment() {

    private val args by navArgs<ActualizarJugadorFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_jugador_seleccionado, container, false)
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
        findNavController().navigate(R.id.action_actualizarJugadorFragment_to_listaJugadoresFragment)
    }


}