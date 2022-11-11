package com.example.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "restaurants")

data class Restaurant(
    @PrimaryKey val name: String,
    val address: String,
    val description: String,
    val id: Int,
    val logo: String,
    val phoneNumber: String,
    val review: String,
    val type: String,
    val uid: String
)

