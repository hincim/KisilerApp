package com.hakaninc.kisilerapp

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hakaninc.kisilerapp.entity.Persons
import com.hakaninc.kisilerapp.viewmodel.*

@Composable
fun PersonDetail(person: Persons,navController: NavController) {

    val tfNameController = remember {
        mutableStateOf("")
    }
    val tfTelController = remember {
        mutableStateOf("")
    }

    val localFocusManager = LocalFocusManager.current

    val context = LocalContext.current
    val viewModel : PersonDetailViewModel = viewModel(
        factory = PersonDetailViewModelFactory(context.applicationContext as Application)
    )

    LaunchedEffect(key1 = true){
        tfNameController.value = person.person_name
        tfTelController.value = person.person_tel
    }

    Scaffold(
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

                TextField(value = tfNameController.value, onValueChange = {
                    tfNameController.value = it
                }, label = {
                    Text(text ="Person Name")
                })
                TextField(value = tfTelController.value, onValueChange = {
                    tfTelController.value = it
                }, label = {
                    Text(text ="Person Tel")
                })
                Button(onClick = {
                    localFocusManager.clearFocus()
                    val personName = tfNameController.value
                    val personTel = tfTelController.value
                    viewModel.personUpdate(person.person_id,personName,personTel)
                    navController.popBackStack()
                }) {
                    Text(text = "Update")
                }
            }
        }
    )
}