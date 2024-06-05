package com.djcdev.practicascompose.ui.screens.remember

import androidx.lifecycle.ViewModel
import com.djcdev.practicas.domain.usecase.RememberUserUseCase
import com.djcdev.practicascompose.domain.model.exceptions.FailedLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RememberViewModel @Inject constructor(
    private val rememberUserUseCase: RememberUserUseCase
): ViewModel(){

    private val _email = MutableStateFlow("")
    val email get () = _email



    private var _isLoading = MutableStateFlow(false)
    val isLoading get () = _isLoading

    fun changeIsLoading(boolean: Boolean){
        _isLoading.value= boolean
    }



    fun remember(user: String, remember: (Boolean, FailedLogin?) -> Unit) {
        rememberUserUseCase(user) { boolean, failed -> remember(boolean, failed) }
    }

}