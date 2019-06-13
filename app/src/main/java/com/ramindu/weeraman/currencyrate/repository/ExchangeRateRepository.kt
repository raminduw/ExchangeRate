package com.ramindu.weeraman.currencyrate.repository

import com.ramindu.weeraman.currencyrate.api.ExchangeRatesApiService


class ExchangeRateRepository(private val exchangeRatesApiService: ExchangeRatesApiService) {

    fun getExchangeRate() = this.exchangeRatesApiService.getExchangeRate()

}