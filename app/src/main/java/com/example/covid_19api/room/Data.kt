package com.example.covid_19api.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COVID-19")
data class Data(
    @PrimaryKey(autoGenerate = true) val pk:Int,
    val country: String,
    val confirmed: Int,
    val death: Int
)
