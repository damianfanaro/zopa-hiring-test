package com.zopa.loan.calculator

interface CompoundInterestCalculator {

    fun quote(rate: Double, timePeriod: Int, requestedLoan: Long): Quotation

}

interface QuotationFormatter<T> {

    fun format(quotation: Quotation): T

}

data class Quotation(
        val requestedLoan: Long,
        val rate: Double,
        val monthlyRepayment: Double,
        val totalRepayment: Double
)
