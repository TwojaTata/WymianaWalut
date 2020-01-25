package WymianaWalut;

public class ExchangeRate {
    private final Double rate;
    private final String name;

    public ExchangeRate(String name, Double rate) {
        this.rate = rate;
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public String getName() {
        return name;
    }


}
