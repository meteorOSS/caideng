package com.zsenhe.caideng

import GameScreen
import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.zsenhe.caideng.screen.AboutScreen
import com.zsenhe.caideng.screen.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var showSplashScreen by remember { mutableStateOf(true) }

            if(showSplashScreen){
                SplashScreen(onSplashEnded = { showSplashScreen = false })
            }else {
                Main {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") { MainScreen(navController) }
                        composable("game") { GameScreen(navController) }
                        composable("about") { AboutScreen(navController) }
                    }
                }
            }


        }
    }
}

@Composable
fun Main(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}