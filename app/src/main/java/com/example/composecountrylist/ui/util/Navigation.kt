package com.example.composecountrylist.ui.util

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composecountrylist.domain.Country
import com.example.composecountrylist.ui.views.CountryDetailsView
import com.example.composecountrylist.ui.views.CountryListView
import com.example.composecountrylist.view_model.NetworkViewModel

@Composable
fun Navigation(darkTheme: Boolean) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<NetworkViewModel>()
    NavHost(
        navController = navController,
        startDestination = Screen.CountryList.route
    ) {

        composable(route = Screen.CountryList.route) {


            CountryListView(
                viewModel = viewModel,
                darkTheme = darkTheme,
                onNavigate =  { navController.navigate(Screen.CountryDetail.route) }
//                onNavigate = {
//                    navController.navigate(Screen.CountryDetail.createRoute(it))
//                }
            )

        }
        composable(
            route = Screen.CountryDetail.route,
        ) {
            CountryDetailsView(viewModel = viewModel)
        }


    }
}
