package com.SpingWithBushan;

import java.io.IOException;
import java.util.Scanner;

public class CurrencyConverterApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeRateService rateService = new ExchangeRateService();
        CurrencyConverter converter = new CurrencyConverter();

        System.out.println("## Currency Converter ##");
        System.out.print("Enter base currency (e.g. USD): ");
        String base = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g. INR): ");
        String target = scanner.next().toUpperCase();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        try {
            double rate = rateService.getExchangeRate(base, target);
            double converted = converter.convert(amount, rate);
            System.out.printf("%.2f %s = %.2f %s%n", amount, base, converted, target);
        } catch (IOException e) {
            System.out.println("Error fetching exchange rate: " + e.getMessage());
        }

        scanner.close();
    }
}
