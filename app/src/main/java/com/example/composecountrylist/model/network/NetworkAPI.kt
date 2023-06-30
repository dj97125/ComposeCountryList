package com.example.composecountrylist.model.network

import com.example.composecountrylist.common.END_POINT_COUNTRIES
import com.example.composecountrylist.model.network.country_response.CountriesResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface NetworkAPI {

    @GET(END_POINT_COUNTRIES)
    suspend fun getCountriesList(): Response<List<CountriesResponseItem>>
}