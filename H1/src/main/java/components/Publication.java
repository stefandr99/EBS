package components;

import java.util.Date;

public class Publication {
    private String Company;

    private double value;

    private double drop;

    private double variation;

    private Date date;

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

    public void setCompany(String company) {
        Company = company;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getDrop() {
        return drop;
    }

    public void setDrop(double drop) {
        this.drop = drop;
    }

    public double getVariation() {
        return variation;
    }

    public void setVariation(double variation) {
        this.variation = variation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
