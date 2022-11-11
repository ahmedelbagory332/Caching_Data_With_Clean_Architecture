package com.example.domain.repository

import com.example.domain.dto.RestaurantDto
import com.example.domain.entities.Restaurant
import kotlinx.coroutines.flow.Flow


interface RestaurantRepository {

    suspend fun getRemoteRestaurant(size:String):List<RestaurantDto>

    suspend fun getLocalRestaurant(): Flow<List<Restaurant>>

    suspend fun cacheRestaurant(restaurant:List<Restaurant> )

}