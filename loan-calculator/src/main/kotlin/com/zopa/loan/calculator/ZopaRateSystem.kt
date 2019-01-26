package com.zopa.loan.calculator

import java.util.*
import java.util.function.Predicate

internal class ZopaRateSystem(private val lenderDataProvider: LenderDataProvider,
                              private val compoundInterestCalculator: CompoundInterestCalculator,
                              private val loanValidator: Predicate<Long>) {

    fun quote(rateCalculatorStrategy: RateCalculatorStrategy, timePeriod: Int, requestedLoan: Long): Optional<Quotation> {
        if (loanValidator.test(requestedLoan)) {
            val lendersData = lenderDataProvider.getLenderData()
            val calculatedRate = rateCalculatorStrategy.calculateRate(lendersData, requestedLoan)
            if (calculatedRate.isPresent) {
                val quotation = compoundInterestCalculator.quote(calculatedRate.asDouble, timePeriod, requestedLoan)
                return Optional.of(quotation)
            }
        }
        return Optional.empty()
    }

}