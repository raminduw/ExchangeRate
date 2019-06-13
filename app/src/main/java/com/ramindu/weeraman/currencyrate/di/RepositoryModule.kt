package com.ramindu.weeraman.currencyrate.di

import com.ramindu.weeraman.currencyrate.api.ExchangeRatesApiService
import com.ramindu.weeraman.currencyrate.repository.ExchangeRateRepository
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(exchangeRatesApiService: ExchangeRatesApiService) = ExchangeRateRepository(exchangeRatesApiService)

}