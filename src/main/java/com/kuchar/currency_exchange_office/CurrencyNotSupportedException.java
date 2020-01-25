package com.kuchar.currency_exchange_office;

public class CurrencyNotSupportedException extends Throwable {
    public CurrencyNotSupportedException(String s) {
        super(s);
    }
}
