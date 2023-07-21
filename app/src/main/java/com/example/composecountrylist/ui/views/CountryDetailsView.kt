package com.example.composecountrylist.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composecountrylist.ui.theme.Typography
import com.example.composecountrylist.view_model.NetworkViewModel

@Composable
fun CountryDetailsView(viewModel: NetworkViewModel) {
    CountryDetailBody(viewModel = viewModel)
}

@Composable
fun CountryDetailBody(viewModel: NetworkViewModel, modifier: Modifier = Modifier) {

    val country = viewModel.countryDetails
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = country?.name ?: "N/A",
            modifier = modifier
                .width(87.dp)
                .padding(16.dp),
            textAlign = TextAlign.Center,
            style = Typography.labelMedium
        )

    }

}