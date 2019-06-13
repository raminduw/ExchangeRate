package com.ramindu.weeraman.currencyrate.di

import com.ramindu.weeraman.currencyrate.base.BaseActivity
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class, RepositoryModule::class,RxJavaModule::class,ViewModelModule::class])
interface AppComponent {
    fun inject(baseActivity: BaseActivity)
}