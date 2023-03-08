package com.hakaninc.kisilerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.hakaninc.kisilerapp.ui.theme.KisilerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KisilerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomePage()
                }
            }
        }
    }
}

@Composable
fun HomePage() {

    val searchCheck = remember {
        mutableStateOf(false)
    }
    val tf = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Persons")
                }
            , actions = {
                IconButton(onClick = {
                    searchCheck.value = true
                }) {
                    when(searchCheck.value){
                        false -> Icon(painter = painterResource(id = R.drawable.ic_baseline_search_24),
                            contentDescription = null, modifier = Modifier.clickable {
                                searchCheck.value = true
                            })
                        true -> Icon(painter = painterResource(id = R.drawable.ic_baseline_cancel_24),
                            contentDescription = null, modifier = Modifier.clickable {
                                searchCheck.value = false
                            })
                    }
                }
                })
        }
    , content = {

        }, floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {

            }
        })
}










