package com.zopa.loan.calculator

import com.zopa.loan.calculator.testdata.InMemoryLenderDataProvider
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.function.Predicate

internal class ZopaRateSystemTest {

    companion object {
        private val ALWAYS_FALSE = Predicate<Long> { false }
        private val ALWAYS_TRUE = Predicate<Long> { true }
    }

    private val timePeriod = 36
    private val lenderDataProvider = InMemoryLenderDataProvider()
    private val compoundInterestCalculator = MonthlyCompoundInterestCalculator()
    private val rateCalculatorStrategy = LowestRateCalculatorStrategy()

    @Test
    fun given_invalidRequestedLoan_when_gettingQuotation_then_quotationIsNotAvailable() {
        val requestedLoan = 1000L
        val rateSystem = ZopaRateSystem(lenderDataProvider, compoundInterestCalculator, ALWAYS_FALSE)

        val quotation = rateSystem.quote(rateCalculatorStrategy, timePeriod, requestedLoan)

        assertFalse(quotation.isPresent)
    }

    @Test
    fun given_validRequestedLoan_when_gettingQuotation_then_quotationIsAvailable() {
        val requestedLoan = 1000L
        val rateSystem = ZopaRateSystem(lenderDataProvider, compoundInterestCalculator, ALWAYS_TRUE)

        val quotation = rateSystem.quote(rateCalculatorStrategy, timePeriod, requestedLoan)

        assertTrue(quotation.isPresent)
    }

    @Test
    fun given_requestedLoanThatExceedTotalAvailable_when_gettingQuotation_then_quotationIsNotAvailable() {
        val requestedLoan = 10000L
        val rateSystem = ZopaRateSystem(lenderDataProvider, compoundInterestCalculator, ALWAYS_TRUE)

        val quotation = rateSystem.quote(rateCalculatorStrategy, timePeriod, requestedLoan)

        assertFalse(quotation.isPresent)
    }

}