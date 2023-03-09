package com.hakaninc.kisilerapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.hakaninc.kisilerapp.entity.Persons
import com.hakaninc.kisilerapp.viewmodel.PersonRecordViewModel

@Composable
fun PersonRecord(navController: NavController) {

    val tfNameController = remember {
        mutableStateOf("")
    }
    val tfTelController = remember {
        mutableStateOf("")
    }
    var viewModel : PersonRecordViewModel = viewModel()

    val localFocusManager = LocalFocusManager.current
    // geri tuşuna basınca texfieldları kapatmadan hemen geri dönerim.
    // 2 kere tıklama yapmamak için.

    Scaffold(
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
                    val name = tfNameController.value
                    val tel = tfTelController.value
                    viewModel.personRecord(name,tel)

                }, modifier = Modifier.size(250.dp,50.dp)) {
                    Text(text = "Save")
                }
            }
        }
    )
}