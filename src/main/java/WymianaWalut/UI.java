package WymianaWalut;

import java.io.PrintStream;
import java.util.List;

public class UI {

    private final PrintStream printStream;

    public UI(PrintStream printStream) {
        this.printStream = printStream;
    }


    public void display(String message) {
        printStream.println(message);
    }

    public void displayMenu(List<ExchangeRate> listOfAvailableCurrencies,
                            double sellingCommission, double buyingCommission) {
        printStream.println("List of available currencies: ");
        for (ExchangeRate rate : listOfAvailableCurrencies
        ) {
            printStream.println(rate.getName() + " - " + rate.getRate());
        }
        printStream.println();
        printStream.println("Prices displayed in relation to US Dollar value in format: currency - rate to USD");
        display("Commissions applied: ");
        display("Buying: " + buyingCommission);
        display("Selling: " + sellingCommission);
        printStream.println("Available actions:");
        printStream.println("1. BUY");
        printStream.println("2. SELL");
        printStream.println();
    }

    public void summerizeTransaction(String currencyToExchange, double amount, String target,
                                     double result, double sellingCommission, double buyingCommission) {
        display("Exchanged:");
        display(currencyToExchange + " " + amount);
        display("To:");
        display(target + " " + result);
        display("Commissions applied: ");
        display("Buying: " + buyingCommission);
        display("Selling: " + sellingCommission);
        display("");
    }
}
