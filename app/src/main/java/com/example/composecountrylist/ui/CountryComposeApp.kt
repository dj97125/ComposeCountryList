package com.example.composecountrylist.ui

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composecountrylist.view_model.NetworkViewModel

@Composable
fun CountryComposeApp(
    darkTheme: Boolean,
    dynamicColor: Boolean = true,
    context: Context,
    viewModel: NetworkViewModel
) {

    CountryTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CountryListView(viewModel = viewModel, context = context, darkTheme = darkTheme)
        }
    }
}