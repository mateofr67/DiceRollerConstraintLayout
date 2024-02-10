package com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mpd.pmdm.dicerollerconstraintlayout.data.AppRepository
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.tirada.Tirada
import kotlinx.coroutines.launch

class TiradaViewModel(val appRepository: AppRepository)  : ViewModel() {

    val obtenerTiradas  : LiveData<List<Tirada>> = appRepository.obtenerTiradas

    fun insertarTirada (fecha: String,dado1 : Int, dado2:Int){
        val newTirada = Tirada(fecha = fecha , dado1 = dado1, dado2 = dado2)
        viewModelScope.launch{
            appRepository.insertarTirada(newTirada)
        }
    }

    fun borrarTiradas(){
        viewModelScope.launch{
            appRepository.borrarTiradas()
        }
    }
}

class TiradaViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return TiradaViewModel(repository) as T
    }
}