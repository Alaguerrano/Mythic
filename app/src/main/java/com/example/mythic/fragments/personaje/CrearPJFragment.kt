package com.example.mythic.fragments.personaje

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
import com.example.mythic.fragments.aventura.crear_aventura.CrearAventuraFragmentArgs
import com.example.mythic.fragments.aventura.crear_aventura.CrearAventuraFragmentDirections
import com.example.mythic.model.Aventura
import com.example.mythic.model.Personaje
import com.example.mythic.viewmodel.AventuraViewModel
import com.example.mythic.viewmodel.AventuraViewModelFactory
import com.example.mythic.viewmodel.PJViewModel
import com.example.mythic.viewmodel.PJViewModelFactory
import kotlinx.android.synthetic.main.fragment_crear_aventura.*
import kotlinx.android.synthetic.main.fragment_crear_jugador.*
import kotlinx.android.synthetic.main.fragment_crear_jugador.nombre_et
import kotlinx.android.synthetic.main.fragment_crear_jugador.view.*
import kotlinx.android.synthetic.main.fragment_crear_pj.*


class CrearPJFragment : Fragment() {

    private val args by navArgs<CrearPJFragmentArgs>()
    private lateinit var mPJViewModel: PJViewModel
    private lateinit var mPJViewModelFactory: PJViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_crear_pj, container, false)
        mPJViewModelFactory = PJViewModelFactory(activity?.application!!, args.jugadorActual.id)
        mPJViewModel = ViewModelProvider(this,mPJViewModelFactory).get(PJViewModel::class.java)

        view.button.setOnClickListener {

            crearPJ()
        }
        return view
    }
    private fun crearPJ() {
        val nombre = nombre_et.text.toString()
        val resumen = resumen_et.text.toString()
        val esPJPuntos = pj_puntos_sw.isChecked()

        if (comprobarCampos(nombre, resumen)) {
            if(comprobarNombre(nombre))
            {
                Log.e("ID Jugador Actual", args.jugadorActual.id.toString())

                val pj = Personaje(0,args.jugadorActual.id,-1,false, esPJPuntos,nombre,resumen,50)
                mPJViewModel.crearPJ(pj)

                if(esPJPuntos == true){
                    //Enviar a atributos de PJ Puntos
                }else{
                    //Enviar a atributos de PJ libre
                }



            } else {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Nombre de PJ incorrecto")
                builder.setMessage("Debes crear un PJ con otro nombre. Ese ya está en uso.")
                builder.create().show()

            }


        } else {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Campos por rellenar")
            builder.setMessage("Debes rellenar todos los campos para crear el PJ.")
            builder.create().show()
        }
    }

    private fun comprobarCampos(nombre: String, resumen: String): Boolean {
        return !(TextUtils.isEmpty(nombre))&&!(TextUtils.isEmpty(resumen))
    }
    private fun comprobarNombre(nombre: String): Boolean{


        return true

    }

}