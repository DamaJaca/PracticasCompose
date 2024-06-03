package com.djcdev.practicascompose.ui.screens.secondscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.djcdev.practicascompose.R
import com.djcdev.practicascompose.ui.screens.secondscreen.SecondScreenViewModel

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

    val viewModel: SecondScreenViewModel = hiltViewModel()
    viewModel.getDetails()

    val isLoading by viewModel.isLoading.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()
    val cauString by viewModel.cauString.collectAsState()
    val stateString by viewModel.stateString.collectAsState()
    val typeString by viewModel.typeString.collectAsState()
    val compString by viewModel.compString.collectAsState()
    val installString by viewModel.installString.collectAsState()

    Box (Modifier.fillMaxSize()){
        if (isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.Transparent)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }else{
            Column {
                Column(modifier= Modifier.padding(vertical = 16.dp)){
                    Text(text = stringResource(id = R.string.cau_autoconsumo),
                        color = colorResource(id = R.color.practice_grey),
                        fontSize = 16.sp
                    )
                    Text(text = cauString,
                        modifier= Modifier.padding(top= 8.dp),
                        fontSize = 16.sp
                    )
                    HorizontalDivider(color= colorResource(id = R.color.practice_grey))
                }

                Box(modifier= Modifier.padding(vertical = 16.dp)){
                    Column(modifier=Modifier.fillMaxWidth()){
                        Text(text = stringResource(id = R.string.estado_solucitud),
                            color = colorResource(id = R.color.practice_grey),
                            fontSize = 16.sp
                        )
                        Text(text = stateString,
                            modifier= Modifier.padding(top= 8.dp),
                            fontSize = 16.sp
                        )
                    HorizontalDivider(color= colorResource(id = R.color.practice_grey))
                    }
                    Image(painter = painterResource(id = R.drawable.infoicon_azul),
                        contentDescription =null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable { viewModel.setShowDialog(true) })
                }

                Column(modifier= Modifier.padding(vertical = 16.dp)){
                    Text(text = stringResource(id = R.string.tipo_autoconsumo),
                        color = colorResource(id = R.color.practice_grey),
                        fontSize = 16.sp
                    )
                    Text(text = typeString,
                        modifier= Modifier.padding(top= 8.dp),
                        fontSize = 16.sp
                    )
                    HorizontalDivider(color= colorResource(id = R.color.practice_grey))
                }

                Column(modifier= Modifier.padding(vertical = 16.dp)){
                    Text(text = stringResource(id = R.string.compensacion_excedentes),
                        color = colorResource(id = R.color.practice_grey),
                        fontSize = 16.sp
                    )
                    Text(text = compString,
                        modifier= Modifier.padding(top= 8.dp),
                        fontSize = 16.sp
                    )
                    HorizontalDivider(color= colorResource(id = R.color.practice_grey))
                }

                Column(modifier= Modifier.padding(vertical = 16.dp)){
                    Text(text = stringResource(id = R.string.potencia_instalacion),
                        color = colorResource(id = R.color.practice_grey),
                        fontSize = 16.sp
                    )
                    Text(text = installString,
                        modifier= Modifier.padding(top= 8.dp),
                        fontSize = 16.sp
                    )
                    HorizontalDivider(color= colorResource(id = R.color.practice_grey))
                }

            }

            if(showDialog){
                StatusAutoConsumeDialog {
                    viewModel.setShowDialog(false)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewTabContent(){
    TabContent3()
}