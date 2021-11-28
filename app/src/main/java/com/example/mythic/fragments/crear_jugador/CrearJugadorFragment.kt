package com.example.mythic.fragments.crear_jugador

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mythic.R
import com.example.mythic.model.Jugador
import com.example.mythic.viewmodel.JugadorViewModel
import kotlinx.android.synthetic.main.fragment_crear_jugador.*
import kotlinx.android.synthetic.main.fragment_crear_jugador.view.*


class InsertarPartidaFragment : Fragment() {

    private lateinit var mJugadorViewModel: JugadorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_crear_jugador, container, false)
        mJugadorViewModel = ViewModelProvider(this).get(JugadorViewModel::class.java)
        view.button.setOnClickListener {
            insertarDatos()
        }
        return view
    }

    private fun insertarDatos() {
        val nombre = nombre_et.text.toString()
        val masterHumano = master_humano_sw.isChecked()
        val multijugador = multijugador_sw.isChecked()
        val motorDistintoMythic = motorDistintoMythic_sw.isChecked()
        if (comprobarCampos(nombre)) {
            if (comprobarNombre(nombre)) {
                val jugador = Jugador(0, nombre, masterHumano, multijugador, motorDistintoMythic)
                mJugadorViewModel.crearJugador(jugador)
                Toast.makeText(requireContext(), "Perfil de Jugador creado", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_crearJugadorFragment_to_listaJugadoresFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Debes elegir otro nombre para el Perfil de Jugador",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show()
        }

    }

    //Comprueba si todos los campos del formulario están llenos
    private fun comprobarCampos(nombre: String): Boolean {
        return !(TextUtils.isEmpty(nombre))
    }

    private fun comprobarNombre(nombre: String): Boolean {
        //TODO implementar
        return true

    }

}