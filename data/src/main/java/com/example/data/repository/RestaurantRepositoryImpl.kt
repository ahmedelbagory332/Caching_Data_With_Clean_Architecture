package com.example.data.repository

import androidx.room.withTransaction
import com.example.data.mapper.RestaurantMapper
import com.example.data.remote.RestaurantApi
import com.example.data.local.RestaurantDatabase
import com.example.domain.model.RestaurantModel
import com.example.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi,
    private val db: RestaurantDatabase,
    private val restaurantMapper: RestaurantMapper,
) : RestaurantRepository {

    override suspend fun getRemoteRestaurant(size:String): List<RestaurantModel> {
        return api.getRestaurants(size).map { restaurantMapper.fromRemoteRestaurantToRestaurantModel(it) }
    }

    override suspend fun getLocalRestaurant(): Flow<List<RestaurantModel>> {
        return db.restaurantDao().getAllRestaurants()
            .map { it->
                it.map {
                    restaurantMapper.fromLocalRestaurantToRestaurantModel(it)
                }

            }
    }


    override suspend fun cacheRestaurant(restaurant:List<RestaurantModel>) {
        db.withTransaction {
            db.restaurantDao().deleteAllRestaurants()
            db.restaurantDao()
                .insertRestaurants(
                    restaurant.map { restaurantMapper.fromRestaurantModelToLocalRestaurant(it) }
                    )
        }
    }


}