package com.marlearnings;

public class MortgageReport {

private MortgageCalculator calculator;

    public MortgageReport(MortgageCalculator calculator) {
        this.calculator = calculator;
    }

    public void printMortgage() {
        String monthlyMortgage = calculator.calculateMonthlyMortgage();
        System.out.println(" ");
        System.out.println("MONTHLY MORTGAGE");
        System.out.println("----------------");
        System.out.println(monthlyMortgage);
    }

    public void printRemainingBalance() {
        System.out.println(" ");
        System.out.println("PAYMENT PLAN");
        System.out.println("------------");
        for (String balance : calculator.getRemainingBalance())
            System.out.println(balance);
    }
}