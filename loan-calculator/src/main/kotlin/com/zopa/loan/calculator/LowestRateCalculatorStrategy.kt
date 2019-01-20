package com.zopa.loan.calculator

import java.util.*
import java.util.stream.Collectors.toList

class LowestRateCalculatorStrategy : RateCalculatorStrategy {

    override fun calculateRate(lendersData: List<LenderData>, requestedLoan: Long): OptionalDouble {
        val lendersByAscendingRate = getLendersOrderedByAscendingRate(lendersData)
        val possibleLenders = calculatePossibleLenders(lendersByAscendingRate, requestedLoan)
        return meanRateIfAvailable(possibleLenders)
    }

    private fun getLendersOrderedByAscendingRate(lendersData: List<LenderData>): List<LenderData> {
        return lendersData.stream()
                .sorted { l1, l2 -> l1.rate.compareTo(l2.rate) }
                .collect(toList())
    }

    private fun calculatePossibleLenders(lendersData: List<LenderData>, requestedLoan: Long): List<LenderData> {
        var accumulatedAvailable: Long = 0
        val possibleLenders = ArrayList<LenderData>()

        for (lenderData in lendersData) {
            if (accumulatedAvailable < requestedLoan) {
                accumulatedAvailable += lenderData.available
                possibleLenders.add(lenderData)
            } else {
                break
            }
        }

        return if (accumulatedAvailable >= requestedLoan) possibleLenders else emptyList()
    }

    private fun meanRateIfAvailable(possibleRates: List<LenderData>): OptionalDouble {
        return possibleRates.stream()
                .mapToDouble { it.rate }
                .average()
    }

}