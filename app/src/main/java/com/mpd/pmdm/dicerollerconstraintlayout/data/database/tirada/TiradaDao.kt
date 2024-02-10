package com.mpd.pmdm.dicerollerconstraintlayout.data.database.tirada

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.tirada.Tirada

@Dao
interface TiradaDao {

    @Query("SELECT * FROM Tirada order by id desc")
    fun obtenerTiradas() : LiveData<List<Tirada>>

    @Query("DELETE  FROM Tirada")
    suspend fun borrarTiradas()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend  fun insertarTirada(tirada: Tirada)


}