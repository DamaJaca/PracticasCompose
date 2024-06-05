package com.djcdev.practicascompose.ui.screens.secondscreen

import androidx.lifecycle.ViewModel
import com.djcdev.practicas.domain.usecase.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondScreenViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase
): ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading get () = _isLoading

    private val _showDialog = MutableStateFlow(false)
    val showDialog get () = _showDialog
    fun setShowDialog(boolean: Boolean){
        _showDialog.value=boolean
    }

    private val _cauString = MutableStateFlow("")
    val cauString get () = _cauString


    private val _stateString = MutableStateFlow("")
    val stateString get () = _stateString


    private val _typeString = MutableStateFlow("")
    val typeString get () = _typeString


    private val _compString = MutableStateFlow("")
    val compString get () = _compString

    private val _installString = MutableStateFlow("")
    val installString get () = _installString



    fun getDetails(){

        CoroutineScope(Dispatchers.IO).launch {
            val details =getDetailsUseCase.invoke()
            _cauString.value = details?.cau.toString()
            stateString.value = details?.estado.toString()
            typeString.value = details?.tipo.toString()
            compString.value = details?.compensacion.toString()
            installString.value = details?.potencia.toString()
            _isLoading.value=false
        }
    }

}