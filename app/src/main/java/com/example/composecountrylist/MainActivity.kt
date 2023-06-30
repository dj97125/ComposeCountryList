package com.example.composecountrylist


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.danielcaballero.mynewcompose.ui.theme.MyNewComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyNewComposeTheme {
                Surface {
                    modifier = Modifier.fillMaxSize(),
                    color
                }

            }
        }
        setContentView(R.layout.activity_main)
    }
}