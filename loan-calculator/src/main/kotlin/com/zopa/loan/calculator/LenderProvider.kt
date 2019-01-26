package com.zopa.loan.calculator

interface LenderDataProvider {

    fun getLenderData(): List<LenderData>

}

data class LenderData(val name: String, val rate: Double, val available: Long)