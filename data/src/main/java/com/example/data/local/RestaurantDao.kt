package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurants")
    fun getAllRestaurants(): Flow<List<Restaurant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants: List<Restaurant>)

    @Query("DELETE FROM restaurants")
    suspend fun deleteAllRestaurants()
}