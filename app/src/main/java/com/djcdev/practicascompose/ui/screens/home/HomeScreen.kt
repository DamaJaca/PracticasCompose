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
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.navigation.Login
import com.djcdev.practicascompose.ui.screens.login.saveUser
import kotlinx.coroutines.runBlocking

@Composable
fun HomeScreen(navController: NavHostController) {
    ComposeStructure(topAppBar = { }) {
        ContentHome(navController)
    }
}

@Composable
private fun ContentHome(
    navController: NavHostController
) {
    val context = LocalContext.current
    Box (modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)

        ) {
            Text(
                text = "Practicas Android",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )

            PracticeItem(
                text = "Practica 1",
                imageRes = R.drawable.ic_next
            ) { navController.navigate(Login) }

            Divider(
                color = Color.Gray,
                thickness = 2.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            PracticeItem(
                text = "Practica 2",
                imageRes = R.drawable.ic_next
            ) { }

            Divider(
                color = Color.Gray,
                thickness = 2.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            PracticeItem(
                text = "Practica 3",
                imageRes = R.drawable.ic_next
            ) {  }
        }

        FloatingActionButton(
            onClick = {
                Toast.makeText(context, "Se ha cerrardo sesion correctamente", Toast.LENGTH_SHORT).show()
                runBlocking { saveUser("","",context) }
                navController.navigate(Login)
                      },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Cerrar Sesion")
        }
    }
}

@Composable
fun PracticeItem(text: String, imageRes: Int, clickedImage :  () -> Unit) {
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
        HomeScreen(navController= rememberNavController())
    }
}