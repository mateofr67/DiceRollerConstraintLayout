package com.mpd.pmdm.dicerollerconstraintlayout

import android.app.Application
import com.mpd.pmdm.dicerollerconstraintlayout.data.AppRepository
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.TiradaDatabase

class TiradasApp : Application() {

    private val tiradaDatabase : TiradaDatabase by lazy {
        TiradaDatabase.getDatabase(this)
    }

    val appRepository : AppRepository by lazy {
        AppRepository(tiradaDatabase.tiradaDao())
    }

}