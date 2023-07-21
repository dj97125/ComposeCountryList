package com.example.composecountrylist.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Country(
    val capital: String?,
    val code: String?,
    val name: String?,
    val region: String?
) : Parcelable