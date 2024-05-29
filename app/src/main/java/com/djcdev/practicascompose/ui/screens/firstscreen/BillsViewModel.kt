package com.djcdev.practicascompose.ui.screens.firstscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djcdev.practicas.domain.model.FacturaModel
import com.djcdev.practicas.domain.usecase.GetFacturasUseCase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class BillsViewModel @Inject constructor(
    private val getFacturasUseCase: GetFacturasUseCase
) : ViewModel() {

    private var _isBillsLoading = MutableStateFlow(false)
    val isBillsLoading get() = _isBillsLoading

    private var _errorInNet = MutableStateFlow(false)
    val errorInNet get() = _errorInNet


    @Inject
    lateinit var firebaseConfig: FirebaseRemoteConfig


    private var _bills = MutableStateFlow<List<FacturaModel>>(emptyList())
    val bills get() = _bills


    private var _isMockCheked = MutableStateFlow(false)
    val isMockCheked get() = _isMockCheked
    fun setIsMockCheked(boolean: Boolean) {
        _isMockCheked.value = boolean
    }


    fun getFacturas() {
        _isBillsLoading.value = true
        var result: List<FacturaModel>
        CoroutineScope(Dispatchers.IO).launch {
            result =
                getFacturasUseCase(isMockCheked.value, firebaseConfig.getBoolean("ktor_client"))


            if (result.isNotEmpty()) {
                _bills.value = result
            } else {
                _errorInNet.value = true
                _bills.value = emptyList()
            }
            _isBillsLoading.value = false
        }
    }

}