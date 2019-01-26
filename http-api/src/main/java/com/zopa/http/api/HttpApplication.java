package com.zopa.http.api;

import com.zopa.lender.provider.CsvLenderDataProvider;
import com.zopa.loan.calculator.*;
import com.zopa.loan.calculator.validator.LoanMultiplicityValidator;
import com.zopa.loan.calculator.validator.LoanRangeValidator;
import ratpack.server.RatpackServer;

import java.util.Optional;
import java.util.function.Predicate;

import static com.zopa.http.api.ZopaConstants.*;

public class HttpApplication {

    public static void main(String[] args) {
        ProgramArguments programArguments = validateAndResolveArguments(args);

        LenderDataProvider lenderDataProvider = new CsvLenderDataProvider(programArguments.getMarketFilePath());
        CompoundInterestCalculator compoundInterestCalculator = new MonthlyCompoundInterestCalculator();
        Predicate<Long> requestedLoanValidator = buildRequestedLoanValidator();

        ZopaRateSystem zopaRateSystem = new ZopaRateSystem(lenderDataProvider, compoundInterestCalculator, requestedLoanValidator);

        Optional<Quotation> quotation = zopaRateSystem.quote(new LowestRateCalculatorStrategy(), TIME_PERIOD, programArguments.getRequestedLoan());

        if (quotation.isPresent()) {
            printQuotationData(quotation.get());
        } else {
            System.out.println("It is not possible to provide a quote at this time.");
        }
    }

    private static ProgramArguments validateAndResolveArguments(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid program arguments. Expected: \"<lender-data-file.csv> <loan-amount>\".");
        }

        String marketFileName = args[0];
        long requestedLoan = Long.parseLong(args[1]);

        return new ProgramArguments(marketFileName, requestedLoan);
    }

    private static Predicate<Long> buildRequestedLoanValidator() {
        return loanRangeValidator().and(loanMultiplicityValidator());
    }

    private static Predicate<Long> loanRangeValidator() {
        return new LoanRangeValidator(MINIMUM_LOAN_ALLOWED, MAXIMUM_LOAN_ALLOWED);
    }

    private static Predicate<Long> loanMultiplicityValidator() {
        return new LoanMultiplicityValidator(LOAN_MULTIPLICITY);
    }

    private static void printQuotationData(Quotation quotation) {
        EnrichedFormatQuotation enrichedQuotation = new UserFriendlyQuotationFormatter(Currency.POUND).format(quotation);
        System.out.println("Requested amount: " + enrichedQuotation.getRequestedAmount());
        System.out.println("Rate: " + enrichedQuotation.getRate());
        System.out.println("Monthly repayment: " + enrichedQuotation.getMonthlyRepayment());
        System.out.println("Total repayment: " + enrichedQuotation.getTotalRepayment());
    }

    private static void startServer() throws Exception {
        RatpackServer.start(server -> server.handlers(chain -> chain

                .get(ctx -> ctx.render("Welcome to Zopa Loan Calculator!"))
                .get("calculateLoan", new LoanCalculatorHandler())

        ));
    }

}
