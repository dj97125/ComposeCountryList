package com.example.composecountrylist


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.composecountrylist.ui.CountryComposeApp
import com.example.composecountrylist.view_model.NetworkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: NetworkViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CountryComposeApp(
                darkTheme = isSystemInDarkTheme(),
                viewModel = viewModel
            )
        }

    }

}