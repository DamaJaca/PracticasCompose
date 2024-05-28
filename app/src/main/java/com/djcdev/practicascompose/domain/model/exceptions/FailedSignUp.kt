package com.djcdev.practicascompose.domain.model.exceptions

sealed class FailedSignUp {
    data object WeakPas: FailedSignUp()
    data object InvalidCredential: FailedSignUp()
    data object UserAlreadyExist: FailedSignUp()
    data object NotSamePass: FailedSignUp()

}