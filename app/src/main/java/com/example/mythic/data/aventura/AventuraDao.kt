package com.example.mythic.data.aventura

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mythic.model.Aventura
import com.example.mythic.model.Jugador


@Dao
interface AventuraDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crearAventura(aventura: Aventura)

    @Update
    fun actualizarAventura(aventura: Aventura)

    @Delete
    fun borrarAventura(aventura: Aventura)

    @Query("SELECT * FROM aventuras_tabla ORDER BY id ASC")
    fun obtenerAventuras(): LiveData<List<Aventura>>

    @Query("SELECT * FROM aventuras_tabla WHERE id =:idJugador")
    fun obtenerAventuraIdJugador(idJugador : Int): LiveData<List<Aventura>>


}