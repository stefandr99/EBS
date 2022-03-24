package components;

import java.util.Date;

public class Publication {
    private final String Company;

    private final double value;

    private final double drop;

    private final double variation;

    private final Date date;

    public Publication(String company, double value, double drop, double variation, Date date) {
        Company = company;
        this.value = value;
        this.drop = drop;
        this.variation = variation;
        this.date = date;
    }

    public String getCompany() {
        return Company;
    }

    public double getValue() {
        return value;
    }

    public double getDrop() {
        return drop;
    }

    public double getVariation() {
        return variation;
    }

    public Date getDate() {
        return date;
    }
}
