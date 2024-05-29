package com.djcdev.practicascompose.ui.screens.firstscreen

import androidx.lifecycle.ViewModel
import com.djcdev.practicas.domain.model.FacturaModel
import com.djcdev.practicas.domain.usecase.FilterFacturasUseCase
import com.djcdev.practicas.domain.usecase.GetFacturasUseCase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class BillsViewModel @Inject constructor(
    private val getFacturasUseCase: GetFacturasUseCase,
    private val filterFacturasUseCase: FilterFacturasUseCase
) : ViewModel() {

    private var _billsMaxImport = MutableStateFlow(50.0f)
    val billsMaxImport get() = _billsMaxImport

    private var _isBillsLoading = MutableStateFlow(false)
    val isBillsLoading get() = _isBillsLoading

    private var _isFiltered = MutableStateFlow(false)
    val isFiltered get() = _isFiltered

    private var _errorInNet = MutableStateFlow(false)
    val errorInNet get() = _errorInNet


    @Inject
    lateinit var firebaseConfig: FirebaseRemoteConfig


    private var _bills = MutableStateFlow<List<FacturaModel>>(emptyList())
    val bills get() = _bills


    private var _isMockCheked = MutableStateFlow(false)
    val isMockCheked get() = _isMockCheked



    //Filters


    private var _isPendingChecked = MutableStateFlow(false)
    val isPendingChecked get() = _isPendingChecked

    fun changeIsPending(){
        _isPendingChecked.value=!_isPendingChecked.value
    }


    private var _isPaidCheked = MutableStateFlow(false)
    val isPaidCheked get() = _isPaidCheked

    fun changeIsPaid(){
        _isPaidCheked.value=!_isPaidCheked.value
    }


    private var _maxImport = MutableStateFlow(50.00)
    val maxImport get() = _maxImport



    private var _initialDate = MutableStateFlow("dd/mm/yyyy")
    val initialDate get() = _initialDate
    fun setInitialDate(date:String){
        _initialDate.value=date
        if(_finalDate.value== "dd/mm/yyyy"){
            _finalDate.value= SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                Date()
            )
        }
    }


    private var _finalDate = MutableStateFlow("dd/mm/yyyy")
    val finalDate get() = _finalDate

    fun setFinalDate(date:String){
        _finalDate.value= date
        if (_initialDate.value=="dd/mm/yyyy"){ _initialDate.value=date }
    }


    fun setIsMockCheked(boolean: Boolean) {
        _isMockCheked.value = boolean
    }


    fun getFacturas() {
        _isBillsLoading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val result: List<FacturaModel> =
                getFacturasUseCase(isMockCheked.value, firebaseConfig.getBoolean("ktor_client"))


            if (result.isNotEmpty()) {
                _bills.value = result
                _billsMaxImport.value= _bills.value.maxOf { it.importe }.toFloat()
            } else {
                _errorInNet.value = true
                _bills.value = emptyList()
            }
            _isBillsLoading.value = false
        }
    }
    fun filterFacturas(){
        _isBillsLoading.value=true
        CoroutineScope(Dispatchers.IO).launch {
            val result: List<FacturaModel> = filterFacturasUseCase(isPendingChecked.value, isPaidCheked.value, maxImport.value, initialDate.value,finalDate.value,isMockCheked.value)

            _bills.value = result
            _isBillsLoading.value = false
        }
    }

}