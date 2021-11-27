package com.example.mythic.fragments.insertar_partida

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
import com.example.mythic.model.Partida
import com.example.mythic.data.PartidaViewModel
import kotlinx.android.synthetic.main.fragment_insertar_partida.*
import kotlinx.android.synthetic.main.fragment_insertar_partida.view.*


class InsertarPartidaFragment : Fragment() {

    private lateinit var mPartidaViewModel : PartidaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_insertar_partida, container, false)
        mPartidaViewModel = ViewModelProvider(this).get(PartidaViewModel::class.java)
        view.button.setOnClickListener {
            insertarDatos()
        }
        return view
    }
    private fun insertarDatos(){
        val nombre = nombre_et.text.toString()
        val masterHumano = master_humano_sw.isChecked()
        val multijugador = multijugador_sw.isChecked()
        val motorDistintoMythic = motorDistintoMythic_sw.isChecked()
        if (comprobarCampos(nombre)){
            if(comprobarNombre(nombre)) {
                val partida = Partida(0, nombre, masterHumano, multijugador, motorDistintoMythic)
                mPartidaViewModel.insertarPartida(partida)
                Toast.makeText(requireContext(), "Partida creada", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_insertarPartidaFragment_to_listaPartidasFragment)
            }
            else{
                Toast.makeText(requireContext(), "Debes elegir otro nombre de partida", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show()
        }

    }
    //Comprueba si todos los campos del formulario están llenos
    private fun comprobarCampos(nombre : String) : Boolean{
        return !(TextUtils.isEmpty(nombre))
    }

    private fun comprobarNombre(nombre: String) : Boolean{
        return true
    }
}