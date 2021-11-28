package com.example.mythic.fragments.actualizar_partida

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mythic.R
import com.example.mythic.model.Partida
import com.example.mythic.viewmodel.PartidaViewModel
import kotlinx.android.synthetic.main.fragment_actualizar_partida.*
import kotlinx.android.synthetic.main.fragment_actualizar_partida.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ActualizarPartidaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActualizarPartidaFragment : Fragment() {

    private lateinit var mPartidaViewModel: PartidaViewModel

    private val args by navArgs<ActualizarPartidaFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_actualizar_partida, container, false)

        mPartidaViewModel = ViewModelProvider(this).get(PartidaViewModel::class.java)

        view.actualizar_nombre_et.setText(args.partidaActual.nombre)
        view.actualizar_master_humano_sw.setChecked(args.partidaActual.masterHumano)
        view.actualizar_multijugador_sw.setChecked(args.partidaActual.multijugador)
        view.actualizar_motorDistintoMythic_sw.setChecked(args.partidaActual.motorDistintoMythic)

        view.button.setOnClickListener {
            actualizarPartida()
        }
        return view
    }

    private fun actualizarPartida(){
        val nombre = actualizar_nombre_et.text.toString()
        val masterHumano = actualizar_master_humano_sw.isChecked()
        val multijugador = actualizar_multijugador_sw.isChecked()
        val motorDistintoMythic = actualizar_motorDistintoMythic_sw.isChecked()
        if(comprobarCampos(nombre)){
            val partidaActualizada = Partida(args.partidaActual.id,nombre,masterHumano, multijugador,motorDistintoMythic)
            mPartidaViewModel.actualizarPartida(partidaActualizada)
            Toast.makeText(requireContext(), "Partida actualizada", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_actualizarPartidaFragment_to_listaPartidasFragment)
        }
        else
        {
            Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun comprobarCampos(nombre: String): Boolean {
        return !(TextUtils.isEmpty(nombre))
    }

}