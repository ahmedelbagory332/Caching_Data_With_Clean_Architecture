package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "restaurants")

data class RestaurantEntity(
    @PrimaryKey val name: String,
    val address: String,
    val logo: String,
    val type: String,
)

