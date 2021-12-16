package com.example.mythic.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "atributos_tabla")
data class Atributo(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val idPersonaje : Int,
    val nombre : String,
    val rango : Int
): Parcelable