package com.zopa.lender.provider

import com.zopa.lender.provider.testdata.buildExpectedLendersData
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class CsvLenderDataProviderTest {

    @ParameterizedTest
    @ValueSource(strings = ["sample/market.csv"])
    fun given_csvFilePath_when_gettingLenderData_then_lenderDataFromFileIsReturned(fileName: String) {
        val expectedLendersData = buildExpectedLendersData()
        val lenderDataProvider = CsvLenderDataProvider(fileName)

        val lendersData = lenderDataProvider.getLenders()

        assertTrue(lendersData.containsAll(expectedLendersData))
    }

    @ParameterizedTest
    @ValueSource(strings = ["sample/market-with-invalid-lines.csv"])
    fun given_csvFilePathWithInvalidLines_when_gettingLenderData_then_onlyValidCsvFileLinesAreReturned(fileName: String) {
        val expectedLendersData = buildExpectedLendersData()
        val lenderDataProvider = CsvLenderDataProvider(fileName)

        val lendersData = lenderDataProvider.getLenders()

        assertTrue(lendersData.containsAll(expectedLendersData))
    }

    @Test
    fun given_nonExistingFileName_when_gettingLenderData_then_anEmptyListIsReturned() {
        val lenderDataProvider = CsvLenderDataProvider("non-existing-file-name")

        val lendersData = lenderDataProvider.getLenders()

        assertTrue(lendersData.isEmpty())
    }

}