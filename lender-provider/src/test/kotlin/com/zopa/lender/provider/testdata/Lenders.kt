package com.zopa.lender.provider.testdata

import com.zopa.loan.calculator.LenderData

fun buildExpectedLendersData() = listOf(
        buildLender("Bob", 0.075, 640),
        buildLender("Jane", 0.069, 480),
        buildLender("Fred", 0.071, 520),
        buildLender("Mary", 0.104, 170),
        buildLender("John", 0.081, 320),
        buildLender("Dave", 0.074, 140),
        buildLender("Angela", 0.071, 60)
)

private fun buildLender(name: String, rate: Double, available: Long) = LenderData(name, rate, available)
