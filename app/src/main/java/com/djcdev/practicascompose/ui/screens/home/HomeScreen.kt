package com.djcdev.practicascompose.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.navigation.Login
import com.djcdev.practicascompose.ui.navigation.PracticeOne
import com.djcdev.practicascompose.ui.navigation.PracticeThree
import com.djcdev.practicascompose.ui.navigation.PracticeTwo
import com.djcdev.practicascompose.ui.screens.login.saveUser
import kotlinx.coroutines.runBlocking

@Composable
fun HomeScreen(navController: NavHostController) {
    ComposeStructure(
        topAppBar = { }, statusBar = false,
        contentApp = {
            ContentHome(navController)
        })
}

@Composable
private fun ContentHome(
    navController: NavHostController
) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)

        ) {
            Text(
                text = stringResource(id = R.string.practicasAndroid),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )

            PracticeItem(
                text = stringResource(id = R.string.practicas_practica1),
                imageRes = R.drawable.ic_next
            ) { navController.navigate(PracticeOne) }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 2.dp,
                color = Color.Gray
            )

            PracticeItem(
                text = stringResource(id = R.string.practicas_practica2),
                imageRes = R.drawable.ic_next
            ) { navController.navigate(PracticeTwo) }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 2.dp,
                color = Color.Gray
            )

            PracticeItem(
                text = stringResource(id = R.string.practicas_practica3),
                imageRes = R.drawable.ic_next
            ) { navController.navigate(PracticeThree) }
        }

        FloatingActionButton(
            onClick = {
                Toast.makeText(
                    context,
                    context.getString(R.string.suscces_log_out),
                    Toast.LENGTH_SHORT
                ).show()
                runBlocking { saveUser("", "", context) }
                navController.navigate(Login) {
                    popUpTo(Login) { inclusive = true }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = stringResource(id = R.string.close)
            )
        }
    }
}

@Composable
fun PracticeItem(text: String, imageRes: Int, clickedImage: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .weight(1f)
        )
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.Gray),
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = clickedImage)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeFragmentScreenPreview() {
    Surface {
        HomeScreen(navController = rememberNavController())
    }
}