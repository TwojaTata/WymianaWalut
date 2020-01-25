package com.kuchar.currency_exchange_office;

import java.util.Scanner;

/**
 * Starting point of an application.
 * <p>
 * Before running add exchanges rates of given currency in relation to USD.
 * Where 1$ = [amount] Currency
 */
public class Main {
    public static void main(String[] args) throws CurrencyAlreadyAddedException {

        CurrencyExchanger exchanger = new CurrencyExchanger(0.05, 0.05);
        exchanger.addCurrencyExchangeRate(new ExchangeRate("EURO", 0.9058));
        exchanger.addCurrencyExchangeRate(new ExchangeRate("PLN", 3.8611));
        exchanger.addCurrencyExchangeRate(new ExchangeRate("USD", 1.0));

        UI ui = new UI(System.out);
        InputValidator inputValidator = new InputValidator(ui, exchanger.getListOfAvailableCurrencies());

        Scanner scanner = new Scanner(System.in);
        TransactionType transactionType;
        String userAnswer;
        double result;
        double amount;

        ui.display("Welcome to currency exchange office");
        do {
            ui.displayMenu(exchanger.getListOfAvailableCurrencies(),
                    exchanger.getSellingCommission(), exchanger.getBuyingCommission());
            ui.display("Select action");
            userAnswer = scanner.nextLine();
            int action = inputValidator.validateActionNumber(userAnswer);
            switch (action) {
                case 1: {
                    transactionType = TransactionType.BUY;
                }
                case 2: {
                    transactionType = TransactionType.SELL;
                }
                default: {
                    transactionType = TransactionType.BUY;
                }
            }
            ui.display("Provide currency which you wish to exchange");
            String currencyToExchange = scanner.nextLine();
            ui.display("Insert amount of money you wish to exchange");
            try {
                amount = inputValidator.validateAmount(scanner.nextLine());
            } catch (NumberFormatException e) {
                ui.display("Not such option");
                break;
            }
            ui.display("Provide currency you wish to exchange for");
            String target = scanner.nextLine();
            try {
                result = exchanger.exchange(currencyToExchange, amount, target, transactionType);
            } catch (CurrencyNotSupportedException e) {
                ui.display(e.getMessage());
                break;
            }
            ui.summerizeTransaction(currencyToExchange, amount, target, result,
                    exchanger.getSellingCommission(), exchanger.getBuyingCommission());
        }
        while (true);
    }
}