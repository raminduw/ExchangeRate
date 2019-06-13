package com.ramindu.weeraman.currencyrate.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ramindu.weeraman.currencyrate.viewmodel.ExchangeRateViewModel
import com.ramindu.weeraman.currencyrate.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ExchangeRateViewModel::class)
    internal abstract fun postMainViewModel(viewModel: ExchangeRateViewModel): ViewModel
}