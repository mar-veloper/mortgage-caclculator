package com.marlearnings;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        int principal = (int)readNumber("Principal: ", 1_000, 1_000_000);
        float annualInterest = (float)readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte)readNumber("Period (Years): ", 1, 30);

        printMortgage(principal, annualInterest, years);
        printRemainingBalance(years, principal, annualInterest);
    }

    private static void printRemainingBalance(byte years, int principal, float annualInterest) {
        System.out.println(" ");
        System.out.println("PAYMENT PLAN");
        System.out.println("------------");
        for (int month = 1; month <= years * MONTHS_IN_YEAR; month++) {
            String balance = calculateBalance(principal, annualInterest, years, (short)month);
            System.out.println("Month: " + month + ": " + balance);
        }
    }

    private static void printMortgage(int principal, float annualInterest, byte years) {
        String monthlyMortgage = calculateMonthlyMortgage(principal, annualInterest, years);
        System.out.println(" ");
        System.out.println("MONTHLY MORTGAGE");
        System.out.println("----------------");
        System.out.println(monthlyMortgage);
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a string between " + (int)min + " and " + (int)max);
        }
        return value;
    }

    public static String calculateMonthlyMortgage(int principal, float annualInterestRate, byte numberOfYearsToPay) {
        float monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;
        short numberOfMonthsToPay = (short)(numberOfYearsToPay * MONTHS_IN_YEAR);

        double dividend = monthlyInterestRate * Math.pow((1 + monthlyInterestRate), numberOfMonthsToPay);
        double divisor = Math.pow((1 + monthlyInterestRate), numberOfMonthsToPay) - 1;
        double monthlyMortgage = principal * (dividend/divisor);

        return NumberFormat.getCurrencyInstance().format(monthlyMortgage);
    }

    public static String calculateBalance(int principal, float annualInterestRate, byte numberOfYearsToPay, short numberOfPaymentsMade) {
        float monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;
        short numberOfMonthsToPay = (short)(numberOfYearsToPay * MONTHS_IN_YEAR);

        // B = L = principal, c = monthlyInterestRate, n = numberOfMonthsToPay, p = numberOfPaymentsMade
        // B = L[(c1+)^n - (1+c)^p]/[(1+c)^n - 1]
        double dividend = principal * (Math.pow(1 + monthlyInterestRate, numberOfMonthsToPay) - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade));
        double divisor = Math.pow(1 + monthlyInterestRate, numberOfMonthsToPay) - 1;
        double balance = dividend/divisor;

        return NumberFormat.getCurrencyInstance().format(balance);
    }

}
