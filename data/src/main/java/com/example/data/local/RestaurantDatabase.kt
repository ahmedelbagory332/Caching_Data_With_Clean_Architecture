package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.data.local.entities.RestaurantEntity

@Database(entities = [RestaurantEntity::class], version = 1, exportSchema = false)
    abstract class RestaurantDatabase : RoomDatabase() {

        abstract fun restaurantDao(): RestaurantDao
    }
