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

    public String getOperator() {
        return operator;
    }

    public void setValue(String value) {
        this.stringValue = value;
    }

    public void setNumericalValue(Double numericalValue) {
        this.numericalValue = numericalValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    @Override
    public String toString() {
        return '(' + field + ", " + operator + ", " + (stringValue != null ? stringValue : (numericalValue != null ? numericalValue : dateValue)) + ')';
    }
}
