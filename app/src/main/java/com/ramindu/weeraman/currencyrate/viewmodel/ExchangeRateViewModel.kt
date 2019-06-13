package com.ramindu.weeraman.currencyrate.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.ramindu.weeraman.currencyrate.EXCHANGE_RATE_REFRESH_DURATION
import com.ramindu.weeraman.currencyrate.di.OBSERVER_ON
import com.ramindu.weeraman.currencyrate.di.SUBCRIBER_ON
import com.ramindu.weeraman.currencyrate.model.ExchangeRate

import com.ramindu.weeraman.currencyrate.repository.ExchangeRateRepository
import io.reactivex.Observable


import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class ExchangeRateViewModel @Inject constructor(private val exchangeRateRepository: ExchangeRateRepository,
                                                @param:Named(SUBCRIBER_ON) private val subscriberOn: Scheduler,
                                                @param:Named(OBSERVER_ON) private val observerOn: Scheduler): BaseViewModel() {

    // FOR DATA
    val exchangeRate: MutableLiveData<ExchangeRate?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean?> = MutableLiveData()
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    init { this.isLoading.value = false }

    fun startExchangeRateRequest() {
        this.disposable.addAll( Observable.interval(0, EXCHANGE_RATE_REFRESH_DURATION,
            TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {getCurrencyExchangeRates()
            })
    }

    private fun getCurrencyExchangeRates(){
        this.disposable.addAll(this.exchangeRateRepository.getExchangeRate()
            .subscribeOn(subscriberOn)
            .observeOn(observerOn)
            .doOnSubscribe { this.isLoading.value = true }
            .doOnComplete { this.isLoading.value = false }
            .doOnError { this.isLoading.value = false }
            .subscribe (
                { this.exchangeRate.value = it },
                { this.errorMessage.value = it.message }))


    }

}