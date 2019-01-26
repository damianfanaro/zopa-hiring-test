package com.zopa.lender.provider

import com.zopa.loan.calculator.LenderData
import com.zopa.loan.calculator.LenderDataProvider
import java.util.Arrays.asList

class MockLenderDataProvider : LenderDataProvider {

    override fun getLenderData(): List<LenderData> {
        return asList(
                LenderData("Gustavo", 0.01, 1000),
                LenderData("Patricia", 0.07, 110),
                LenderData("Damian", 0.11, 14300),
                LenderData("Ramon", 0.33, 1002),
                LenderData("Assan", 1.01, 300)
        )
    }

}