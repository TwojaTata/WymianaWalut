package com.kuchar.currency_exchange_office;

import java.util.*;

public class CurrencyExchanger {

    private final Map<String, Double> exchangeRatesToUSDollar = new HashMap<>();
    private final double buyingCommission;
    private final double sellingCommission;
    private final List<ExchangeRate> listOfAvailableCurrencies = new ArrayList<>();

    public CurrencyExchanger(double buyingCommission, double sellingCommission) {
        this.buyingCommission = buyingCommission;
        this.sellingCommission = sellingCommission;
    }

    public void addCurrencyExchangeRate(ExchangeRate exchangeRate) throws CurrencyAlreadyAddedException {
        if (exchangeRatesToUSDollar.containsKey(exchangeRate.getName().toLowerCase())) {
            throw new CurrencyAlreadyAddedException("Exchange rate for this currency is already specified");
        }
        exchangeRatesToUSDollar.put(exchangeRate.getName().toLowerCase(), exchangeRate.getRate());
        listOfAvailableCurrencies.add(exchangeRate);
    }

    public Double exchange(String nameOfCurrency,
                           Double amountOfMoney,
                           String targetCurrency,
                           TransactionType transactionType) throws CurrencyNotSupportedException {

        validate(nameOfCurrency.toLowerCase(), amountOfMoney, targetCurrency);
        double commission = computeCommission(transactionType, targetCurrency, amountOfMoney);

        return exchangeUSDollarToTargetCurrency
                (targetCurrency, exchangeToUSDollar(nameOfCurrency, amountOfMoney)) - commission;
    }

    public List<ExchangeRate> getListOfAvailableCurrencies() {
        return listOfAvailableCurrencies;
    }

    public double getSellingCommission() {
        return sellingCommission;
    }

    public double getBuyingCommission() {
        return buyingCommission;
    }

    private Double computeCommission(TransactionType transactionType, String targetCurrency, Double amountOfMoney) {
        if (transactionType.equals(TransactionType.BUY)) {
            return exchangeToUSDollar(targetCurrency, amountOfMoney) * buyingCommission;
        } else {
            return exchangeToUSDollar(targetCurrency, amountOfMoney) * sellingCommission;
        }
    }

    private Double exchangeUSDollarToTargetCurrency(String targetCurrency, Double amountOfMoney) {
        return exchangeRatesToUSDollar.get(targetCurrency.toLowerCase()) / amountOfMoney;
    }

    private Double exchangeToUSDollar(String targetCurrency, Double amountOfMoney) {
        System.out.println(targetCurrency.toLowerCase());
        exchangeRatesToUSDollar.forEach((k, v) -> System.out.println(k + v));
        return exchangeRatesToUSDollar.get(targetCurrency.toLowerCase()) / amountOfMoney;
    }

    private void validate(String nameOfCurrency, Double amountOfMoney, String targetCurrency)
            throws CurrencyNotSupportedException {
        if (!exchangeRatesToUSDollar.containsKey(nameOfCurrency.toLowerCase()) ||
                !exchangeRatesToUSDollar.containsKey(targetCurrency.toLowerCase())) {
            throw new CurrencyNotSupportedException("Exchange rate of " + nameOfCurrency + " is not specified");
        } else if (amountOfMoney < 0) {
            throw new IllegalArgumentException("Amount of money can't be negative");
        }
    }
}