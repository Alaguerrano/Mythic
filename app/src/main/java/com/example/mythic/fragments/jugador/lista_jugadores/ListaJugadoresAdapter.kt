package com.example.mythic.fragments.jugador.lista_jugadores


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mythic.R
import com.example.mythic.model.Aventura
import com.example.mythic.model.Jugador
import kotlinx.android.synthetic.main.fila_lista_jugador.view.*

class ListaJugadoresAdapter: RecyclerView.Adapter<ListaJugadoresAdapter.MiViewHolder>(){


    private var jugadoresLista = emptyList<Jugador>()



    class MiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        return MiViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fila_lista_jugador, parent, false))
    }

    override fun getItemCount(): Int {
        return jugadoresLista.size
    }



    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val jugadorActual = jugadoresLista[position]

        holder.itemView.nombre_textView.text = jugadorActual.nombre


        //Si pulso en un jugador para actualizarlo
        holder.itemView.filaLayout.setOnClickListener{
            val action = ListaJugadoresFragmentDirections.actionListaJugadoresFragmentToJugadorSeleccionadoFragment(jugadorActual)
            holder.itemView.findNavController().navigate(action)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun establecerDatos(jugadores: List<Jugador>){
        this.jugadoresLista = jugadores

        this.notifyDataSetChanged()
    }

    fun obtenerArrayJugadores(): Array<Jugador>{
        return this.jugadoresLista.toTypedArray()
    }



}