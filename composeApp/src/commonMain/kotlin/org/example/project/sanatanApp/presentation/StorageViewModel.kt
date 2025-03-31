package org.example.project.sanatanApp.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.sanatanApp.domain.model.Aarti
import org.example.project.sanatanApp.domain.model.Bhajan
import org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen.AartiScreenState

class StorageViewModel():ViewModel() {

    private val _aarti = MutableStateFlow<Aarti?>(null)
    val aartiState = _aarti.asStateFlow()
    private val _bhajan = MutableStateFlow<Bhajan?>(null)
    val bhajanState = _bhajan.asStateFlow()

    fun setAarti(aarti:Aarti){
        _aarti.value = aarti
    }

    fun setBhajan(aarti:Bhajan){
        _bhajan.value = aarti
    }
}