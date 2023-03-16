package com.hakaninc.kisilerapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.hakaninc.kisilerapp.entity.Persons
import com.hakaninc.kisilerapp.ui.theme.KisilerAppTheme
import com.hakaninc.kisilerapp.viewmodel.HomePageViewModel
import com.hakaninc.kisilerapp.viewmodelfactory.HomePageViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


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
    NavHost(navController = navController, startDestination = "home_page") {

        composable("home_page") {
            HomePage(navController = navController)
        }
        composable("person_record") {
            PersonRecord(navController)
        }
        composable("person_detail/{person}", arguments = listOf(
            navArgument("person"){type = NavType.StringType}
        )) {
            val personJson = it.arguments?.getString("person")
            val person = Gson().fromJson(personJson, Persons::class.java)
            PersonDetail(person = person,navController)
        }

    }

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomePage(navController: NavController) {

    val context = LocalContext.current
    val viewModel : HomePageViewModel = viewModel(
        factory = HomePageViewModelFactory(context.applicationContext as Application)
    )
    // FIXME: Bunun ile viewmodelime application gönderirim.

    val searchCheck = remember {
        mutableStateOf(false)
    }
    val tf = remember {
        mutableStateOf("")
    }
    val titleControl = remember {
        mutableStateOf(true)
    }
    val personList = viewModel.personsList.observeAsState(listOf())

    val animatedProgress = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true){
        viewModel.getAllPersons()
        animatedProgress.animateTo(
            targetValue = 360f,
            animationSpec = tween(durationMillis = 750)
        )
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
                            viewModel.searchPerson(tf.value)
                        }, label = {
                            Text(text = "Search", color = Color.Black)
                        }, colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,
                            backgroundColor = Color.White,
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
        }, content = {


            LazyColumn {
                items(
                    count = personList.value!!.count(),
                    itemContent = {
                        val person = personList.value!![it]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .graphicsLayer(
                                    rotationX = animatedProgress.value
                                )
                        ) {
                            Row(modifier = Modifier.clickable {
                                val personJson = Gson().toJson(person)
                                navController.navigate("person_detail/$personJson")
                            }) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(text = person.person_name +" - ${person.person_tel}")
                                        IconButton(onClick = {
                                            viewModel.personDelete(person)
                                        }) {
                                            Icon(painter = painterResource(id = R.drawable.ic_baseline_delete_outline_24), contentDescription = null,
                                            tint = Color.Gray)
                                        }
                                    }
                                }
                            }
                        }
                    }
                )
            }

        }, floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("person_record")
            }, backgroundColor = Color.Black, content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_person_add_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }, modifier = Modifier.graphicsLayer(
                    rotationY = animatedProgress.value
                    ))
        })
    val activity = (LocalContext.current as Activity)
    BackHandler(onBack = {
        if (searchCheck.value) {
            searchCheck.value = false
            titleControl.value = true
            tf.value = ""
        } else {
            activity.finish()
        }
    })
}










