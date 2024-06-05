package com.djcdev.practicascompose.ui.screens.login

import androidx.lifecycle.ViewModel
import com.djcdev.practicascompose.domain.usecase.LoginUseCase
import com.djcdev.practicascompose.domain.usecase.RememberUserUseCase
import com.djcdev.practicascompose.domain.model.exceptions.FailedLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val rememberUserUseCase: RememberUserUseCase
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



    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible get () = _passwordVisible
    fun changeVisibilityPass(){
        _passwordVisible.value=!_passwordVisible.value
    }


    private val _rememberPassword = MutableStateFlow(false)
    val rememberPassword get () = _rememberPassword
    fun changeCheckPass(){
        _rememberPassword.value= !_rememberPassword.value
    }


    private var _isLoading = MutableStateFlow(false)
    val isLoading get () = _isLoading

    fun changeIsLoading(boolean: Boolean){
        _isLoading.value= boolean
    }



    fun login(user: String, pass: String, logged: (Boolean, FailedLogin?) -> Unit) {
        loginUseCase.invoke(user, pass) { boolean, failed -> logged(boolean, failed) }
    }

    fun remember(user: String, remember: (Boolean, FailedLogin?) -> Unit) {
        rememberUserUseCase(user) { boolean, failed -> remember(boolean, failed) }
    }

}