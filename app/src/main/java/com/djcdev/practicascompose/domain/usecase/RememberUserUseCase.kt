package com.djcdev.practicascompose.domain.usecase

import com.djcdev.practicascompose.domain.model.exceptions.FailedLogin
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import javax.inject.Inject

class RememberUserUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth){
    operator fun invoke(user: String, remember: (Boolean, FailedLogin?) -> Unit){
        firebaseAuth
            .sendPasswordResetEmail(user)
            .addOnCompleteListener {
                if (it.isSuccessful){ remember(true, null) }
            }.addOnFailureListener {exception ->

                when (exception) {

                    is FirebaseAuthInvalidUserException -> remember(
                        false,
                        FailedLogin.InvalidUser
                    )

                    is FirebaseNetworkException -> remember(
                        false,
                        FailedLogin.NetworkFail
                    )

                    is FirebaseTooManyRequestsException -> remember(
                        false,
                        FailedLogin.TooManyRequests
                    )
                    else -> remember(false, null)
                }
            }
    }
}