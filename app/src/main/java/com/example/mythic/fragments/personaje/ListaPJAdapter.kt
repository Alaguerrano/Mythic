package com.example.mythic.fragments.personaje

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mythic.R

import com.example.mythic.fragments.personaje.ListaPJAdapter

import com.example.mythic.model.Personaje
import kotlinx.android.synthetic.main.fila_lista_jugador.view.*

class ListaPJAdapter(idJugador : Int): RecyclerView.Adapter<ListaPJAdapter.MiViewHolder>(){
    //Todas las aventuras
    private var PJsLista = emptyList<Personaje>()


    class MiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        return MiViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fila_lista_pj, parent, false))
    }

    override fun getItemCount(): Int {
        return PJsLista.size
    }



    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val PJactual = PJsLista[position]
        holder.itemView.nombre_textView.text = PJactual.nombre


        //Si pulso en un PJ para actualizarlo
        holder.itemView.filaLayout.setOnClickListener{


        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun establecerDatos(pj: List<Personaje>){
        this.PJsLista = pj
        this.notifyDataSetChanged()
    }

    fun obtenerArrayPJs(): Array<Personaje>{
        return this.PJsLista.toTypedArray()
    }
}