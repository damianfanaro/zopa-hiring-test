package com.zopa.loan.calculator.validator

import java.util.function.Predicate

class LoanMultiplicityValidator(private val loanMultiplicity: Long) : Predicate<Long> {

    override fun test(requestedLoan: Long): Boolean {
        return isRequestedLoanValidMultiple(requestedLoan)
    }

    private fun isRequestedLoanValidMultiple(requestLoan: Long): Boolean {
        return requestLoan % loanMultiplicity == 0L
    }

}