package com.example.composecountrylist.model.network.country_response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountriesResponseItem(
    @SerialName("capital")
    val capital: String?,
    @SerialName("code")
    val code: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("region")
    val region: String?
)

@Serializable
data class Currency(
    @SerialName("code")
    val code: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("symbol")
    val symbol: String?
)
@Serializable
data class Language(
    @SerialName("code")
    val code: String?,
    @SerialName("iso639_2")
    val iso6392: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("nativeName")
    val nativeName: String?
)