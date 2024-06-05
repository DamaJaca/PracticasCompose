package com.djcdev.practicascompose.ui.companioncomposables.loginregister

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.domain.model.exceptions.FailedSignUp

@Composable
fun ShowErrorRegisterMessage(fail: FailedSignUp?, onDimiss: () -> Unit) {

    AlertDialog(
        onDismissRequest = { onDimiss() },
        title = {
            Text(text = stringResource(id = R.string.error_dialog_title))
        },
        text = {
            Text(
                text = when (fail) {
                    FailedSignUp.InvalidCredential -> stringResource(id = R.string.no_valid_email)
                    FailedSignUp.UserAlreadyExist -> stringResource(id = R.string.user_already_exist)
                    FailedSignUp.WeakPas -> stringResource(id = R.string.weak_pass)
                    FailedSignUp.NotSamePass -> stringResource(id = R.string.not_same_pass)
                    null -> stringResource(id = R.string.unknown_error)
                }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onDimiss()
                }) {
                Text(stringResource(id = R.string.ok))
            }
        }
    )

}