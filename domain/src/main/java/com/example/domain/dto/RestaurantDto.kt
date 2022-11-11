package com.example.domain.dto

import com.example.domain.entities.Restaurant
import com.google.gson.annotations.SerializedName

data class RestaurantDto(
    val address: String,
    val description: String,
    val hours: Hours,
    val id: Int,
    val logo: String,
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val review: String,
    val type: String,
    val uid: String
)

fun RestaurantDto.toRestaurant(): Restaurant {
    return Restaurant(
        name = name,
        address = address,
        description = description,
        id = id,
        logo = logo,
        phoneNumber = phoneNumber,
        review = review,
        type = type,
        uid = uid
    )
}