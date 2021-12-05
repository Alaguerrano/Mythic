package com.example.mythic.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "aventuras_tabla")
data class Aventura(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val idJugador: Int,
    val nombre : String,
    val premisa : String,
    val caos : Int
): Parcelable


