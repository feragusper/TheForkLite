package com.feragusper.theforklite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feragusper.theforklite.domain.Restaurant
import com.feragusper.theforklite.usecase.FetchRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel @Inject internal constructor(
    private val fetchRestaurantUseCase: FetchRestaurantUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RestaurantDetailUiState>(RestaurantDetailUiState.Loading)

    val uiState: StateFlow<RestaurantDetailUiState> = _uiState

    fun fetchRestaurant(id: String) {
        viewModelScope.launch {
            fetchRestaurantUseCase(id)
                .catch { e ->
                    _uiState.value = RestaurantDetailUiState.Error(e)
                }
                .collect { restaurant ->
                    _uiState.value = RestaurantDetailUiState.Success(restaurant)
                }
        }
    }
}

sealed class RestaurantDetailUiState {
    object Loading : RestaurantDetailUiState()
    data class Success(val restaurant: Restaurant) : RestaurantDetailUiState()
    data class Error(val exception: Throwable) : RestaurantDetailUiState()
}
