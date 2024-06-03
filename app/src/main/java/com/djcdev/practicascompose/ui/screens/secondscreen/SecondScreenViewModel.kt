package com.djcdev.practicascompose.ui.screens.secondscreen

import androidx.lifecycle.ViewModel
import com.djcdev.practicas.domain.usecase.GetDetailsUseCase
import com.djcdev.practicas.domain.usecase.SingUpUseCase
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
    fun setIsLoading(boolean: Boolean){
        _isLoading.value=boolean
    }

    private val _showDialog = MutableStateFlow(false)
    val showDialog get () = _showDialog
    fun setShowDialog(boolean: Boolean){
        _showDialog.value=boolean
    }

    private val _cauString = MutableStateFlow("")
    val cauString get () = _cauString
    fun setCauStrign(newString:String){
        _cauString.value=newString
    }


    private val _stateString = MutableStateFlow("")
    val stateString get () = _stateString
    fun setStateString(newString:String){
        _stateString.value=newString
    }


    private val _typeString = MutableStateFlow("")
    val typeString get () = _typeString
    fun setTypeString(newString:String){
        _typeString.value=newString
    }


    private val _compString = MutableStateFlow("")
    val compString get () = _compString
    fun setCompString(newString:String){
        _compString.value=newString
    }


    private val _installString = MutableStateFlow("")
    val installString get () = _installString
    fun setInstallString(newString:String){
        _installString.value=newString
    }



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