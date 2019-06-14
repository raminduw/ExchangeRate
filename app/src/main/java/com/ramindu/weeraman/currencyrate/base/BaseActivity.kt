package com.ramindu.weeraman.currencyrate.base

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    // FOR DATA
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    // --- LIFECYCLE METHODS ---
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutById())
        ButterKnife.bind(this)
        this.configureDagger()
        this.configureDesign()
    }

    // --- DEPENDENCIES INJECTION ---
    private fun configureDagger() = (this.application as BaseApplication).appComponent.inject(this)

    // --- ABSTRACT METHODS ---
    abstract fun getLayoutById(): Int
    abstract fun configureDesign()
}