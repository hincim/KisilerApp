package com.hakaninc.kisilerapp

import android.app.Application
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hakaninc.kisilerapp.viewmodel.PersonRecordViewModel
import com.hakaninc.kisilerapp.viewmodelfactory.PersonRecordViewModelFactory

@Composable
fun PersonRecord(navController: NavController) {

    val tfNameController = remember {
        mutableStateOf("")
    }
    val tfTelController = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val viewModel : PersonRecordViewModel = viewModel(
        factory = PersonRecordViewModelFactory(context.applicationContext as Application)
    )

    val localFocusManager = LocalFocusManager.current
    // geri tuşuna basınca texfieldları kapatmadan hemen geri dönerim.
    // 2 kere tıklama yapmamak için.

    val animatedProgress = remember {
        androidx.compose.animation.core.Animatable(500f)
    }

    LaunchedEffect(key1 = true){
        animatedProgress.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 250)
        )
    }

    Scaffold(modifier = Modifier.offset(
        x = 0.dp,
        y = animatedProgress.value.dp
    ),
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                contentColor = Color.Black,
                title = {
                    Text(text = "Person Record")
                }
            )
        }
        , content = {
            Column(verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {

                OutlinedTextField(value = tfNameController.value, onValueChange = {
                    tfNameController.value = it
                }, label = {
                    Text(text ="Person Name")
                },colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedBorderColor = Color.DarkGray,
                    unfocusedLabelColor = Color.DarkGray,
                ))
                OutlinedTextField(value = tfTelController.value, onValueChange = {
                    tfTelController.value = it
                }, label = {
                    Text(text ="Person Tel")
                },colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedBorderColor = Color.DarkGray,
                    unfocusedLabelColor = Color.DarkGray,
                ))
                OutlinedButton(onClick = {
                    localFocusManager.clearFocus()
                    val name = tfNameController.value
                    val tel = tfTelController.value
                    viewModel.personRecord(name,tel)
                    navController.popBackStack()
                }, modifier = Modifier.size(200.dp,50.dp)) {
                    Text(text = "Save", color = Color.Black)
                }
            }
        }
    )
}