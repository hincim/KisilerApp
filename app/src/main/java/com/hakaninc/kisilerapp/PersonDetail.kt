package com.hakaninc.kisilerapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun PersonDetail() {

    val tfNameController = remember {
        mutableStateOf("")
    }
    val tfTelController = remember {
        mutableStateOf("")
    }

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
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Update")
                }
            }
        }
    )
}