package com.ramindu.weeraman.currencyrate.base

import android.app.Application
import com.ramindu.weeraman.currencyrate.BASE_URL
import com.ramindu.weeraman.currencyrate.di.*


open class BaseApplication : Application() {

    // FOR DATA
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        this.appComponent = this.initDagger()
    }

    // --- Dependencies injection ---

   protected open fun initDagger(): AppComponent
            = DaggerAppComponent.builder()
            .netModule(NetModule(BASE_URL))
            .repositoryModule(RepositoryModule())
            .rxJavaModule(RxJavaModule())
            .build()

}