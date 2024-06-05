package com.djcdev.practicascompose.ui.screens.thirdscreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.djcdev.practicascompose.MainActivity.Companion.URL_DESTINO
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.ui.companioncomposables.ComposeStructure
import com.djcdev.practicascompose.ui.companioncomposables.TopBarNoAction
import com.djcdev.practicascompose.ui.screens.thirdscreen.components.WebViewComponent


@Composable
fun ThirdScreen(navController: NavController= NavController(LocalContext.current)){
    val context = LocalContext.current

    var showWebView by  rememberSaveable {
        mutableStateOf(false)
    }
    var showProgressBar by  rememberSaveable {
        mutableStateOf(false)
    }

    ComposeStructure(topAppBar = { TopBarNoAction(name = "Navegación") {
        navController.navigateUp()
    } }, statusBar = true,
        contentApp = {
            Column (modifier = Modifier.padding(36.dp)) {
                Text(text= stringResource(id = R.string.info_navigation))
                Row(modifier= Modifier.padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(URL_DESTINO))
                        context.startActivity(intent)
                    },
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(2f)
                    ) {
                        Text (
                            text= stringResource(
                                id = R.string.abrir_navegador_externo),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.padding(8.dp) )

                    Button(onClick = {
                        showWebView=!showWebView
                        if (showWebView) showProgressBar= true
                    },
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(2f)
                    ) {
                        Text (
                            text= stringResource(id = R.string.abrir_webview)
                        )
                    }
                }
                    if (showWebView){
                        WebViewComponent(url = URL_DESTINO){showProgressBar= false}
                    }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    if (showProgressBar) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(35.dp)
                                .background(Color.Transparent),
                            color = Color.Green
                        )
                    }

                }
            }
        })
}

@Preview(showBackground = true)
@Composable
fun PreviewThirdScreen(){
    ThirdScreen()
}