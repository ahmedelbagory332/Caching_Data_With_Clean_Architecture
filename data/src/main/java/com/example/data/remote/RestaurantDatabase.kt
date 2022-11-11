package com.example.data.remote

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.data.local.RestaurantDao
import com.example.domain.entities.Restaurant

@Database(entities = [Restaurant::class], version = 1, exportSchema = false)
    abstract class RestaurantDatabase : RoomDatabase() {

        abstract fun restaurantDao(): RestaurantDao
    }
