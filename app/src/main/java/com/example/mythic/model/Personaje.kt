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
    //si idAventura -1 no esta en ninguna aventura
    val idAventura : Int,
    //si no es NPJ es PJ
    val esNPJ : Boolean,
    //si no es PJPuntos, es PJ Libre
    val esPJPuntos : Boolean,
    val nombre : String,
    val resumen : String,
    val puntosFavor : Int
): Parcelable