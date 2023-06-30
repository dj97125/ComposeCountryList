package com.example.composecountrylist



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composecountrylist.ui.theme.MyNewComposeTheme
import com.example.composecountrylist.ui.CountryListView
import com.example.composecountrylist.view_model.NetworkViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyNewComposeTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = viewModel<NetworkViewModel>()
                    CountryListView(viewModel = viewModel )
                }

            }
        }

    }
}