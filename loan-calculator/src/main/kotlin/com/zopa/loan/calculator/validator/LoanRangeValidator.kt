package com.zopa.loan.calculator.validator

import java.util.function.Predicate

class LoanRangeValidator(private val minimumLoanAllowed: Long, private val maximumLoanAllowed: Long) : Predicate<Long> {

    init {
        checkInvariants()
    }

    override fun test(requestedLoan: Long): Boolean {
        return isRequestedLoanWithinBounds(requestedLoan)
    }

    private fun isRequestedLoanWithinBounds(requestedLoan: Long): Boolean {
        return requestedLoan in minimumLoanAllowed..maximumLoanAllowed
    }

    private fun checkInvariants() {
        if (minimumLoanAllowed < 0) throw IllegalArgumentException("Minimum loan value must be positive")
        if (minimumLoanAllowed > maximumLoanAllowed) throw IllegalArgumentException("The minimum and maximum values must not overlap")
    }

}