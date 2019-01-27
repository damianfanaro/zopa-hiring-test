package com.zopa.loan.calculator.validator

import java.util.function.Predicate

class LoanMultiplicityValidator(private val loanMultiplicity: Long) : Predicate<Long> {

    override fun test(requestedLoan: Long): Boolean {
        return requestedLoan isMultipleOf loanMultiplicity
    }

    private infix fun Long.isMultipleOf(loanMultiplicity: Long) = this % loanMultiplicity == 0L

}