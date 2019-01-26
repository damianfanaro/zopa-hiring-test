package com.zopa.loan.calculator.validator

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LoanMultiplicityValidatorTest {

    companion object {
        private const val LOAN_MULTIPLICITY = 100
    }

    @ParameterizedTest
    @ValueSource(longs = [100, 500, 1000])
    fun given_loanMultiplicityValidator_when_requestedLoanMultiplicityIsValid_then_loanIsAccepted(requestedLoan: Long) {
        val loanMultiplicityValidator = LoanMultiplicityValidator(LOAN_MULTIPLICITY.toLong())

        val loanValidation = loanMultiplicityValidator.test(requestedLoan)

        assertTrue(loanValidation)
    }

    @ParameterizedTest
    @ValueSource(longs = [99, 150, 203])
    fun given_loanMultiplicityValidator_when_requestedLoanMultiplicityIsInvalid_then_loanIsRejected(requestedLoan: Long) {
        val loanMultiplicityValidator = LoanMultiplicityValidator(LOAN_MULTIPLICITY.toLong())

        val loanValidation = loanMultiplicityValidator.test(requestedLoan)

        assertFalse(loanValidation)
    }

}
