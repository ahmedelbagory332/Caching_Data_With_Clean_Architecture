package com.example.domain.use_case

import com.example.domain.Resource
import com.example.domain.dto.toRestaurant
import com.example.domain.entities.Restaurant
import com.example.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRestaurantsUseCase  @Inject constructor(
    private val repository: RestaurantRepository,

    ) {
    operator fun invoke(size:String): Flow<Resource<List<Restaurant>>> = flow {
        try {
            emit(Resource.Loading<List<Restaurant>>())
             val remoteRestaurant = repository.getRemoteRestaurant(size).map { it.toRestaurant() }
                 repository.cacheRestaurant(remoteRestaurant)
                 repository.getLocalRestaurant().collect {
                emit(Resource.Success<List<Restaurant>>(it))
               }
        } catch(e: Exception) {
            emit(Resource.Error<List<Restaurant>>("${e.localizedMessage} : An unexpected error happened"))
            repository.getLocalRestaurant().collect {
                emit(Resource.Success<List<Restaurant>>(it))
            }
        }
    }
}