package com.feragusper.theforklite.domain

import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit tests for the implementation of [RestaurantDetailViewModel].
 */
@RunWith(MockitoJUnitRunner::class)
class RestaurantTest {

    @Test
    fun cardMenuIncomplete() {
        val cardMain1Name = "Steak"
        val cardMain2Name = "Steak II"
        val cardStart2Name = "Salad II"
        val testRestaurant = Restaurant(
            picsDiaporamaImageUrls = listOf("url1", "url2"),
            name = "Mirazur",
            minPrice = 50F,
            currencyCode = "EUR",
            avgRate = "9.3",
            tripAdvisorAvgRating = 9F,
            tripAdvisorReviewCount = 999,
            rateDistinction = "Incredible",
            speciality = "Mediterranean",
            cardMain1 = cardMain1Name,
            cardDessert1 = "Pudin",
            cardMain2 = cardMain2Name,
            cardDessert2 = "Pudin II",
            cardStart2 = cardStart2Name,
            priceCardMain1 = 20F,
            priceCardStart1 = 15F,
            priceCardDessert2 = 10F,
            priceCardMain2 = 20F,
            priceCardStart2 = 15F
        )
        Truth.assertThat(testRestaurant.menus[0].name).isEqualTo(cardMain1Name)
        Truth.assertThat(testRestaurant.menus[1].name).isEqualTo(cardMain2Name)
        Truth.assertThat(testRestaurant.menus[2].name).isEqualTo(cardStart2Name)
    }

    @Test
    fun cardMenuEmpty() {
        val testRestaurant = Restaurant(
            picsDiaporamaImageUrls = listOf("url1", "url2"),
            name = "Mirazur",
            minPrice = 50F,
            currencyCode = "EUR",
            avgRate = "9.3",
            tripAdvisorAvgRating = 9F,
            tripAdvisorReviewCount = 999,
            rateDistinction = "Incredible",
            speciality = "Mediterranean",
            cardDessert1 = "Pudin",
            cardDessert2 = "Pudin II",
            priceCardMain1 = 20F,
            priceCardStart1 = 15F,
            priceCardMain2 = 20F,
            priceCardStart2 = 15F
        )
        Truth.assertThat(testRestaurant.menus).isEmpty()
    }

    @Test
    fun formattedPrice() {
        val testRestaurant = Restaurant(
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
        Truth.assertThat(testRestaurant.price).isEqualTo("€50.00")
    }

}
