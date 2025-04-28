package com.devalere.code_with_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("work") { WorkScreen() }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFE3F2FD)
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Challenge #1",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Applying HTML & CSS basics with a simple card layout!",
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { navController.navigate("work") }) {
                Text("See the Result 🚀")
            }
        }
    }
}

@Composable
fun WorkScreen() {
    var isZoomed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isZoomed) 2f else 1f,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 1500)
    )
    var buttonLabel by remember { mutableStateOf("Zoom In 🔍") }
    // Automatically start zooming out after 2 secongs
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        isZoomed = true
        buttonLabel = "Zoom Out 🔍"
    }

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Centered Image
            Image(
                painter = painterResource(id = R.drawable.sample_card),
                contentDescription = "Your Work",
                modifier = Modifier
                    .align(Alignment.Center)
                    .scale(scale)
                    .size(200.dp)
                    .background(Color.LightGray, RoundedCornerShape(16.dp))
            )

            //Toggle Button
            Button(
                onClick = {
                    isZoomed = !isZoomed
                    buttonLabel = if (isZoomed) "Zoom Out 🔍" else "Zoom In 🔍"
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = buttonLabel,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}











