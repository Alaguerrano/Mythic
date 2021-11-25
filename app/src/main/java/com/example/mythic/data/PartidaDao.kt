package com.example.mythic.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PartidaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarPartida(partida: Partida)

    @Query("SELECT * FROM partidas_tabla ORDER BY id ASC")
    fun leerTodosLosDatos(): LiveData<List<Partida>>
}