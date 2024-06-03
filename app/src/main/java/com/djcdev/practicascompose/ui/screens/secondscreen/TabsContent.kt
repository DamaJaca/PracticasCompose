package com.djcdev.practicascompose.ui.screens.secondscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.djcdev.practicascompose.R

@Composable
fun TabContent1(){
    Column {
        val appendedText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = colorResource(id = R.color.practice_light_grey) )
            ){
                append("Autoconsumo: ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)
            ){
                append("92%")
            }
        }
        Text("Aquí tienes los datos de tu instalación fotovoltaica en tiempo real",
            modifier= Modifier.padding(bottom = 16.dp)
        )
        Text(appendedText,
            modifier= Modifier.padding(bottom = 16.dp)
        )
        Image(painter = painterResource(id = R.drawable.graph_one),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

    }
}

@Composable
fun TabContent2(){
    Column (
        modifier= Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.plan_gestiones),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.on_progress_text_energy),
            modifier= Modifier.padding(38.dp),
            textAlign = TextAlign.Center,
            letterSpacing = 2.sp,
            fontSize = 16.sp,
            lineHeight = 24.sp

        )
    }

}

@Composable
fun TabContent3(){

    var isLoading by remember { mutableStateOf(false) }

    Column (Modifier.fillMaxSize()){
        if (isLoading){
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary, strokeWidth = 3.dp)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewTabContent(){
    TabContent3()
}