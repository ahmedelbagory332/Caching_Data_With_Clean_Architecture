package com.example.presentation.ui

import androidx.lifecycle.*
import com.example.domain.utils.ScreenStats
import com.example.domain.use_case.GetRestaurantsUseCase
import com.example.domain.utils.Resource
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
                is Resource.Success -> {
                    _state.value = ScreenStats(
                        restaurants = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = ScreenStats(
                        error = result.message ?: "An unexpected error happened"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ScreenStats(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}