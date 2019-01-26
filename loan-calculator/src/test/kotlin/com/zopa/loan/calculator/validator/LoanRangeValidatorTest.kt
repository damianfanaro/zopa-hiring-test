package com.zopa.loan.calculator.validator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LoanRangeValidatorTest {

    companion object {
        private const val MINIMUM_LOAN_ALLOWED = 200L
        private const val MAXIMUM_LOAN_ALLOWED = 400L
    }

    @ParameterizedTest
    @ValueSource(longs = [100, 500])
    fun given_loanRangeValidator_when_requestedLoanIsOutOfBounds_then_loanIsRejected(requestedLoan: Long) {
        val loanAmountValidator = LoanRangeValidator(MINIMUM_LOAN_ALLOWED, MAXIMUM_LOAN_ALLOWED)

        val loanValidation = loanAmountValidator.test(requestedLoan)

        assertFalse(loanValidation)
    }

    @Test
    fun given_loanRangeValidator_when_requestedLoanIsWithingBounds_then_loanIsAccepted() {
        val loanAmountValidator = LoanRangeValidator(MINIMUM_LOAN_ALLOWED, MAXIMUM_LOAN_ALLOWED)

        val requestedLoan = 300L
        val loanValidation = loanAmountValidator.test(requestedLoan)

        assertTrue(loanValidation)
    }

    @Test
    fun given_overlappedLoanRangeValues_when_creatingValidatorInstance_then_exceptionIsThrown() {
        val illegalArgumentException = assertThrows<IllegalArgumentException> {
            LoanRangeValidator(MAXIMUM_LOAN_ALLOWED, MINIMUM_LOAN_ALLOWED)
        }

        assertEquals("The minimum and maximum values must not overlap", illegalArgumentException.message)
    }

    @Test
    fun given_negativeMinimumLoanRangeValue_when_creatingValidatorInstance_then_exceptionIsThrown() {
        val illegalArgumentException = assertThrows<IllegalArgumentException> {
            LoanRangeValidator(minimumLoanAllowed = -5, maximumLoanAllowed = MAXIMUM_LOAN_ALLOWED)
        }

        assertEquals("Minimum loan value must be positive", illegalArgumentException.message)
    }

}
