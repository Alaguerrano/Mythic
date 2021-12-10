package com.example.mythic.fragments.aventura.aventura_seleccionada

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.mythic.R
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
        return view
    }


}