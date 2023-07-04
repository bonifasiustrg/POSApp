package com.takasima.posapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.takasima.posapp.ui.screen.LoginScreen
import com.takasima.posapp.ui.screen.RegisterScreen
import com.takasima.posapp.ui.screen.WelcomeScreen
import com.takasima.posapp.ui.screen.owner.POSApp
import com.takasima.posapp.ui.theme.POSAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POSAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val navController = rememberNavController()

                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome_screen", builder = {
        composable("welcome_screen", content = { WelcomeScreen(navController = navController) })
        composable("login_screen", content = { LoginScreen(navController = navController) })
//        composable("register_screen", content = { RegisterScreen(navController = navController) })
        composable("posapp", content = { POSApp() })
    })
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    POSAppTheme {

    }
}