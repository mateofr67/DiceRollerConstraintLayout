package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.tirada.Tirada
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.tirada.TiradaDao

@Database(entities = [Tirada::class] , version = 1 , exportSchema = false)
abstract class TiradaDatabase : RoomDatabase() {
    abstract fun tiradaDao() : TiradaDao

    companion object{
        @Volatile
        private var INSTANCE: TiradaDatabase? = null

        fun getDatabase(context: Context): TiradaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    TiradaDatabase::class.java,
                    "tirada_database"
                )
                    .build()
                INSTANCE = instance

                instance
            }
        }


    }


}