package com.hakaninc.kisilerapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    PageChange()
                }
            }
        }
    }
}

@Composable
fun PageChange() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home_page"){

        composable("home_page"){
            HomePage(navController = navController)
        }
        composable("person_record"){
            PersonRecord()
        }
        composable("person_detail"){
            PersonDetail()
        }
    }
    
}

@Composable
fun HomePage(navController: NavController) {

    val searchCheck = remember {
        mutableStateOf(false)
    }
    val tf = remember {
        mutableStateOf("")
    }
    val titleControl = remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                contentColor = Color.Black,
                title = {
                    if (titleControl.value){
                        Text(text = "Persons")
                    }else{
                        TextField(value = tf.value, onValueChange = {
                            tf.value = it
                        }, label = {
                            Text(text = "Search", color = Color.Black)
                        }, colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.White
                        ))
                    }
                }
            , actions = {

                IconButton(onClick = {
                    searchCheck.value = true
                }) {
                    when(searchCheck.value){
                        false -> Icon(painter = painterResource(id = R.drawable.ic_baseline_search_24),
                            contentDescription = null, modifier = Modifier.clickable {
                                searchCheck.value = true
                                titleControl.value = false
                            })
                        true -> Icon(painter = painterResource(id = R.drawable.ic_baseline_cancel_24),
                            contentDescription = null, modifier = Modifier.clickable {
                                searchCheck.value = false
                                titleControl.value = true
                                tf.value = ""
                            })
                    }
                }
                })
        }
    , content = {

        }, floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("person_record")
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_add_24), contentDescription = null,)
            }
        })
    val activity = (LocalContext.current as Activity)
    BackHandler(onBack = {
        if (searchCheck.value){
            searchCheck.value = false
            titleControl.value = true
            tf.value = ""
        }else{
            activity.finish()
        }
    })
}










