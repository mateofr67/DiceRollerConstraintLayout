package com.mpd.pmdm.dicerollerconstraintlayout.data.database.tirada

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tirada(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo val fecha : String,
    @ColumnInfo val dado1 : Int,
    @ColumnInfo val dado2: Int
)
