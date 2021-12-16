package com.example.mythic.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "jugadores_tabla")
data class Jugador(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val nombre : String,
    val tipoMaster : Int,
    val numeroJugadores : Int,
    val motorJuego: Int

): Parcelable