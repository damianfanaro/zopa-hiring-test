package com.zopa.loan.calculator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MonthlyCompoundInterestCalculatorTest {

    private val compoundInterestCalculator = MonthlyCompoundInterestCalculator()

    @ParameterizedTest
    @CsvSource("0.07, 36, 1000, 30.87709686537183, 1111.575487153386")
    fun given_quotationParameters_when_calculatingCompoundInterest_then_expectedQuotationIsReturned(
            rate: Double,
            timePeriod: Int,
            requestedLoan: Long,
            expectedMonthlyRepayment: Double,
            expectedTotalRepayment: Double) {

        val quotation = compoundInterestCalculator.quote(rate, timePeriod, requestedLoan)

        assertEquals(expectedMonthlyRepayment, quotation.monthlyRepayment)
        assertEquals(expectedTotalRepayment, quotation.totalRepayment)
    }

}