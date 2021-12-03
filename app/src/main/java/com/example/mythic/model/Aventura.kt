package com.example.mythic.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aventuras_tabla")
data class Aventura(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val idJugador: Int,
    val nombre : String,
    val premisa : String,
    val caos : Int
)

