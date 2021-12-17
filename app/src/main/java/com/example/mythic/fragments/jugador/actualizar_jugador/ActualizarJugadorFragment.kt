package com.example.mythic.fragments.jugador.actualizar_jugador

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mythic.R
import com.example.mythic.fragments.aventura.lista_aventuras.ListaAventurasAdapter
import com.example.mythic.model.Jugador
import com.example.mythic.model.Master
import com.example.mythic.model.MotorJuego
import com.example.mythic.model.NumeroJugadores
import com.example.mythic.viewmodel.AventuraViewModel
import com.example.mythic.viewmodel.AventuraViewModelFactory
import com.example.mythic.viewmodel.JugadorViewModel
import kotlinx.android.synthetic.main.fragment_actualizar_jugador.*
import kotlinx.android.synthetic.main.fragment_actualizar_jugador.view.*
import kotlinx.android.synthetic.main.fragment_actualizar_jugador.view.button
import kotlinx.android.synthetic.main.fragment_actualizar_jugador.view.motor_juego_sp
import kotlinx.android.synthetic.main.fragment_actualizar_jugador.view.numero_jugadores_sp
import kotlinx.android.synthetic.main.fragment_actualizar_jugador.view.tipo_master_sp
import kotlinx.android.synthetic.main.fragment_crear_jugador.view.*


class ActualizarJugadorFragment : Fragment() {

    private lateinit var mJugadorViewModel: JugadorViewModel
    private lateinit var mAventuraViewModel: AventuraViewModel
    private lateinit var mAventuraViewModelFactory: AventuraViewModelFactory

    private val args by navArgs<ActualizarJugadorFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_actualizar_jugador, container, false)

        val master = Master()
        val numeroJugadores = NumeroJugadores()
        val motorJuego = MotorJuego()

        val mTipoMasterSpinner = view.tipo_master_sp
        val mNumeroJugadoresSpinner  = view.numero_jugadores_sp
        val mMotorJuegoSpinner = view.motor_juego_sp

        val masterAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, master.valor )
        val numeroJugadoresAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, numeroJugadores.valor )
        val motorJuegoAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, motorJuego.valor )

        mTipoMasterSpinner.adapter = masterAdapter
        mNumeroJugadoresSpinner.adapter = numeroJugadoresAdapter
        mMotorJuegoSpinner.adapter = motorJuegoAdapter

        mJugadorViewModel = ViewModelProvider(this).get(JugadorViewModel::class.java)

        mAventuraViewModelFactory = AventuraViewModelFactory(activity?.application!!, args.jugadorActual.id)
        mAventuraViewModel = ViewModelProvider(this,mAventuraViewModelFactory).get(AventuraViewModel::class.java)

        view.actualizar_nombre_et.setText(args.jugadorActual.nombre)
        view.tipo_master_sp.setSelection(args.jugadorActual.tipoMaster)
        view.numero_jugadores_sp.setSelection(args.jugadorActual.numeroJugadores)
        view.motor_juego_sp.setSelection(args.jugadorActual.motorJuego)


        view.button.setOnClickListener {
            actualizarJugador()
        }

        setHasOptionsMenu(true)
        return view
    }

    private fun actualizarJugador(){
        val nombre = actualizar_nombre_et.text.toString()
        val tipoMaster = tipo_master_sp.selectedItemPosition
        val numeroJugadores = numero_jugadores_sp.selectedItemPosition + 1
        val motorJuego = motor_juego_sp.selectedItemPosition
        if(comprobarCampos(nombre)){
            val jugadorActualizado = Jugador(args.jugadorActual.id,nombre,tipoMaster, numeroJugadores,motorJuego)
            mJugadorViewModel.actualizarJugador(jugadorActualizado)
            Toast.makeText(requireContext(), "Perfil de Jugador actualizado", Toast.LENGTH_LONG).show()
            val action = ActualizarJugadorFragmentDirections.actionActualizarJugadorFragmentToListaJugadoresFragment()
            findNavController().navigate(action)
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

            mAventuraViewModel.borrarAventurasJugador()
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