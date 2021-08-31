package com.feragusper.theforklite.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.feragusper.theforklite.common.MainCoroutineRule
import com.feragusper.theforklite.data.RestaurantRepository
import com.feragusper.theforklite.domain.Restaurant
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.ExperimentalTime

/**
 * Unit tests for the implementation of [FetchRestaurantUseCase].
 */
@RunWith(MockitoJUnitRunner::class)
class FetchRestaurantUseCaseTest {

    private val testRestaurant = Restaurant(
        picsDiaporamaImageUrls = listOf("url1", "url2"),
        name = "Mirazur",
        minPrice = 50F,
        currencyCode = "EUR",
        avgRate = "9.3",
        tripAdvisorAvgRating = 9F,
        tripAdvisorReviewCount = 999,
        rateDistinction = "Incredible",
        speciality = "Mediterranean",
        cardMain1 = "Steak",
        cardDessert1 = "Pudin",
        cardStart1 = "Salad",
        priceCardDessert1 = 10F,
        priceCardMain1 = 20F,
        priceCardStart1 = 15F
    )
    private val testRestaurantId = "1234"

    private lateinit var fetchRestaurantUseCase: FetchRestaurantUseCase

    @Mock
    private lateinit var restaurantRepository: RestaurantRepository

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        fetchRestaurantUseCase = FetchRestaurantUseCase(restaurantRepository)
    }

    @ExperimentalTime
    @ExperimentalCoroutinesApi
    @Test
    fun fetchRestaurantSuccess() {
        mainCoroutineRule.runBlockingTest {
            BDDMockito.given(restaurantRepository.fetchRestaurant(testRestaurantId)).willReturn(flow {
                emit(testRestaurant)
            })

            restaurantRepository.fetchRestaurant(testRestaurantId).collect { restaurant ->
                Truth.assertThat(restaurant).isEqualTo(testRestaurant)
            }
        }

    }

}
