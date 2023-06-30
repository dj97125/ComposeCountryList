package com.example.composecountrylist.model.network.country_response

import com.google.gson.annotations.SerializedName


data class CountriesResponseItem(
    val capital: String?,
    val code: String?,
    val name: String?,
    val region: String?
)

data class Currency(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?
)

data class Language(
    @SerializedName("code")
    val code: String?,
    @SerializedName("iso639_2")
    val iso6392: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nativeName")
    val nativeName: String?
)