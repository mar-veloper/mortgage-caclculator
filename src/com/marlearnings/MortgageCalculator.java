package com.marlearnings;

import java.text.NumberFormat;

public class MortgageCalculator {
    private final byte MONTHS_IN_YEAR = 12;
    private final NumberFormat currency;
    private int principal;
    private float annualInterestRate;
    private byte numberOfYearsToPay;

    public MortgageCalculator (int principal, float annualInterestRate, byte years) {
        this.setPrincipal(principal);
        this.setAnnualInterestRate(annualInterestRate);
        this.setNumberOfYearsToPay(years);
        currency = NumberFormat.getCurrencyInstance();
    }
    public String calculateMonthlyMortgage() {
        double numerator = getMonthlyInterestRate() * Math.pow((1 + getMonthlyInterestRate()), getNumberOfMonthsToPay());
        double denominator = Math.pow((1 + getMonthlyInterestRate()), getNumberOfMonthsToPay()) - 1;
        double monthlyMortgage = principal * (numerator/denominator);

        return currency.format(monthlyMortgage);
    }
    public String calculateBalance(int numberOfPaymentsMade) {
        // B = L = principal, c = monthlyInterestRate, n = numberOfMonthsToPay, p = numberOfPaymentsMade
        // B = L[(1+c)^n - (1+c)^p]/[(1+c)^n - 1]
        double numerator = principal * (Math.pow(1 + getMonthlyInterestRate(), getNumberOfMonthsToPay()) - Math.pow(1 + getMonthlyInterestRate(), numberOfPaymentsMade));
        double denominator = Math.pow(1 + getMonthlyInterestRate(), getNumberOfMonthsToPay()) - 1;
        double balance = numerator/denominator;

        return currency.format(balance);
    }



    public String[] getRemainingBalance() {
      String[] balances = new String[getNumberOfMonthsToPay()];
        for (int month = 1; month <= balances.length; month++) {
            balances[month - 1] = this.calculateBalance(month);
        };
        return balances;
    }
    private short getNumberOfMonthsToPay() {
        return (short)(numberOfYearsToPay * MONTHS_IN_YEAR);
    }

    private void setPrincipal(int principal) {
        this.principal = principal;
    }

    private void setAnnualInterestRate(float annualInterestRate){
         this.annualInterestRate = annualInterestRate;
    }

    private void setNumberOfYearsToPay(byte  numberOfYearsToPay){
         this.numberOfYearsToPay =  numberOfYearsToPay;
    }
    private float getMonthlyInterestRate() {
        final byte PERCENT = 100;
        return (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;
    }


}
