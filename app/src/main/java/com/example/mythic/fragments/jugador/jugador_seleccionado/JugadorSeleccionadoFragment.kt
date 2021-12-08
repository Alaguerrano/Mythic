package com.example.mythic.fragments.jugador.jugador_seleccionado

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.text.Html
import android.util.Log
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
import com.example.mythic.viewmodel.AventuraViewModelFactory
import com.example.mythic.viewmodel.JugadorViewModel
import kotlinx.android.synthetic.main.fragment_jugador_seleccionado.view.*
import kotlinx.android.synthetic.main.fragment_jugador_seleccionado.view.floatingActionButton
import kotlinx.android.synthetic.main.fragment_lista_jugadores.view.*


class JugadorSeleccionadoFragment : Fragment() {


    private lateinit var mAventuraViewModel: AventuraViewModel
    private lateinit var mAventuraViewModelFactory: AventuraViewModelFactory
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


        mAventuraViewModelFactory = AventuraViewModelFactory(activity?.application!!, args.jugadorActual.id)
        mAventuraViewModel = ViewModelProvider(this,mAventuraViewModelFactory).get(AventuraViewModel::class.java)


        view.nombre_jugador.setText(Html.fromHtml("<b>Nombre del Jugador:</b> " + args.jugadorActual.nombre))
        if(args.jugadorActual.masterHumano == true){
            view.tipo_master.setText(Html.fromHtml("<b>Master:</b> Humano"))
        }else{
            view.tipo_master.setText(Html.fromHtml("<b>Master:</b> Mythic"))
        }
        if(args.jugadorActual.multijugador == true){
            view.numero_jugadores.setText(Html.fromHtml("<b>Número jugadores:</b> Multijugador"))
        }else{
            view.numero_jugadores.setText(Html.fromHtml("<b>Número jugadores:</b> Solitario"))
        }
        if(args.jugadorActual.motorDistintoMythic == true){
            view.motor_juego.setText(Html.fromHtml("<b>Motor de juego:</b> Distinto a Mythic"))
        }else{
            view.motor_juego.setText(Html.fromHtml("<b>Motor de juego:</b> Mythic"))
        }

        mAventuraViewModel.listaAventuras.observe(viewLifecycleOwner, Observer { aventura ->
            listaAventurasAdapter.establecerDatos(aventura)

            //************************************************************************************
            //Si la lista de Aventuras esta vacia, decir al usuario que cree una aventura
            //*******************************************************************************

            Log.e("AVENTURAS JUGADOR:",mAventuraViewModel.listaAventuras.value?.size.toString())
            //Comprobar si hay alguna aventura creada con la id del Jugador
            if(listaAventurasAdapter.getItemCount() == 0){
                //Mostrar mensaje de Crear Aventura
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Tu Jugador no tiene ninguna Aventura creada.")
                builder.setMessage("Debes crear al menos una aventura en tu Perfil de Jugador, para poder jugar.")
                builder.create().show()
                //Mandarlo a pantalla crear una Aventura
                val action = JugadorSeleccionadoFragmentDirections.actionJugadorSeleccionadoFragmentToCrearAventuraFragment(args.jugadorActual, listaAventurasAdapter.obtenerArrayAventuras())
                findNavController().navigate(action)
            }


        })
        setHasOptionsMenu(true)

        //********************************************************************
        //Cuando pulsas el botón + te manda a Crear una nueva Aventura
        //*********************************************************************
        view.floatingActionButton.setOnClickListener{
            val action = JugadorSeleccionadoFragmentDirections.actionJugadorSeleccionadoFragmentToCrearAventuraFragment(args.jugadorActual, listaAventurasAdapter.obtenerArrayAventuras())
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