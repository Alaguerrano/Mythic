package com.example.mythic.fragments.aventura

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mythic.R
import com.example.mythic.fragments.jugador.crear_jugador.CrearJugadorFragmentArgs
import com.example.mythic.fragments.jugador.lista_jugadores.ListaJugadoresFragmentDirections
import com.example.mythic.model.Aventura
import com.example.mythic.model.Jugador
import com.example.mythic.viewmodel.AventuraViewModel
import com.example.mythic.viewmodel.AventuraViewModelFactory
import kotlinx.android.synthetic.main.fragment_crear_jugador.*
import kotlinx.android.synthetic.main.fragment_crear_jugador.view.*


class CrearAventuraFragment : Fragment() {

    private val args by navArgs<CrearAventuraFragmentArgs>()
    private lateinit var mAventuraViewModel: AventuraViewModel
    private lateinit var mAventuraViewModelFactory: AventuraViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_crear_aventura, container, false)

        mAventuraViewModelFactory = AventuraViewModelFactory(args.jugadorActual.id)
        mAventuraViewModel = ViewModelProvider(this,mAventuraViewModelFactory).get(AventuraViewModel::class.java)

        view.button.setOnClickListener {

            insertarDatos()
        }
        return view
    }
    private fun insertarDatos() {

        val nombre = nombre_et.text.toString()

        if (comprobarCampos(nombre)) {
            if(comprobarNombre(nombre))
            {
                Log.e("ID Jugador Actual", args.jugadorActual.id.toString())
                val aventura = Aventura(0,args.jugadorActual.id,nombre,"Premisa sin hacer", 5)
                mAventuraViewModel.crearAventura(aventura)
                Toast.makeText(requireContext(), "Aventura creada", Toast.LENGTH_LONG).show()

                val action = CrearAventuraFragmentDirections.actionCrearAventuraFragmentToJugadorSeleccionadoFragment(args.jugadorActual)
                findNavController().navigate(action)
            } else {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Nombre de Aventura incorrecta")
                builder.setMessage("Debes crear una Aventura con otro nombre. Esa ya está en uso.")
                builder.create().show()

            }


        } else {
            Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show()
        }

    }

    //Comprueba si todos los campos del formulario están llenos
    private fun comprobarCampos(nombre: String): Boolean {
        return !(TextUtils.isEmpty(nombre))
    }
    private fun comprobarNombre(nombre: String): Boolean{
        val listaAventurasAdapter = ListaAventurasAdapter(args.jugadorActual.id)

        if (listaAventurasAdapter.getItemCount() != 0) {
            Log.e("Lista Aventuras Jugador tamaño:", "Distinto de cero")
            for (aventura in listaAventurasAdapter.obtenerArrayAventuras()){
                Log.e("NOMBRE", aventura.nombre)
                if(aventura.nombre == nombre){
                    return false
                }
            }
        }
        Log.e("COMPROBAR NOMBRE", "Lista nula")
        return true
    }

}