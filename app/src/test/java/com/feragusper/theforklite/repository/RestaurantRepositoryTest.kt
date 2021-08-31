package com.feragusper.theforklite.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.feragusper.theforklite.common.MainCoroutineRule
import com.feragusper.theforklite.data.RestaurantRepository
import com.feragusper.theforklite.data.api.RestaurantAPIService
import com.feragusper.theforklite.data.api.RestaurantDetailEntity
import com.feragusper.theforklite.data.api.RestaurantEntity
import com.feragusper.theforklite.domain.Restaurant
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
 * Unit tests for the implementation of [RestaurantRepository].
 */
@RunWith(MockitoJUnitRunner::class)
class RestaurantRepositoryTest {

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

    private val testRestaurantDetailEntity = RestaurantDetailEntity(
        data = RestaurantEntity(
            picsDiaporama = listOf(RestaurantEntity.PicEntity("url1"), RestaurantEntity.PicEntity("url2")),
            name = "Mirazur",
            minPrice = 50F,
            currencyCode = "EUR",
            avgRate = 9.3F,
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
    )
    private val testRestaurantId = "1234"

    private lateinit var restaurantRepository: RestaurantRepository

    @Mock
    private lateinit var restaurantAPIService: RestaurantAPIService

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        restaurantRepository = RestaurantRepository(restaurantAPIService)
    }

    @ExperimentalTime
    @ExperimentalCoroutinesApi
    @Test
    fun fetchRestaurantSuccess() {
        mainCoroutineRule.runBlockingTest {
            BDDMockito.given(restaurantAPIService.getRestaurantDetail(restaurantId = testRestaurantId)).willReturn(testRestaurantDetailEntity)

            restaurantRepository.fetchRestaurant(testRestaurantId).test {
                Truth.assertThat(awaitItem()).isEqualTo(testRestaurant)
                awaitComplete()
            }
        }

    }

}
