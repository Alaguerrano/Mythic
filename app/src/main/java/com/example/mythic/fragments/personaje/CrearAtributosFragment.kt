package com.example.mythic.fragments.personaje

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.mythic.R
import com.example.mythic.model.Rango
import kotlinx.android.synthetic.main.fragment_crear_atributos.view.*


class CrearAtributosFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_crear_atributos, container, false)
        val rango = Rango()
        val mSpinner = view.rango_fuerza_sp
        val rangoAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, rango.valor )
        mSpinner.adapter = rangoAdapter
        return view
    }


}