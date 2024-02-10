package com.mpd.pmdm.dicerollerconstraintlayout.data

import com.mpd.pmdm.dicerollerconstraintlayout.data.database.tirada.Tirada
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.tirada.TiradaDao

class AppRepository(val tiradaDao: TiradaDao) {
    val obtenerTiradas = tiradaDao.obtenerTiradas()

    suspend fun insertarTirada(tirada: Tirada){
        tiradaDao.insertarTirada(tirada)
    }

    suspend fun borrarTiradas(){
        tiradaDao.borrarTiradas()
    }
}