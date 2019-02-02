package com.zopa.lender.provider

import com.zopa.loan.calculator.LenderData
import com.zopa.loan.calculator.LenderDataProvider

class InMemoryLenderDataProvider : LenderDataProvider {

    private val lenders = mutableListOf(
            LenderData("Bob", 0.075, 640),
            LenderData("Jane", 0.069, 480),
            LenderData("Fred", 0.071, 520),
            LenderData("Mary", 0.104, 170),
            LenderData("John", 0.081, 320),
            LenderData("Dave", 0.074, 140),
            LenderData("Angela", 0.071, 60)
    )

    override fun getLenders() = lenders

    override fun addLender(lenderData: LenderData) {
        lenders.add(lenderData)
    }

}