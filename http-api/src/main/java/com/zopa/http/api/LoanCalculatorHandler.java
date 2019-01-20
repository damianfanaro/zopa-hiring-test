package com.zopa.http.api;

import com.zopa.loan.calculator.*;
import ratpack.handling.Context;
import ratpack.handling.Handler;

public class LoanCalculatorHandler implements Handler {

    @Override
    public void handle(Context ctx) {
        CompoundInterestCalculator compoundInterestCalculator = new MonthlyCompoundInterestCalculator();
        Quotation quote = compoundInterestCalculator.quote(0.01, 10, 10000);
        QuotationFormatter<ProxiedQuotation> quotationFormatter = new InterfaceFriendlyQuotationFormatter(Currency.EURO);
        ctx.render(quotationFormatter.format(quote));
    }

}
