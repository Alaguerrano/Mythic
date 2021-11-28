package com.example.mythic.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "partidas_tabla")
data class Partida(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val nombre : String,
    val masterHumano : Boolean,
    val multijugador : Boolean,
    val motorDistintoMythic : Boolean
): Parcelable