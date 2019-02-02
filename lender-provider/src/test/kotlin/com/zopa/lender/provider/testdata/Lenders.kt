package com.zopa.lender.provider.testdata

import com.zopa.loan.calculator.LenderData

fun buildExpectedLendersData() = listOf(
        LenderData("Bob", 0.075, 640),
        LenderData("Jane", 0.069, 480),
        LenderData("Fred", 0.071, 520),
        LenderData("Mary", 0.104, 170),
        LenderData("John", 0.081, 320),
        LenderData("Dave", 0.074, 140),
        LenderData("Angela", 0.071, 60)
)
