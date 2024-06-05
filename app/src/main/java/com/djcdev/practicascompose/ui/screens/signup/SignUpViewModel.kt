package com.djcdev.practicascompose.ui.screens.signup

import androidx.lifecycle.ViewModel
import com.djcdev.practicas.domain.usecase.SingUpUseCase
import com.djcdev.practicascompose.domain.model.exceptions.FailedSignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val singUpUseCase: SingUpUseCase
): ViewModel(){

    private val _email = MutableStateFlow("")
    val email get () = _email
    fun setEmail(email:String){
        _email.value=email
    }



    private val _password = MutableStateFlow("")
    val password get () = _password
    fun setPassword(pass:String){
        _password.value=pass
    }



    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword get () = _confirmPassword
    fun setConfirmPassword(pass:String){
        _confirmPassword.value=pass
    }



    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible get () = _passwordVisible
    fun changeVisibilityPass(){
        _passwordVisible.value=!_passwordVisible.value
    }



    private val _confirmPasswordVisible = MutableStateFlow(false)
    val confirmPasswordVisible get () = _confirmPasswordVisible
    fun changeConfirmVisibilityPass(){
        _confirmPasswordVisible.value=!_passwordVisible.value
    }


    private var _isLoading = MutableStateFlow(false)
    val isLoading get () = _isLoading

    fun changeIsLoading(boolean: Boolean){
        _isLoading.value= boolean
    }



    fun singUp(user: String, pass: String, onComplete: (Boolean, FailedSignUp?) -> Unit) {
        singUpUseCase.invoke(user, pass) { bolean, fail -> onComplete(bolean, fail) }
    }

}