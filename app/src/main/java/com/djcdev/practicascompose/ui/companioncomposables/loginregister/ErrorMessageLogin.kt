package com.djcdev.practicascompose.ui.companioncomposables.loginregister

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.domain.model.exceptions.FailedLogin


@Composable
fun ShowErrorMessage(fail: FailedLogin?, onDimiss: () -> Unit) {

    AlertDialog(
        onDismissRequest = { onDimiss() },
        title = {
            Text(text = stringResource(id = R.string.error_dialog_title))
        },
        text = {
            Text(
                text = when (fail) {
                    FailedLogin.InvalidUser -> stringResource(id = R.string.no_user_found)
                    FailedLogin.InvalidPass -> stringResource(id = R.string.no_valid_user)
                    FailedLogin.LoggedUser -> stringResource(id = R.string.already_logged_user)
                    FailedLogin.MissingSomething -> stringResource(id = R.string.missing_pass)
                    FailedLogin.NetworkFail -> stringResource(id = R.string.net_error)
                    FailedLogin.TooManyRequests -> stringResource(id = R.string.too_many_request_log)
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