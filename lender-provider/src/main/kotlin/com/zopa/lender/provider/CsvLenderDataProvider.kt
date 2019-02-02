package com.zopa.lender.provider

import com.zopa.loan.calculator.LenderData
import com.zopa.loan.calculator.LenderDataProvider
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.Collections.unmodifiableList

class CsvLenderDataProvider(private val fileName: String) : LenderDataProvider {

    private var lendersData: MutableList<LenderData>? = null

    override fun getLenders(): List<LenderData> {
        if (lendersDataNotReadYet()) {
            readLendersData()
        }
        return unmodifiableList(lendersData!!)
    }

    override fun addLender(lenderData: LenderData) {
        lendersData?.add(lenderData)
    }

    private fun lendersDataNotReadYet(): Boolean {
        return lendersData == null
    }

    private fun readLendersData() {
        lendersData = ArrayList()
        val path = Paths.get(this.javaClass.classLoader.getResource(fileName).toURI())

        try {
            Files.lines(path).forEach { mapToLenderData(it) }
        } catch (e: IOException) {
            println("Exception when trying to read CSV file")
        }

    }

    private fun mapToLenderData(lenderDataFileLine: String) {
        try {
            val lenderDataArray = lenderDataFileLine.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val lenderData = buildLenderData(lenderDataArray[0], lenderDataArray[1], lenderDataArray[2])
            lendersData!!.add(lenderData)
        } catch (e: Exception) {
            println("Skipping invalid CSV file line: $lenderDataFileLine")
        }

    }

    private fun buildLenderData(name: String, rate: String, available: String): LenderData {
        return LenderData(notEmptyOrException(name), rate.toDouble(), available.toLong())
    }

    private fun notEmptyOrException(name: String): String {
        if (name.isEmpty()) {
            throw RuntimeException("Lender name is empty")
        }
        return name
    }

}