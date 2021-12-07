package com.example.mythic.fragments.jugador.jugador_seleccionado

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mythic.R
import com.example.mythic.fragments.aventura.ListaAventurasAdapter
import com.example.mythic.fragments.jugador.actualizar_jugador.ActualizarJugadorFragmentArgs
import com.example.mythic.fragments.jugador.lista_jugadores.ListaJugadoresFragmentDirections
import com.example.mythic.model.Aventura
import com.example.mythic.viewmodel.AventuraViewModel
import com.example.mythic.viewmodel.JugadorViewModel
import kotlinx.android.synthetic.main.fragment_jugador_seleccionado.view.*
import kotlinx.android.synthetic.main.fragment_jugador_seleccionado.view.floatingActionButton
import kotlinx.android.synthetic.main.fragment_lista_jugadores.view.*


class JugadorSeleccionadoFragment : Fragment() {

    private lateinit var mJugadorViewModel: JugadorViewModel
    private lateinit var mAventuraViewModel: AventuraViewModel
    private val args by navArgs<JugadorSeleccionadoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_jugador_seleccionado, container, false)

        val listaAventurasAdapter = ListaAventurasAdapter(args.jugadorActual.id)
        val recyclerView = view.lista_aventuras_rv
        recyclerView.adapter = listaAventurasAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())



        mAventuraViewModel = AventuraViewModel(Application(),args.jugadorActual.id)


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

        mAventuraViewModel.listaAventuras.observe(viewLifecycleOwner, Observer { aventura ->
            listaAventurasAdapter.establecerDatos(aventura)
            //************************************************************************************
            //Si la lista de Aventuras esta vacia, decir al usuario que cree una aventura
            //*******************************************************************************


            //Comprobar si hay alguna aventura creada con la id del Jugador
            if(listaAventurasAdapter.getItemCount() == 0){
                //Mostrar mensaje de Crear Aventura
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Tu Jugador no tiene ninguna Aventura creada.")
                builder.setMessage("Debes crear al menos una aventura en tu Perfil de Jugador, para poder jugar.")
                builder.create().show()
                //Mandarlo a pantalla crear una Aventura
                val action = JugadorSeleccionadoFragmentDirections.actionJugadorSeleccionadoFragmentToCrearAventuraFragment(args.jugadorActual)
                findNavController().navigate(action)
            }


        })
        setHasOptionsMenu(true)

        //********************************************************************
        //Cuando pulsas el botón + te manda a Crear una nueva Aventura
        //*********************************************************************
        view.floatingActionButton.setOnClickListener{
            val action = JugadorSeleccionadoFragmentDirections.actionJugadorSeleccionadoFragmentToCrearAventuraFragment(args.jugadorActual)
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