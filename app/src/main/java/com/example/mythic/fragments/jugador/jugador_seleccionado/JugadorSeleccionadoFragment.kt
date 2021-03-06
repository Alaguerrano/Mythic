package com.example.mythic.fragments.jugador.jugador_seleccionado

import android.os.Bundle
import android.text.Html
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mythic.R
import com.example.mythic.fragments.aventura.lista_aventuras.ListaAventurasAdapter
import com.example.mythic.model.Master
import com.example.mythic.model.MotorJuego
import com.example.mythic.model.NumeroJugadores
import kotlinx.android.synthetic.main.fragment_jugador_seleccionado.view.*
import kotlinx.android.synthetic.main.fragment_jugador_seleccionado.view.aventuras_bt


class JugadorSeleccionadoFragment : Fragment() {



    private val args by navArgs<JugadorSeleccionadoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_jugador_seleccionado, container, false)






        view.nombre_jugador.setText(Html.fromHtml("<b>Nombre del Jugador:</b> " + args.jugadorActual.nombre))
        view.tipo_master.setText(Html.fromHtml("<b>Master:</b> " + Master().valor[args.jugadorActual.tipoMaster]    ))
        view.numero_jugadores.setText(Html.fromHtml("<b>Número jugadores:</b> " + NumeroJugadores().valor[args.jugadorActual.numeroJugadores]))
        view.motor_juego.setText(Html.fromHtml("<b>Motor de juego:</b> " + MotorJuego().valor[args.jugadorActual.motorJuego]))



        setHasOptionsMenu(true)

        //********************************************************************
        //Cuando pulsas el botón Aventuras, ir a Lista de Aventuras
        //*********************************************************************
        view.aventuras_bt.setOnClickListener{
            val action = JugadorSeleccionadoFragmentDirections.actionJugadorSeleccionadoFragmentToListaAventurasFragment(args.jugadorActual)
            findNavController().navigate(action)

        }

        view.PJs_bt.setOnClickListener{
            val action = JugadorSeleccionadoFragmentDirections.actionJugadorSeleccionadoFragmentToListaPJFragment(args.jugadorActual)
            findNavController().navigate(action)
        }


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