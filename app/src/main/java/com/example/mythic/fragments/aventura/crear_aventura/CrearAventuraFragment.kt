package com.example.mythic.fragments.aventura.crear_aventura

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mythic.R
import com.example.mythic.model.Aventura
import com.example.mythic.viewmodel.AventuraViewModel
import com.example.mythic.viewmodel.AventuraViewModelFactory
import kotlinx.android.synthetic.main.fragment_crear_aventura.*
import kotlinx.android.synthetic.main.fragment_crear_jugador.nombre_et
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

        mAventuraViewModelFactory = AventuraViewModelFactory(activity?.application!!, args.jugadorActual.id)
        mAventuraViewModel = ViewModelProvider(this,mAventuraViewModelFactory).get(AventuraViewModel::class.java)


        view.button.setOnClickListener {

            insertarDatos()
        }
        return view
    }
    private fun insertarDatos() {

        val nombre = nombre_et.text.toString()
        val premisa = premisa_et.text.toString()

        if (comprobarCampos(nombre, premisa)) {
            if(comprobarNombre(nombre))
            {
                Log.e("ID Jugador Actual", args.jugadorActual.id.toString())
                val aventura = Aventura(0,args.jugadorActual.id,nombre,premisa, 5)
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
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Campos por rellenar")
            builder.setMessage("Debes rellenar todos los campos para crear la Aventura.")
            builder.create().show()
        }

    }

    //Comprueba si todos los campos del formulario están llenos
    private fun comprobarCampos(nombre: String, premisa: String): Boolean {
        return !(TextUtils.isEmpty(nombre))&&!(TextUtils.isEmpty(premisa))
    }
    private fun comprobarNombre(nombre: String): Boolean{



        if (args.listaAventuras.size != 0) {

            for (aventura in args.listaAventuras){
                Log.e("NOMBRE", aventura.nombre)
                if(aventura.nombre == nombre){
                    return false
                }
            }
        }else
        {
            Log.e("COMPROBAR NOMBRE", "Lista nula")
        }

        return true
    }

}