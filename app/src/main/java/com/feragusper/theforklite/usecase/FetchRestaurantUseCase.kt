package com.feragusper.theforklite.usecase

import com.feragusper.theforklite.data.RestaurantRepository
import com.feragusper.theforklite.domain.Restaurant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchRestaurantUseCase @Inject constructor(private val repository: RestaurantRepository) {

    suspend operator fun invoke(id: String): Flow<Restaurant> = repository.fetchRestaurant(id = id)

}