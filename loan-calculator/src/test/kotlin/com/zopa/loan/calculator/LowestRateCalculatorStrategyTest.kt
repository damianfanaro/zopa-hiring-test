package com.zopa.loan.calculator

import com.zopa.loan.calculator.testdata.buildLendersData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class LowestRateCalculatorStrategyTest {

    private val lowestRateCalculator = LowestRateCalculatorStrategy()

    @ParameterizedTest
    @CsvSource("0.07, 1000", "0.07785714285714285, 2330", "0.069, 480", "0.07033333333333333, 1040")
    fun given_lendersDataAndLoanAmount_when_requestingInterestRate_then_lowestRateIsCalculated(expectedRate: Double, requestedLoan: Long) {
        val lendersData = buildLendersData()

        val actualRate = lowestRateCalculator.calculateRate(lendersData, requestedLoan)

        assertTrue(actualRate.isPresent)
        assertEquals(expectedRate, actualRate.asDouble)
    }

    @Test
    fun given_loanAmountBiggerThanTotalAvailable_when_requestingInterestRate_then_rateNotAvailable() {
        val lendersData = buildLendersData()
        val requestedLoan: Long = 10000

        val actualRate = lowestRateCalculator.calculateRate(lendersData, requestedLoan)

        assertFalse(actualRate.isPresent)
    }

}