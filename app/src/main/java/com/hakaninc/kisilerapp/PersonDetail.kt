package com.hakaninc.kisilerapp

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hakaninc.kisilerapp.entity.Persons
import com.hakaninc.kisilerapp.viewmodel.*
import com.hakaninc.kisilerapp.viewmodelfactory.PersonDetailViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun PersonDetail(person: Persons,navController: NavController) {

    val tfNameController = remember {
        mutableStateOf("")
    }
    val tfTelController = remember {
        mutableStateOf("")
    }
    var oldName = ""

    val localFocusManager = LocalFocusManager.current

    val context = LocalContext.current
    val viewModel : PersonDetailViewModel = viewModel(
        factory = PersonDetailViewModelFactory(context.applicationContext as Application)
    )
    val animatedProgress = remember {
        androidx.compose.animation.core.Animatable(-500f)
    }

    LaunchedEffect(key1 = true){
        tfNameController.value = person.person_name
        tfTelController.value = person.person_tel
        oldName = person.person_name
        animatedProgress.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 250)
        )
    }

    Scaffold(modifier = Modifier.offset(
        x = animatedProgress.value.dp,
        y = 0.dp
    ),
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                contentColor = Color.Black,
                title = {
                    Text(text = "Person Detail")
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
                }, modifier = Modifier.offset(
                    x = animatedProgress.value.dp,
                    y = 0.dp
                ), colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedBorderColor = Color.DarkGray,
                    unfocusedLabelColor = Color.DarkGray,
                ))
                OutlinedTextField(value = tfTelController.value, onValueChange = {
                    tfTelController.value = it
                }, label = {
                    Text(text ="Person Tel")
                },modifier = Modifier.offset(
                    x = animatedProgress.value.dp,
                    y = 0.dp
                ),colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedBorderColor = Color.DarkGray,
                    unfocusedLabelColor = Color.DarkGray,
                ))
                OutlinedButton(onClick = {
                    localFocusManager.clearFocus()
                    val personName = tfNameController.value
                    val personTel = tfTelController.value
                    viewModel.personUpdate(person.person_id,personName,personTel,oldName)
                    navController.popBackStack()
                }, modifier = Modifier
                    .graphicsLayer(
                        rotationX = animatedProgress.value
                    )
                    .size(200.dp, 50.dp)) {
                    Text(text = "Update", color = Color.Black)
                }
            }
        }
    )
}