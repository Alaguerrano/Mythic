package com.example.mythic.data.atributo

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mythic.model.Aventura

@Dao
interface AtributoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crearAventura(aventura: Aventura)

    @Update
    fun actualizarAventura(aventura: Aventura)

    @Delete
    fun borrarAventura(aventura: Aventura)

    @Query("SELECT * FROM aventuras_tabla WHERE idJugador =:idJugador")
    fun obtenerAventurasJugador(idJugador : Int): LiveData<List<Aventura>>

    @Query("DELETE FROM aventuras_tabla WHERE idJugador =:idJugador")
    fun borrarAventurasJugador(idJugador : Int)
}