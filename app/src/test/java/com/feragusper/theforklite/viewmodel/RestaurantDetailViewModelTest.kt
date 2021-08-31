package com.feragusper.theforklite.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.feragusper.theforklite.common.MainCoroutineRule
import com.feragusper.theforklite.domain.Restaurant
import com.feragusper.theforklite.usecase.FetchRestaurantUseCase
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
 * Unit tests for the implementation of [RestaurantDetailViewModel].
 */
@RunWith(MockitoJUnitRunner::class)
class RestaurantDetailViewModelTest {

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

    private lateinit var restaurantDetailViewModel: RestaurantDetailViewModel

    @Mock
    private lateinit var fetchRestaurantUseCase: FetchRestaurantUseCase

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        restaurantDetailViewModel = RestaurantDetailViewModel(fetchRestaurantUseCase)
    }

    @ExperimentalTime
    @ExperimentalCoroutinesApi
    @Test
    fun fetchRestaurantSuccess() {
        mainCoroutineRule.runBlockingTest {
            BDDMockito.given(fetchRestaurantUseCase(testRestaurantId)).willReturn(flow {
                emit(testRestaurant)
            })

            restaurantDetailViewModel.fetchRestaurant(testRestaurantId)

            restaurantDetailViewModel.uiState.test {
                Truth.assertThat(awaitItem()).isEqualTo(RestaurantDetailUiState.Success(testRestaurant))
            }
        }

    }

    @ExperimentalTime
    @ExperimentalCoroutinesApi
    @Test
    fun fetchRestaurantError() {
        mainCoroutineRule.runBlockingTest {
            val runtimeException = RuntimeException()
            BDDMockito.given(fetchRestaurantUseCase(testRestaurantId)).willReturn(flow {
                throw runtimeException
            })

            restaurantDetailViewModel.fetchRestaurant(testRestaurantId)

            restaurantDetailViewModel.uiState.test {
                Truth.assertThat(awaitItem()).isEqualTo(RestaurantDetailUiState.Error(runtimeException))
            }
        }

    }

}
