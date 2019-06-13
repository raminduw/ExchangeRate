package com.ramindu.weeraman.currencyrate.api


import com.ramindu.weeraman.currencyrate.model.ExchangeRate
import io.reactivex.Observable
import retrofit2.http.GET


interface ExchangeRatesApiService {
    @GET("/latest")
    fun getExchangeRate(): Observable<ExchangeRate>
}