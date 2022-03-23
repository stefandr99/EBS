package items;

import java.util.Date;

public class Field {
    String field;

    String operator;

    String stringValue = null;

    Double numericalValue = null;

    Date dateValue = null;

    public void setField(String field) {
        this.field = field;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }


    public String getField() {
        return field;
    }

    public String getOperator() {
        return operator;
    }

    public String getValue() {
        return stringValue;
    }

    public void setValue(String value) {
        this.stringValue = value;
    }

    public Double getNumericalValue() {
        return numericalValue;
    }

    public void setNumericalValue(Double numericalValue) {
        this.numericalValue = numericalValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    @Override
    public String toString() {
        return '(' + field + ", " + operator + ", " + (stringValue != null ? stringValue : (numericalValue != null ? numericalValue : dateValue)) + ')';
    }
}
