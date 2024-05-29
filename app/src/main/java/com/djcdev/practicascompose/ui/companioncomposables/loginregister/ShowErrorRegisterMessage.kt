package com.djcdev.practicascompose.ui.companioncomposables.loginregister

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.djcdev.practicascompose.domain.model.exceptions.FailedSignUp

@Composable
fun ShowErrorRegisterMessage(fail: FailedSignUp?, onDimiss: () -> Unit) {

    AlertDialog(
        onDismissRequest = { onDimiss() },
        title = {
            Text(text = "Error!")
        },
        text = {
            Text(
                text = when (fail) {
                    FailedSignUp.InvalidCredential -> "El email introducido no es válido. Comprueba que es un email válido"
                    FailedSignUp.UserAlreadyExist -> "El usuario que intenta introducir ya está registrado"
                    FailedSignUp.WeakPas -> "La contraseña es demasiado debil"
                    FailedSignUp.NotSamePass -> "Las contraseñas no coinciden o los campos estan vacios"
                    null -> "Ha ocurrido un error inesperado al intentar realizar esa acción"
                }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onDimiss()
                }) {
                Text("OK")
            }
        }
    )

}