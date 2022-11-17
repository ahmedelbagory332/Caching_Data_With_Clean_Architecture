package com.example.domain.use_case

import com.example.domain.utils.Resource
import com.example.domain.model.RestaurantModel
import com.example.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRestaurantsUseCase  @Inject constructor(
    private val repository: RestaurantRepository,

    ) {
    operator fun invoke(size:String): Flow<Resource<List<RestaurantModel>>> = flow {
        try {
            emit(Resource.Loading<List<RestaurantModel>>())
               repository.cacheRestaurant(repository.getRemoteRestaurant(size))
                 repository.getLocalRestaurant().collect {
                emit(Resource.Success<List<RestaurantModel>>(it))
               }
        } catch(e: Exception) {
            emit(Resource.Error<List<RestaurantModel>>("${e.localizedMessage} : An unexpected error happened"))
            repository.getLocalRestaurant().collect {
                emit(Resource.Success<List<RestaurantModel>>(it))
            }
        }
    }
}