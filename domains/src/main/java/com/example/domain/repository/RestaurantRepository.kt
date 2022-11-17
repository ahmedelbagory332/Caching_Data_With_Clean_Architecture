package com.example.domain.repository

import com.example.domain.RestaurantModel
import kotlinx.coroutines.flow.Flow


interface RestaurantRepository {

    suspend fun getRemoteRestaurant(size:String):List<RestaurantModel>

    suspend fun getLocalRestaurant(): Flow<List<RestaurantModel>>

    suspend fun cacheRestaurant(restaurant:List<RestaurantModel> )

}