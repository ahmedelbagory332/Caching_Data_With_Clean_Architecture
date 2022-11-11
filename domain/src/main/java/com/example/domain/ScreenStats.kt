package com.example.domain

import com.example.domain.entities.Restaurant


data class ScreenStats (
    val isLoading: Boolean = false,
    val restaurants: List<Restaurant> = emptyList(),
    val error: String = ""
)

