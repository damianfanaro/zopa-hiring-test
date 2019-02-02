package com.zopa.http.api.wiring;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.zopa.http.api.config.ZopaConfig;
import com.zopa.lender.provider.InMemoryLenderDataProvider;
import com.zopa.loan.calculator.CompoundInterestCalculator;
import com.zopa.loan.calculator.LenderDataProvider;
import com.zopa.loan.calculator.MonthlyCompoundInterestCalculator;
import com.zopa.loan.calculator.ZopaRateSystem;
import com.zopa.loan.calculator.validator.LoanMultiplicityValidator;
import com.zopa.loan.calculator.validator.LoanRangeValidator;

import javax.inject.Singleton;
import java.util.function.Predicate;

public class ApplicationModule extends AbstractModule {

    @Provides
    @Singleton
    LenderDataProvider lenderDataProvider() {
        return new InMemoryLenderDataProvider();
    }

    @Provides
    @Singleton
    CompoundInterestCalculator compoundInterestCalculator() {
        return new MonthlyCompoundInterestCalculator();
    }

    @Provides
    @Singleton
    Predicate<Long> loanValidator(ZopaConfig zopaConfig) {
        LoanRangeValidator rangeValidator = new LoanRangeValidator(zopaConfig.getMinimumLoanAllowed(), zopaConfig.getMinimumLoanAllowed());
        LoanMultiplicityValidator multiplicityValidator = new LoanMultiplicityValidator(zopaConfig.getLoanMultiplicity());
        return rangeValidator.and(multiplicityValidator);
    }

    @Provides
    @Singleton
    ZopaRateSystem zopaRateSystem(LenderDataProvider lenderDataProvider, CompoundInterestCalculator compoundInterestCalculator, Predicate<Long> loanValidator) {
        return new ZopaRateSystem(lenderDataProvider, compoundInterestCalculator, loanValidator);
    }

}
