package com.feragusper.theforklite.di

import com.feragusper.theforklite.data.api.RestaurantAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRestaurantAPIService(): RestaurantAPIService {
        return RestaurantAPIService.create()
    }
}
