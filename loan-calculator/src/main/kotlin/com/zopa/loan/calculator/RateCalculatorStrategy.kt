package com.zopa.loan.calculator

import java.util.*

interface RateCalculatorStrategy {

    fun calculateRate(lendersData: List<LenderData>, requestedLoan: Long): OptionalDouble

}

data class LenderData(
        val name: String,
        val rate: Double,
        val available: Long
)