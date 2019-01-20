package com.zopa.loan.calculator

class MonthlyCompoundInterestCalculator : CompoundInterestCalculator {

    override fun quote(rate: Double, timePeriod: Int, requestedLoan: Long): Quotation {
        val monthlyRate = rate / 12
        val rateOverTimePeriod = Math.pow(monthlyRate + 1, timePeriod.toDouble()) - 1
        val simpleCompoundInterest = monthlyRate + monthlyRate / rateOverTimePeriod
        val monthlyRepayment = simpleCompoundInterest * requestedLoan
        val totalRepayment = monthlyRepayment * timePeriod

        return Quotation(requestedLoan, rate, monthlyRepayment, totalRepayment)
    }

}