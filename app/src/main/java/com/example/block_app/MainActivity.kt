package com.example.block_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.block_app.ui.theme.BlockappTheme
import com.example.block_app.ui.view.HomePage
import com.example.block_app.ui.view.navigation.navigation



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
                Screens()

            }
        }
}

@Composable
fun Screens() {
    HomePage(navigation())
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BlockappTheme {
        HomePage(navigation())
    }
}