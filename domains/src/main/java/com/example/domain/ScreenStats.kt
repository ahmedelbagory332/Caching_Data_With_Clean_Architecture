package com.example.domain



data class ScreenStats (
    val isLoading: Boolean = false,
    val restaurants: List<RestaurantModel> = emptyList(),
    val error: String = ""
)

