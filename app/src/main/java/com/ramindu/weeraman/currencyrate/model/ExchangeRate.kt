package com.ramindu.weeraman.currencyrate.model

data class ExchangeRate(val base: String?, val rates: Rates?, val date: String?)

data class Rates(val BGN: Number?, val NZD: Number?, val ILS: Number?, val RUB: Number?, val CAD: Number?,
                 val USD: Number?, val PHP: Number?, val CHF: Number?, val ZAR: Number?, val AUD: Number?, val JPY: Number?,
                 val TRY: Number?, val HKD: Number?, val MYR: Number?, val THB: Number?, val HRK: Number?, val NOK: Number?,
                 val IDR: Number?, val DKK: Number?, val CZK: Number?, val HUF: Number?, val GBP: Number?, val MXN: Number?,
                 val KRW: Number?, val ISK: Number?, val SGD: Number?, val BRL: Number?,
                 val PLN: Number?, val INR: Number?, val RON: Number?, val CNY: Number?, val SEK: Number?)