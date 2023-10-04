package com.example.viewmodal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.example.viewmodal.ui.theme.ViewModalTheme

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = viewModel<MyViewModel>()
//                    val count = viewModel.count
                    val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") {
                    FirstScreen(viewModel,navController = navController)
                }

                composable("second") {

                    SecondScreen(viewModel, navController)
                }
                }

                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {


//                        MyButton(currentCount = count) {
//                            viewModel.increaseCount()
//                        }


                    }
                }
            }
        }
    }
}

class MyViewModel : ViewModel() {

//    var count by mutableStateOf(65)
//
//    fun increaseCount() {
//        count++
//    }

    var name by mutableStateOf("")

    fun changeName(givenName : String) {
        name = givenName;
    }

}

@Composable
fun FirstScreen(viewModel: MyViewModel, navController: NavController) {
    val name = viewModel.name

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Name Value: $name", fontSize = 24.sp)
        Button(onClick = { navController.navigate("second") }) {
            Text(text = "Go to Second Screen")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(viewModel: MyViewModel, navController: NavController) {
    var name = viewModel.name;

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = name, onValueChange = {
            name = it;
            viewModel.changeName(name);
        })


        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Go Back to First Screen")
        }
    }
}



//@Composable
//fun MyButton(currentCount: Int, updateCount: (Int) -> Unit) {
//    Button(
//        onClick = {
//            updateCount(currentCount)
//        },
//        contentPadding = PaddingValues(16.dp),
//        border = BorderStroke(10.dp, Color.Black),
//        colors = ButtonDefaults.textButtonColors(
//            containerColor = Color.Green,
//            contentColor = Color.White
//        )
//    ) {
//        Text(
//            "Count is : $currentCount",
//            style = MaterialTheme.typography.headlineSmall,
//            modifier = Modifier.padding(5.dp)
//        )
//    }
//}


