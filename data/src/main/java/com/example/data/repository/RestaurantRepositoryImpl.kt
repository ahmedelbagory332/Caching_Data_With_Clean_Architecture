package com.example.data.repository

import androidx.room.withTransaction
import com.example.data.remote.RestaurantApi
import com.example.data.remote.RestaurantDatabase
import com.example.domain.dto.RestaurantDto
import com.example.domain.entities.Restaurant
import com.example.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi,
    private val db: RestaurantDatabase,
) : RestaurantRepository {

    override suspend fun getRemoteRestaurant(size:String): List<RestaurantDto> {
        return api.getRestaurants(size)
    }

    override suspend fun getLocalRestaurant(): Flow<List<Restaurant>> {
        return db.restaurantDao().getAllRestaurants()
    }

    override suspend fun cacheRestaurant(restaurant:List<Restaurant>) {
        db.withTransaction {
            db.restaurantDao().deleteAllRestaurants()
            db.restaurantDao().insertRestaurants(restaurant)
        }
    }


}