package com.feragusper.theforklite.domain

import java.text.NumberFormat
import java.util.*

data class Restaurant(
    val picsDiaporamaImageUrls: List<String>,
    val name: String,
    val minPrice: Float,
    val currencyCode: String,
    val avgRate: String,
    val tripAdvisorAvgRating: Float,
    val tripAdvisorReviewCount: Int,
    val rateDistinction: String,
    val speciality: String,
    val cardStart1: String? = null,
    val cardStart2: String? = null,
    val cardStart3: String? = null,
    val cardMain1: String? = null,
    val cardMain2: String? = null,
    val cardMain3: String? = null,
    val cardDessert1: String? = null,
    val cardDessert2: String? = null,
    val cardDessert3: String? = null,
    val priceCardStart1: Float? = null,
    val priceCardStart2: Float? = null,
    val priceCardStart3: Float? = null,
    val priceCardMain1: Float? = null,
    val priceCardMain2: Float? = null,
    val priceCardMain3: Float? = null,
    val priceCardDessert1: Float? = null,
    val priceCardDessert2: Float? = null,
    val priceCardDessert3: Float? = null,
) {
    val price: String = minPrice.withCurrency(currencyCode)

    val menus: List<MenuItemNameAndPrice> by lazy {
        val menus = mutableListOf<MenuItemNameAndPrice>()

        fun safelyAdd(menuName: String?, price: Float?) {
            if (menuName.isNullOrBlank().not().and(price != null)) {
                menus.add(MenuItemNameAndPrice(menuName!!, price!!.withCurrency(currencyCode)))
            }
        }

        safelyAdd(cardMain1, priceCardMain1)
        safelyAdd(cardMain2, priceCardMain2)
        safelyAdd(cardMain3, priceCardMain3)

        safelyAdd(cardStart1, priceCardStart1)
        safelyAdd(cardStart2, priceCardStart2)
        safelyAdd(cardStart3, priceCardStart3)

        safelyAdd(cardDessert1, priceCardDessert1)
        safelyAdd(cardDessert2, priceCardDessert2)
        safelyAdd(cardDessert3, priceCardDessert3)

        menus
    }

    data class MenuItemNameAndPrice(val name: String, val price: String)
}

private fun Float.withCurrency(currencyCode: String): String {
    val formatter = NumberFormat.getCurrencyInstance()
    formatter.currency = Currency.getInstance(currencyCode)
    return formatter.format(this)
}
