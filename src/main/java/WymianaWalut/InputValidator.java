package WymianaWalut;

import java.util.List;

public class InputValidator {

    private final UI ui;
    private final List<ExchangeRate> availableCurrencies;

    public InputValidator(UI ui, List<ExchangeRate> availableCurrencies) {
        this.ui = ui;
        this.availableCurrencies = availableCurrencies;
    }

    public Integer validateActionNumber(String input) {
        return Integer.valueOf(input);
    }

    public double validateAmount(String input) {
        return Double.valueOf(input);
    }
}
