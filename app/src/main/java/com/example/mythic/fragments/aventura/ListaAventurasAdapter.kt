package com.example.mythic.fragments.aventura

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.mythic.R

import com.example.mythic.model.Aventura

import kotlinx.android.synthetic.main.fila_lista_jugador.view.filaLayout
import kotlinx.android.synthetic.main.fila_lista_jugador.view.nombre_textView

class ListaAventurasAdapter(idJugador : Int): RecyclerView.Adapter<ListaAventurasAdapter.MiViewHolder>(){

    //Todas las aventuras
    private var aventurasLista = emptyList<Aventura>()
    private var idJugadorSeleccionado = idJugador

    class MiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        return MiViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fila_lista_aventura, parent, false))
    }

    override fun getItemCount(): Int {
        return aventurasLista.size
    }



    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val aventuraActual = aventurasLista[position]
        holder.itemView.nombre_textView.text = aventuraActual.nombre


        //Si pulso en una aventura para actualizarla
        holder.itemView.filaLayout.setOnClickListener{
           //TODO
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun establecerDatos(aventura: List<Aventura>){
        this.aventurasLista = aventura
        this.notifyDataSetChanged()
    }

    fun obtenerArrayAventuras(): Array<Aventura>{
        return this.aventurasLista.toTypedArray()
    }

   

}