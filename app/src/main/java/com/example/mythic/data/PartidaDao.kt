package com.example.mythic.data


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mythic.model.Partida


@Dao
interface PartidaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertarPartida(partida: Partida)

    @Update
    fun actualizarPartida(partida: Partida)

    @Delete
    fun borrarPartida(partida: Partida)

    @Query("SELECT * FROM partidas_tabla ORDER BY id ASC")
    fun leerTodosLosDatos(): LiveData<List<Partida>>

    @Query("SELECT * FROM partidas_tabla WHERE nombre =:nombre")
    fun obtenerPartidaPorNombre(nombre : String): LiveData<Partida>
}