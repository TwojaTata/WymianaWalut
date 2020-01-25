package com.kuchar.currency_exchange_office;

public class CurrencyAlreadyAddedException extends Throwable {

    public CurrencyAlreadyAddedException(String message) {
        super(message);
    }
}
