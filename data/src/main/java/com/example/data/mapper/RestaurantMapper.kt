package com.example.data.mapper

import com.example.data.local.entities.RestaurantEntity
import com.example.data.remote.dto.RestaurantDto
import com.example.domain.model.RestaurantModel
import javax.inject.Inject


class RestaurantMapper  @Inject constructor(){

     fun fromRemoteRestaurantToRestaurantModel(obj: RestaurantDto): RestaurantModel {

        return RestaurantModel(
            name = obj.name,
            address = obj.address,
            logo = obj.logo,
            type = obj.type,
        )
    }

    fun fromLocalRestaurantToRestaurantModel(obj: RestaurantEntity): RestaurantModel {

        return RestaurantModel(
            name = obj.name,
            address = obj.address,
            logo = obj.logo,
            type = obj.type,
        )
    }

    fun fromRestaurantModelToLocalRestaurant(obj: RestaurantModel): RestaurantEntity {

        return RestaurantEntity(
            name = obj.name,
            address = obj.address,
            logo = obj.logo,
            type = obj.type,
        )
    }


}