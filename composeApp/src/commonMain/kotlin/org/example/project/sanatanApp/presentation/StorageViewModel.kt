package org.example.project.sanatanApp.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.sanatanApp.domain.model.Aarti
import org.example.project.sanatanApp.domain.model.Bhajan
import org.example.project.sanatanApp.domain.model.Mantra
import org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen.AartiScreenState

class StorageViewModel():ViewModel() {

    private val _aarti = MutableStateFlow<Aarti?>(null)
    val aartiState = _aarti.asStateFlow()
    private val _bhajan = MutableStateFlow<Bhajan?>(null)
    val bhajanState = _bhajan.asStateFlow()
    private val _listType = MutableStateFlow<String?>(null)
    val listTypeState = _listType.asStateFlow()
    private val _mantra = MutableStateFlow<Pair<Mantra,Int>?>(null)
    val mantraState = _mantra.asStateFlow()

    fun setAarti(aarti:Aarti){
        _listType.value = "Aarti"
        _aarti.value = aarti
    }

    fun setBhajan(bhajan:Bhajan){
        _listType.value="Bhajan"
        _bhajan.value = bhajan
    }
    fun setMantra(mantra:Mantra,type:Int){
        _listType.value="Mantra"
        _mantra.value = Pair(mantra,type)
    }
}