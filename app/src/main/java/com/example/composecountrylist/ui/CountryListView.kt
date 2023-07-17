package com.example.composecountrylist.ui

import android.app.AlertDialog
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.example.composecountrylist.common.StateAction
import com.example.composecountrylist.common.toast
import com.example.composecountrylist.domain.Country
import com.example.composecountrylist.ui.theme.DarkColorPalette
import com.example.composecountrylist.ui.theme.LightColorPalette
import com.example.composecountrylist.ui.util.ShimmerListItem
import com.example.composecountrylist.view_model.NetworkViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun CountryListView(
    darkTheme: Boolean,
    viewModel: NetworkViewModel,
    context: Context,
    lifecycle: LifecycleOwner = LocalLifecycleOwner.current
) {

    val state by viewModel.countryResponse.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()


    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = { viewModel.getCountryList(isRefreshing = true) },
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                scale = true,
                contentColor = if (darkTheme) DarkColorPalette.primary else LightColorPalette.primary,
            )
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            when (val currentState = state) {
                is StateAction.ERROR -> {
                    val isRetry = displayErrors(context) {
                        viewModel.getCountryList()
                    }

                    if (isRetry) StartShimmer()
                }

                StateAction.LOADING -> {
                    StartShimmer()

                }

                is StateAction.SUCCESS<*> -> {
                    val retrievedCountries = currentState.response as List<Country>
                    RecyclerView(countriesForPopulate = retrievedCountries, darkTheme = darkTheme)
                    if (!isRefreshing) context.toast("Success")
                }

                else -> {}
            }

        }
    }
}

@Composable
fun StartShimmer() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(20) {
            ShimmerListItem(
                isLoading = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }

}

fun displayErrors(
    context: Context,
    message: String = "A moment please! Working on the issues",
    retry: () -> Unit
): Boolean {
    var isRetry = false
    AlertDialog.Builder(context)
        .setTitle("Error occurred")
        .setPositiveButton("RETRY") { dialog, _ ->
            dialog.dismiss()
            retry()
            isRetry = true
        }
        .setNegativeButton("DISMISS") { dialog, _ ->
            dialog.dismiss()
        }
        .setMessage(message)
        .create()
        .show()

    return isRetry
}


@Composable
fun RecyclerView(countriesForPopulate: List<Country>, darkTheme: Boolean) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        countriesForPopulate.forEach { country ->
            item {
                ItemCountry(country = country, darkTheme = darkTheme)
            }
        }
    }
}

@Composable
fun CustomText(text: String, modifier: Modifier = Modifier, darkTheme: Boolean) {
    Text(
        text = text,
        color = if (darkTheme) DarkColorPalette.primary else LightColorPalette.primary,
        softWrap = true,
        fontSize = 15.sp,
        modifier = modifier
            .width(87.dp)
            .padding(16.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h6
    )
}


@Composable
fun ItemCountry(
    country: Country,
    darkTheme: Boolean
//    onCountrySelected: (CountriesResponseItem) -> Unit
) {

    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                CustomText(
                    text = "Country",
                    darkTheme = darkTheme
                )
                CustomText(
                    text = country.name ?: "N/A",
                    darkTheme = darkTheme

                )
                CustomText(
                    text = "Region",
                    darkTheme = darkTheme
                )
                CustomText(
                    text = country.region ?: "N/A",
                    darkTheme = darkTheme

                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                CustomText(
                    text = "Capital",
                    darkTheme = darkTheme
                )
                CustomText(
                    text = country.capital ?: "N/A",
                    darkTheme = darkTheme

                )
                CustomText(
                    text = "Code",
                    darkTheme = darkTheme
                )
                CustomText(
                    text = country.code ?: "N/A",
                    darkTheme = darkTheme
                )
            }
        }
    }


}