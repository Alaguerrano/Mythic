package com.example.mythic.fragments.jugador.jugador_seleccionado

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mythic.R
import com.example.mythic.fragments.jugador.lista_jugadores.ListaJugadoresAdapter
import com.example.mythic.fragments.jugador.lista_jugadores.ListaJugadoresFragmentDirections
import com.example.mythic.model.Aventura
import com.example.mythic.model.Jugador
import kotlinx.android.synthetic.main.fila_lista_aventura.view.*
import kotlinx.android.synthetic.main.fila_lista_jugador.view.*
import kotlinx.android.synthetic.main.fila_lista_jugador.view.filaLayout
import kotlinx.android.synthetic.main.fila_lista_jugador.view.nombre_textView

class ListaAventurasAdapter: RecyclerView.Adapter<ListaAventurasAdapter.MiViewHolder>(){

    private var aventurasLista = emptyList<Aventura>()

    class MiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        return MiViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fila_lista_aventura, parent, false))
    }

    override fun getItemCount(): Int {
        return aventurasLista.size
    }



    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val aventuraActual = aventurasLista[position]
        holder.itemView.nombre_textView.text = aventurasLista.nombre


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