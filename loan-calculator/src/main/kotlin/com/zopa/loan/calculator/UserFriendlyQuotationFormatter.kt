package com.zopa.loan.calculator

import java.text.DecimalFormat

class UserFriendlyQuotationFormatter(private val currency: Currency) : QuotationFormatter<EnrichedFormatQuotation> {

    override fun format(quotation: Quotation): EnrichedFormatQuotation {
        return EnrichedFormatQuotation(currency, quotation)
    }

}

enum class Currency(val symbol: String) {

    POUND("£"),
    EURO("€"),
    DOLLAR("$")

}

class EnrichedFormatQuotation(private val currency: Currency, private val quotation: Quotation) {

    val requestedAmount: String
        get() = currency.symbol + quotation.requestedLoan

    val rate: String
        get() = DecimalFormat("#.0%").format(quotation.rate)

    val monthlyRepayment: String
        get() {
            val monthlyRepayment = DecimalFormat("#.00").format(quotation.monthlyRepayment)
            return currency.symbol + monthlyRepayment
        }

    val totalRepayment: String
        get() {
            val totalRepayment = DecimalFormat("#.00").format(quotation.totalRepayment)
            return currency.symbol + totalRepayment
        }

}
