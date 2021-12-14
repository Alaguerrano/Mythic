package com.example.mythic.fragments.aventura.aventura_seleccionada

import android.os.Bundle
import android.text.Html
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mythic.R
import com.example.mythic.fragments.jugador.jugador_seleccionado.JugadorSeleccionadoFragmentDirections
import kotlinx.android.synthetic.main.fragment_aventura_seleccionada.view.*


class AventuraSeleccionadaFragment : Fragment() {

    private val args by navArgs<AventuraSeleccionadaFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_aventura_seleccionada, container, false)
        view.nombre_aventura.setText(Html.fromHtml("<b>Nombre de la Aventura:</b> " + args.aventuraSeleccionada.nombre))
        view.premisa_aventura.setText(Html.fromHtml("<b>Premisa de la Aventura:</b> " + args.aventuraSeleccionada.premisa))
        view.caos_aventura.setText(Html.fromHtml("<b>Caos de la Aventura:</b> " + args.aventuraSeleccionada.caos))

        setHasOptionsMenu(true)

        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.editar_aventura_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.editar_menu) {
            editarAventura()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editarAventura() {
        val action = AventuraSeleccionadaFragmentDirections.actionAventuraSeleccionadaFragmentToActualziarAventuraFragment()
        findNavController().navigate(action)

    }


}