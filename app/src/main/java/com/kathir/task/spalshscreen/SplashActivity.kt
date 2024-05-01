package com.kathir.task.spalshscreen
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kathir.task.R
import com.kathir.task.colors.ui.MainActivity
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen() // Pass context to SplashScreen
        }
    }
}

@Composable
fun SplashScreen() {
    var navigateToMain by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2000) // Splash screen duration: 2 seconds
        navigateToMain = true
    }

    if (navigateToMain) {
        // Start MainActivity
        val context = LocalContext.current
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        (context as ComponentActivity).finish() // Finish SplashActivity
    } else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Image background
            Image(
                painter = painterResource(id = R.drawable.splash_image), // Your image resource
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize()
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultView() {
    SplashScreen()
}
