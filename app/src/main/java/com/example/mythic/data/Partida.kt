package com.example.mythic.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partidas_tabla")
data class Partida(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val nombre : String,
    val masterHumano : Boolean
)