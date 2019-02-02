package com.zopa.loan.calculator

interface LenderDataProvider {

    fun getLenders(): List<LenderData>

    fun addLender(lenderData: LenderData)

}

data class LenderData(val name: String, val rate: Double, val available: Long)