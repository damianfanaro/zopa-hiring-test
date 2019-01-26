package com.zopa.loan.calculator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class UserFriendlyQuotationFormatterTest {

    @Test
    fun given_quotationData_when_formattingQuotationData_then_dataInProperFormatIsGiven() {
        val quotation = Quotation(1000, 0.0712345, 30.87709686537183, 1111.575487153386)

        val quotationFormatter = UserFriendlyQuotationFormatter(Currency.EURO).format(quotation)

        assertEquals("€1000", quotationFormatter.requestedAmount)
        assertEquals("7.1%", quotationFormatter.rate)
        assertEquals("€30.88", quotationFormatter.monthlyRepayment)
        assertEquals("€1111.58", quotationFormatter.totalRepayment)
    }

}