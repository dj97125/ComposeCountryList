package com.example.composecountrylist.ui.util

sealed class Screen(val route: String) {
    object CountryList : Screen("country_list_view")
    object CountryDetail : Screen("country_details_view")
}


