package com.example.mythic.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partidas_tabla")
data class Partida(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val nombre : String,
    val masterHumano : Boolean,
    val multijugador : Boolean,
    val motorDistintoMythic : Boolean
)