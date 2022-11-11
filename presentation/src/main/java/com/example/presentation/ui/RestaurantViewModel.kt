package com.example.presentation.ui

import androidx.lifecycle.*
import com.example.domain.ScreenStats
import com.example.domain.use_case.GetRestaurantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class RestaurantViewModel  @Inject constructor(
      val getRestaurantsUseCase: GetRestaurantsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ScreenStats>(ScreenStats())

    val restaurants: LiveData<ScreenStats>
        get() = _state.asLiveData()



     fun getRestaurant(size:String) {
        getRestaurantsUseCase(size).onEach { result ->
            when (result) {
                is com.example.domain.Resource.Success -> {
                    _state.value = com.example.domain.ScreenStats(
                        restaurants = result.data ?: emptyList()
                    )
                }
                is com.example.domain.Resource.Error -> {
                    _state.value = com.example.domain.ScreenStats(
                        error = result.message ?: "An unexpected error happened"
                    )
                }
                is com.example.domain.Resource.Loading -> {
                    _state.value = com.example.domain.ScreenStats(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}