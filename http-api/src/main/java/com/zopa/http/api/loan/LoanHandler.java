package com.zopa.http.api.loan;

import com.zopa.loan.calculator.*;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.JsonRender;

import java.util.Optional;
import java.util.function.Consumer;

import static ratpack.jackson.Jackson.json;

public class LoanHandler implements Handler {

    private final ZopaRateSystem zopaRateSystem;
    private final RateCalculatorStrategy rateCalculatorStrategy;

    public LoanHandler(ZopaRateSystem zopaRateSystem, RateCalculatorStrategy rateCalculatorStrategy) {
        this.zopaRateSystem = zopaRateSystem;
        this.rateCalculatorStrategy = rateCalculatorStrategy;
    }

    @Override
    public void handle(Context ctx) {
        Optional<Quotation> quote = zopaRateSystem.quote(rateCalculatorStrategy, 36, 1000);
        quote.ifPresentOrElse(renderQuotation(ctx), renderError(ctx));
    }

    private Consumer<? super Quotation> renderQuotation(Context ctx) {
        return quotation -> {
            EnrichedFormatQuotation enrichedQuotation = enrichedFormatQuotation(quotation);
            JsonRender json = json(enrichedQuotation);
            ctx.render(json);
        };
    }

    private Runnable renderError(Context ctx) {
        return () -> ctx.render("It is not possible to calculate your loan right now.");
    }

    private EnrichedFormatQuotation enrichedFormatQuotation(Quotation quotation) {
        return new UserFriendlyQuotationFormatter(Currency.POUND).format(quotation);
    }

}
