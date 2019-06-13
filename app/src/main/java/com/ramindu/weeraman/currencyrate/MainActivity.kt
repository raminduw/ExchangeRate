package com.ramindu.weeraman.currencyrate

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import com.ramindu.weeraman.currencyrate.base.BaseActivity
import com.ramindu.weeraman.currencyrate.model.ExchangeRate
import com.ramindu.weeraman.currencyrate.viewmodel.ExchangeRateViewModel


class MainActivity : BaseActivity() {

    @BindView(R.id.loadingProgressBar) lateinit var progressBar: ProgressBar
    @BindView(R.id.txtBaseCurrency) lateinit var txtBaseCurrency: TextView
    @BindView(R.id.txtUSDRate) lateinit var txtUSDRate: TextView
    @BindView(R.id.txtPLNRate) lateinit var txtPLNRate: TextView

    private lateinit var viewModel: ExchangeRateViewModel

    override fun getLayoutById(): Int = R.layout.activity_main

    override fun configureDesign() {
        this.configureViewModel()
        this.observeData()
    }

    private fun configureViewModel(){
        this.viewModel = ViewModelProviders.of(this, viewModelFactory)[ExchangeRateViewModel::class.java];
    }

    private fun observeData(){
        this.viewModel.exchangeRate
            .observe(this, Observer { it?.let { this.updateUI(it) } })
        this.viewModel.isLoading
            .observe(this, Observer { it?.let { this.updateLoadingIndicator(it) } })
        this.viewModel.errorMessage
            .observe(this, Observer { it?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            } })
    }

    private fun updateUI(exchangeRate: ExchangeRate){
        Log.d("TAG", "USD Rate: ${exchangeRate.rates!!.USD}")
        Log.d("TAG", "PLN Rate: ${exchangeRate.rates!!.PLN}")
        Log.d("TAG", "Base Currency: ${exchangeRate.base}")

        this.txtBaseCurrency.text = "Base Currency: ${exchangeRate.base}"
        this.txtUSDRate.text = "USD Rate: ${exchangeRate.rates!!.USD}"
        this.txtPLNRate.text = "PLN Rate: ${exchangeRate.rates!!.PLN}"
    }


    private fun updateLoadingIndicator(isRefreshing: Boolean){
        if(isRefreshing)
        progressBar.visibility = View.VISIBLE
        else
        progressBar.visibility = View.GONE
    }

}
