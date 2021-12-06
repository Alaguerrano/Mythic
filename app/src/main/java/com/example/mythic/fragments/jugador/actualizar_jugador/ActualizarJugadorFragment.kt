package com.example.mythic.fragments.jugador.actualizar_jugador

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mythic.R
import com.example.mythic.fragments.aventura.ListaAventurasAdapter
import com.example.mythic.model.Jugador
import com.example.mythic.viewmodel.AventuraViewModel
import com.example.mythic.viewmodel.JugadorViewModel
import kotlinx.android.synthetic.main.fragment_actualizar_jugador.*
import kotlinx.android.synthetic.main.fragment_actualizar_jugador.view.*


class ActualizarJugadorFragment : Fragment() {

    private lateinit var mJugadorViewModel: JugadorViewModel
    private lateinit var mAventuraViewModel: AventuraViewModel

    private val args by navArgs<ActualizarJugadorFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_actualizar_jugador, container, false)



        mJugadorViewModel = ViewModelProvider(this).get(JugadorViewModel::class.java)
        mAventuraViewModel = ViewModelProvider(this).get(AventuraViewModel::class.java)

        view.actualizar_nombre_et.setText(args.jugadorActual.nombre)
        view.actualizar_master_humano_sw.setChecked(args.jugadorActual.masterHumano)
        view.actualizar_multijugador_sw.setChecked(args.jugadorActual.multijugador)
        view.actualizar_motorDistintoMythic_sw.setChecked(args.jugadorActual.motorDistintoMythic)

        view.button.setOnClickListener {
            actualizarJugador()
        }

        setHasOptionsMenu(true)
        return view
    }

    private fun actualizarJugador(){
        val nombre = actualizar_nombre_et.text.toString()
        val masterHumano = actualizar_master_humano_sw.isChecked()
        val multijugador = actualizar_multijugador_sw.isChecked()
        val motorDistintoMythic = actualizar_motorDistintoMythic_sw.isChecked()
        if(comprobarCampos(nombre)){
            val jugadorActualizado = Jugador(args.jugadorActual.id,nombre,masterHumano, multijugador,motorDistintoMythic)
            mJugadorViewModel.actualizarJugador(jugadorActualizado)
            Toast.makeText(requireContext(), "Perfil de Jugador actualizado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_actualizarJugadorFragment_to_listaJugadoresFragment)
        }
        else
        {
            Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun comprobarCampos(nombre: String): Boolean {
        return !(TextUtils.isEmpty(nombre))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.borrar_jugador_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.borrar_menu) {
            borrarJugador()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun borrarJugador() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Si"){_,_ ->
            mJugadorViewModel.borrarJugador(args.jugadorActual)
            val listaAventurasAdapter = ListaAventurasAdapter(args.jugadorActual.id)
            val listaAventurasJugadorSeleccionado = listaAventurasAdapter.obtenerlistaAventurasJugadorSeleccionado()
            mAventuraViewModel.borrarAventuras(listaAventurasJugadorSeleccionado)
            Toast.makeText(requireContext(), "Perfil de Jugador borrado: ${args.jugadorActual.nombre}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_actualizarJugadorFragment_to_listaJugadoresFragment)

        }
        builder.setNegativeButton("No"){
         _, _  ->
        }
        builder.setTitle("Borrar ${args.jugadorActual.nombre}")
        builder.setMessage("¿Estás seguro que quieres borrar ${args.jugadorActual.nombre} ?")
        builder.create().show()
    }

}