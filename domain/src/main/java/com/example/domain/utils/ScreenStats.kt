package com.example.domain.utils

import com.example.domain.model.RestaurantModel


data class ScreenStats (
    val isLoading: Boolean = false,
    val restaurants: List<RestaurantModel> = emptyList(),
    val error: String = ""
)

