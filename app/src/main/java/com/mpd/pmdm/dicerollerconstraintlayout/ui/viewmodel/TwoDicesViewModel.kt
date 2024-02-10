package com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mpd.pmdm.dicerollerconstraintlayout.logic.Dice

class TwoDicesViewModel() : ViewModel() {

    private val dice1 = Dice(6)
    private val dice2 = Dice(6)

    private val _dado1 = MutableLiveData<Int>(dice1.roll())
    val dado1: LiveData<Int> = _dado1

    private val _dado2 = MutableLiveData<Int>(dice2.roll())
    val dado2: LiveData<Int> = _dado2


    fun rollDices() {
        _dado1.value = dice1.roll()
        _dado2.value = dice2.roll()
    }


    @Suppress("UNCHECKED_CAST")
    class TwoDicesFactory() : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TwoDicesViewModel() as T
        }

    }
}