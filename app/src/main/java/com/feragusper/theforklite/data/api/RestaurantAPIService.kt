package com.feragusper.theforklite.data.api

import com.feragusper.theforklite.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Used to connect to the TMDB API to query catalog
 */
interface RestaurantAPIService {

    @GET("api")
    suspend fun getRestaurantDetail(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("method") method: String = "restaurant_get_info",
        @Query("id_restaurant") restaurantId: String,
    ): RestaurantDetailEntity

    companion object {
        fun create(): RestaurantAPIService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RestaurantAPIService::class.java)
        }
    }
}
