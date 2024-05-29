package com.djcdev.practicascompose.ui.companioncomposables.loginregister

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.djcdev.practicascompose.domain.model.exceptions.FailedLogin


@Composable
fun ShowErrorMessage(fail: FailedLogin?, onDimiss: () -> Unit) {

    AlertDialog(
        onDismissRequest = { onDimiss() },
        title = {
            Text(text = "Error!")
        },
        text = {
            Text(
                text = when (fail) {
                    FailedLogin.InvalidUser -> "El usuario que está intentando ingresar no existe"
                    FailedLogin.InvalidPass -> "Usuario o Contraseña no válida"
                    FailedLogin.LoggedUser -> "Error al logear usuario. Compruebe que no inició sesion en otro dispositivo"
                    FailedLogin.MissingSomething -> "Usuario o contraseña faltante"
                    FailedLogin.NetworkFail -> "No se ha podido conectar con el servidor"
                    FailedLogin.TooManyRequests -> "Ha agotado todos los intentos de inicio de sesion. Intentelo más adelante"
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