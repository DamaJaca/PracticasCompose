package com.djcdev.practicascompose.ui.screens.firstscreen

import androidx.lifecycle.ViewModel
import com.djcdev.practicas.domain.model.FacturaModel
import com.djcdev.practicas.domain.usecase.FilterFacturasUseCase
import com.djcdev.practicas.domain.usecase.GetFacturasUseCase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.math.max


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
    fun changeIsFiltered(boolean: Boolean){
        _isFiltered.value=boolean
    }

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

    fun changeIsPending(boolean:Boolean){
        _isPendingChecked.value=boolean
    }

    private var _isNulledChecked = MutableStateFlow(false)
    val isNulledChecked get() = _isNulledChecked

    fun changeIsNulled(boolean:Boolean){
        _isNulledChecked.value=boolean
    }

    private var _isFixedChecked = MutableStateFlow(false)
    val isFixedChecked get() = _isFixedChecked

    fun changeIsFixed(boolean:Boolean){
        _isFixedChecked.value=boolean
    }

    private var _isPlannedChecked = MutableStateFlow(false)
    val isPlannedChecked get() = _isPlannedChecked

    fun changeIsPlanned(boolean:Boolean){
        _isPlannedChecked.value=boolean
    }


    private var _isPaidCheked = MutableStateFlow(false)
    val isPaidCheked get() = _isPaidCheked

    fun changeIsPaid(boolean: Boolean){
        _isPaidCheked.value=boolean
    }


    private var _maxImport = MutableStateFlow(0.00)
    val maxImport get() = _maxImport

    fun setMaxImport(newImport:Double){
        _maxImport.value= newImport
    }



    private var _initialDate = MutableStateFlow("dd/mm/yyyy")
    val initialDate get() = _initialDate
    fun setInitialDate(date:String){
        _initialDate.value=date
        if(_finalDate.value== "dd/mm/yyyy"&& date!= "dd/mm/yyyy"){
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

    private var _showInitialDatePickerDialog = MutableStateFlow(false)
    val showInitialDatePiclerDialog get () = _showInitialDatePickerDialog

    fun setShowInitialDatePickerDialog (boolean: Boolean){
        _showInitialDatePickerDialog.value= boolean
    }

    private var _showFinalDatePickerDialog = MutableStateFlow(false)
    val showFinalDatePickerDialog get () = _showFinalDatePickerDialog

    fun setShowFinalDatePickerDialog (boolean: Boolean){
        _showFinalDatePickerDialog.value= boolean
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

        var importe :Double? = null
        if (maxImport.value != 0.0){
            importe = maxImport.value
        }

        CoroutineScope(Dispatchers.IO).launch{
            val result: List<FacturaModel> = if (initialDate.value!= "dd/mm/yyyy" && finalDate.value!= "dd/mm/yyyy"){
                 filterFacturasUseCase.invoke(isPendingChecked.value, isPaidCheked.value, importe, initialDate.value,finalDate.value,isMockCheked.value)
            }
            else{
                filterFacturasUseCase.invoke(isPendingChecked.value, isPaidCheked.value, importe, null, null,isMockCheked.value)
            }


            _bills.value = result
            _isBillsLoading.value = false
        }
    }

}