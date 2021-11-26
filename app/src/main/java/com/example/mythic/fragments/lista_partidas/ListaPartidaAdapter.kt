package com.example.mythic.fragments.lista_partidas


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mythic.R
import com.example.mythic.data.Partida
import kotlinx.android.synthetic.main.fila_lista_partida.view.*

class ListaPartidaAdapter: RecyclerView.Adapter<ListaPartidaAdapter.MiViewHolder>(){

    private var partidasLista = emptyList<Partida>()

    class MiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        return MiViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fila_lista_partida, parent, false))
    }

    override fun getItemCount(): Int {
        return partidasLista.size
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val partidaActual = partidasLista[position]
        holder.itemView.nombre_textView.text = partidaActual.nombre
    }


    @SuppressLint("NotifyDataSetChanged")
    fun establecerDatos(partida: List<Partida>){
        this.partidasLista = partida
        this.notifyDataSetChanged()
    }
}