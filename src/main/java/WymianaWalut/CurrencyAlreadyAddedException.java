package WymianaWalut;

public class CurrencyAlreadyAddedException extends Throwable {

    public CurrencyAlreadyAddedException(String message) {
        super(message);
    }
}
