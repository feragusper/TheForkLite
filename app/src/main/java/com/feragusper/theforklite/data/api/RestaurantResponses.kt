package com.feragusper.theforklite.data.api

import com.feragusper.theforklite.domain.Restaurant
import com.google.gson.annotations.SerializedName

data class RestaurantDetailEntity(
    val data: RestaurantEntity,
) {
    fun toRestaurant(): Restaurant {
        return data.toRestaurant()
    }
}

data class RestaurantEntity(
    @field: SerializedName("pics_diaporama") private val picsDiaporama: List<PicEntity>,
    private val name: String,
    @field: SerializedName("min_price") private val minPrice: Float,
    @field: SerializedName("currency_code") private val currencyCode: String,
    @field: SerializedName("avg_rate") private val avgRate: Float,
    @field: SerializedName("trip_advisor_review_count") private val tripAdvisorReviewCount: Int,
    @field: SerializedName("trip_advisor_avg_rating") private val tripAdvisorAvgRating: Float,
    @field: SerializedName("rate_distinction") private val rateDistinction: String,
    private val speciality: String,
    @field: SerializedName("card_start_1") private val cardStart1: String? = null,
    @field: SerializedName("card_start_2") private val cardStart2: String? = null,
    @field: SerializedName("card_start_3") private val cardStart3: String? = null,
    @field: SerializedName("card_main_1") private val cardMain1: String? = null,
    @field: SerializedName("card_main_2") private val cardMain2: String? = null,
    @field: SerializedName("card_main_3") private val cardMain3: String? = null,
    @field: SerializedName("card_dessert_1") private val cardDessert1: String? = null,
    @field: SerializedName("card_dessert_2") private val cardDessert2: String? = null,
    @field: SerializedName("card_dessert_3") private val cardDessert3: String? = null,
    @field: SerializedName("price_card_start_1") private val priceCardStart1: Float? = null,
    @field: SerializedName("price_card_start_2") private val priceCardStart2: Float? = null,
    @field: SerializedName("price_card_start_3") private val priceCardStart3: Float? = null,
    @field: SerializedName("price_card_main_1") private val priceCardMain1: Float? = null,
    @field: SerializedName("price_card_main_2") private val priceCardMain2: Float? = null,
    @field: SerializedName("price_card_main_3") private val priceCardMain3: Float? = null,
    @field: SerializedName("price_card_dessert_1") private val priceCardDessert1: Float? = null,
    @field: SerializedName("price_card_dessert_2") private val priceCardDessert2: Float? = null,
    @field: SerializedName("price_card_dessert_3") private val priceCardDessert3: Float? = null,
) {

    fun toRestaurant() = Restaurant(
        picsDiaporamaImageUrls = picsDiaporama.map { picEntity -> picEntity.url },
        name = name,
        minPrice = minPrice,
        currencyCode = currencyCode,
        avgRate = avgRate.toString(),
        tripAdvisorReviewCount = tripAdvisorReviewCount,
        tripAdvisorAvgRating = tripAdvisorAvgRating,
        rateDistinction = rateDistinction,
        speciality = speciality,
        cardStart1 = cardStart1,
        cardStart2 = cardStart2,
        cardStart3 = cardStart3,
        cardMain1 = cardMain1,
        cardMain2 = cardMain2,
        cardMain3 = cardMain3,
        cardDessert1 = cardDessert1,
        cardDessert2 = cardDessert2,
        cardDessert3 = cardDessert3,
        priceCardStart1 = priceCardStart1,
        priceCardStart2 = priceCardStart2,
        priceCardStart3 = priceCardStart3,
        priceCardMain1 = priceCardMain1,
        priceCardMain2 = priceCardMain2,
        priceCardMain3 = priceCardMain3,
        priceCardDessert1 = priceCardDessert1,
        priceCardDessert2 = priceCardDessert2,
        priceCardDessert3 = priceCardDessert3,
    )

    data class PicEntity(@field: SerializedName("1350x759") val url: String)
}