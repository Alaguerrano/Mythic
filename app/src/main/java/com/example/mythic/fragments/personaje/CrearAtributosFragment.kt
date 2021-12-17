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
        val mFuerzaSpinner = view.rango_fuerza_sp
        val mAgilidadSpinner  = view.rango_agilidad_sp
        val mReflejosSpinner = view.rango_reflejos_sp
        val mInteligenciaSpinner = view.rango_inteligencia_sp
        val mIntuicionSpinner = view.rango_intuicion_sp
        val mFuerzaVoluntadSpinner = view.rango_fuerza_voluntad_sp
        val mFortalezaSpinner = view.rango_fortaleza_sp

        val rangoAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, rango.valor )

        mFuerzaSpinner.adapter = rangoAdapter
        mFuerzaSpinner.setSelection(5)

        mAgilidadSpinner.adapter = rangoAdapter
        mAgilidadSpinner.setSelection(5)

        mReflejosSpinner.adapter = rangoAdapter
        mReflejosSpinner.setSelection(5)

        mInteligenciaSpinner.adapter = rangoAdapter
        mInteligenciaSpinner.setSelection(5)

        mIntuicionSpinner.adapter = rangoAdapter
        mIntuicionSpinner.setSelection(5)

        mFuerzaVoluntadSpinner.adapter = rangoAdapter
        mIntuicionSpinner.setSelection(5)

        mFortalezaSpinner.adapter = rangoAdapter
        mFortalezaSpinner.setSelection(5)

        return view
    }


}