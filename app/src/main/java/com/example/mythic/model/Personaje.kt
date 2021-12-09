package com.example.mythic.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "personajes_tabla")
data class Personaje(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val idJugador : Int,
    val estaEnAventura: Boolean,
    val idAventura : Int,
    val esPJ : Boolean,
    val esNPJ : Boolean,
    val esPJLibre : Boolean,
    val esPJPuntos : Boolean,
    val nombre : String,
    val resumen : String,
    val puntosFavor : Int
): Parcelable