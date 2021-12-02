package com.example.mythic.fragments.crear_jugador

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
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.mythic.R
import com.example.mythic.data.jugador.JugadorBD
import com.example.mythic.data.jugador.JugadorDao
import com.example.mythic.fragments.lista_jugadores.ListaJugadoresAdapter
import com.example.mythic.model.Jugador
import com.example.mythic.repository.JugadorRepository
import com.example.mythic.viewmodel.JugadorViewModel
import kotlinx.android.synthetic.main.fragment_crear_jugador.*
import kotlinx.android.synthetic.main.fragment_crear_jugador.view.*
import kotlinx.coroutines.*


class CrearJugadorFragment : Fragment() {

    private lateinit var mJugadorViewModel: JugadorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_crear_jugador, container, false)

        mJugadorViewModel = ViewModelProvider(this).get(JugadorViewModel::class.java)
        view.button.setOnClickListener {

            mJugadorViewModel.viewModelScope.launch(Dispatchers.IO) {
                contarJugadoresConNombreIgual()

                Log.e("VALUE:", mJugadorViewModel.numeroJugadoresIgualNombre.value.toString())

            }
            if(mJugadorViewModel.numeroJugadoresIgualNombre.value == 0){
                Log.e("INSERTO DATOS", "Insertando")
                insertarDatos()
            }else{
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Perfil de Jugador usado")
                builder.setMessage("No puede haber dos Perfiles de Jugador con el mismo nombre. Elige otro nombre.")
                builder.create().show()
            }

        }
        return view
    }
    private suspend fun contarJugadoresConNombreIgual(){
        mJugadorViewModel.contarJugadoresConNombreIgualA(nombre_et.text.toString())
    }
    private fun insertarDatos() {



                val nombre = nombre_et.text.toString()
                val masterHumano = master_humano_sw.isChecked()
                val multijugador = multijugador_sw.isChecked()
                val motorDistintoMythic = motorDistintoMythic_sw.isChecked()
                if (comprobarCampos(nombre)) {

                    val jugador = Jugador(0, nombre, masterHumano, multijugador, motorDistintoMythic)
                    mJugadorViewModel.crearJugador(jugador)
                    Toast.makeText(requireContext(), "Perfil de Jugador creado", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_crearJugadorFragment_to_listaJugadoresFragment)

                } else {
                    Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show()
                }






    }

    //Comprueba si todos los campos del formulario están llenos
    private fun comprobarCampos(nombre: String): Boolean {
        return !(TextUtils.isEmpty(nombre))
    }



}