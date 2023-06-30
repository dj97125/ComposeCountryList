package com.example.composecountrylist.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "capital")
    val capital: String?,
    @ColumnInfo(name = "code")
    val code: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "region")
    val region: String?
)
