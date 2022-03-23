package components;

import com.fasterxml.jackson.annotation.JsonInclude;
import items.Field;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subscription {
    private Field company;

    private Field value;

    private Field drop;

    private Field variation;

    private Field date;

    public Field get(String fieldName) {
        switch (fieldName){
            case "company":
                return company;
            case "value":
                return value;
            case "drop":
                return drop;
            case "variation":
                return variation;
            case "calendar":
                return date;
        }

        return null;
    }

    public Field getCompany() {
        return company;
    }

    public void setCompany(Field company) {
        this.company = company;
    }

    public Field getValue() {
        return value;
    }

    public void setValue(Field value) {
        this.value = value;
    }

    public Field getDrop() {
        return drop;
    }

    public void setDrop(Field drop) {
        this.drop = drop;
    }

    public Field getVariation() {
        return variation;
    }

    public void setVariation(Field variation) {
        this.variation = variation;
    }

    public Field getDate() {
        return date;
    }

    public void setDate(Field date) {
        this.date = date;
    }
}
