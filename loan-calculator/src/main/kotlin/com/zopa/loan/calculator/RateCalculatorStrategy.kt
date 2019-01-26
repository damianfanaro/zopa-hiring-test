package com.zopa.loan.calculator

import java.util.*

interface RateCalculatorStrategy {

    fun calculateRate(lendersData: List<LenderData>, requestedLoan: Long): OptionalDouble

}
