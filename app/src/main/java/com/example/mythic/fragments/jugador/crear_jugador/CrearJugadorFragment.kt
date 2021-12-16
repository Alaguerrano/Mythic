package com.example.mythic.fragments.jugador.crear_jugador

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mythic.R
import com.example.mythic.fragments.jugador.actualizar_jugador.ActualizarJugadorFragmentArgs
import com.example.mythic.model.Jugador
import com.example.mythic.model.Master
import com.example.mythic.model.MotorJuego
import com.example.mythic.model.Rango
import com.example.mythic.repository.JugadorRepository
import com.example.mythic.viewmodel.AventuraViewModel
import com.example.mythic.viewmodel.JugadorViewModel
import kotlinx.android.synthetic.main.fragment_crear_atributos.view.*
import kotlinx.android.synthetic.main.fragment_crear_jugador.*
import kotlinx.android.synthetic.main.fragment_crear_jugador.view.*


class CrearJugadorFragment : Fragment() {

    private lateinit var mJugadorViewModel: JugadorViewModel
    private lateinit var mAventuraViewModel: AventuraViewModel

    private val args by navArgs<CrearJugadorFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_crear_jugador, container, false)

        val master = Master()
        val motorJuego = MotorJuego()
        val mTipoMasterSpinner = view.tipo_master_sp
        val mNumeroJugadoresSpinner  = view.numero_jugadores_sp
        val mMotorJuegoSpinner = view.motor_juego_sp

        val masterAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, master.valor )
        val motorJuegoAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, motorJuego.valor )
       mTipoMasterSpinner.adapter = masterAdapter
       mMotorJuegoSpinner.adapter = motorJuegoAdapter



        mJugadorViewModel = ViewModelProvider(this).get(JugadorViewModel::class.java)




        view.button.setOnClickListener {

            insertarDatos()
        }
        return view
    }

    private fun insertarDatos() {

        val nombre = nombre_et.text.toString()
        val tipoMaster = tipo_master_sp.selectedItemPosition
        val numeroJugadores = numero_jugadores_sp.selectedItemPosition
        val motorJuego = motor_juego_sp.selectedItemPosition
        if (comprobarCampos(nombre)) {
            if(comprobarNombre(nombre))
            {
                val jugador = Jugador(0, nombre,tipoMaster,numeroJugadores, motorJuego)
                mJugadorViewModel.crearJugador(jugador)
                Toast.makeText(requireContext(), "Perfil de Jugador creado", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_crearJugadorFragment_to_listaJugadoresFragment)
            } else {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Nombre de Perfil de Jugador incorrecto")
                builder.setMessage("Debes crear un Perfil de Jugador con otro nombre. Ese ya está en uso.")
                builder.create().show()

            }


        } else {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Campo de nombre vacio")
            builder.setMessage("Debes rellenar todos los campos para crear un nuevo perfil.")
            builder.create().show()
        }

    }

    //Comprueba si todos los campos del formulario están llenos
    private fun comprobarCampos(nombre: String): Boolean {
        return !(TextUtils.isEmpty(nombre))
    }

    private fun comprobarNombre(nombre: String): Boolean{

        val listaJugadores  = args.listaJugadores
        if (listaJugadores != null) {
            Log.e("COMPROBAR NOMBRE", "Lista no nula")
            for (jugador in listaJugadores){
                if(jugador.nombre == nombre){
                    return false
                }
            }
        }
        Log.e("COMPROBAR NOMBRE", "Lista nula")
        return true
    }


}