package com.example.composecountrylist.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composecountrylist.common.StateAction
import com.example.composecountrylist.view_model.NetworkViewModel


@Composable
fun CountryListView(viewModel: NetworkViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)) {

        val state by viewModel.countryResponse.collectAsState()
        val composeState = viewModel.countryResponseeCompose

        when(state) {
            is StateAction.ERROR -> TODO()
            StateAction.LOADING -> TODO()
            is StateAction.SUCCESS<*> -> TODO()
            else -> {}
        }

    }
}

@Composable
fun RecyclerView() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {


    }
}

@Composable
fun ItemCountry() {
    Card(
        border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Column() {
            
        }

    }
    
}