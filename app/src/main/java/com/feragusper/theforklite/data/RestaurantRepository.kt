package com.feragusper.theforklite.data

import com.feragusper.theforklite.data.api.RestaurantAPIService
import com.feragusper.theforklite.domain.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class RestaurantRepository @Inject constructor(
    private val restaurantAPIService: RestaurantAPIService,
) {
    suspend fun fetchRestaurant(id: String): Flow<Restaurant> {
        return flow {
            emit(restaurantAPIService.getRestaurantDetail(restaurantId = id).toRestaurant())
        }.flowOn(Dispatchers.IO)
    }

}
