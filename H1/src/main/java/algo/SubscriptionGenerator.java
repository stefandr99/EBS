package algo;

import components.Publication;
import components.Subscription;
import items.Field;
import javafx.util.Pair;

import java.util.*;

public class SubscriptionGenerator extends Generator {
    private int index;
    private final List<String> fields;
    private final List<Subscription> subscriptions;
    private final int size;
    private Field currentField;

    public SubscriptionGenerator(int size) {
        super();
        this.size = size;
        index = 0;

        fields = new ArrayList<>(Arrays.asList("company", "value", "drop", "variation", "date"));
        subscriptions = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            subscriptions.add(new Subscription());
        }
    }

    public void generate(Publication publication, Map<String, Double> fieldFrequency, Map<String, Pair<String, Double>> operatorFrequency) {
        for (String field : fields) {
            String currentOperator;
            String specifiedOperator = "=";
            int operatorQuantity = 0;

            List<String> operators = new ArrayList<>(Arrays.asList("=", "!=", "<", "<=", ">", ">="));
            List<String> equalityOperators = new ArrayList<>(Arrays.asList("=", "!="));

            int quantity;
            if(fieldFrequency.get(field) != null) // if quantity is not sent, a default 50% is applied
                quantity = (int) Math.round(size * fieldFrequency.get(field) / 100.00);
            else quantity = 50;

            // check if exists operation percent for current field
            if(operatorFrequency.get(field) != null) {
                specifiedOperator = operatorFrequency.get(field).getKey();

                // remove specified operator from list in order not to be included when needed to be set
                if(field.equals("company"))
                    equalityOperators.remove(specifiedOperator);
                else
                    operators.remove(specifiedOperator);

                double operatorPercentage = operatorFrequency.get(field).getValue();
                operatorQuantity = (int) Math.ceil(quantity * operatorPercentage / 100.00);
            }

            for (int j = 0; j < quantity; j++) {
                // index % size because we don't want to get out of bounds, so if the percents sum exceeds 100, set from the beginning again
                Subscription subscription = subscriptions.get(index % size);

                // if operator quantity is still > 0, then the operator sent should be selected, else one from the remaining
                if (operatorQuantity > 0) {
                    currentOperator = specifiedOperator;
                }
                else {
                    if(field.equals("company"))
                        currentOperator = equalityOperators.get(random.nextInt(equalityOperators.size()));
                    else
                        currentOperator = operators.get(random.nextInt(operators.size()));
                }

                // get current object based on the current field
                // set current object value from their specific domain (strings for company, double for nums, date for date)
                switch (field) {
                    case "company":
                        subscription.setCompany(new Field());
                        currentField = subscription.getCompany();
                        currentField.setValue(getFieldStringValueBasedOnOperator(currentOperator, publication.getCompany()));
                        break;
                    case "value":
                        subscription.setValue(new Field());
                        currentField = subscription.getValue();
                        currentField.setNumericalValue(getFieldNumericalValueBasedOnOperator(currentOperator, publication.getValue()));
                        break;
                    case "drop":
                        subscription.setDrop(new Field());
                        currentField = subscription.getDrop();
                        currentField.setNumericalValue(getFieldNumericalValueBasedOnOperator(currentOperator, publication.getDrop()));
                        break;
                    case "variation":
                        subscription.setVariation(new Field());
                        currentField = subscription.getVariation();
                        currentField.setNumericalValue(getFieldNumericalValueBasedOnOperator(currentOperator, publication.getVariation()));
                        break;
                    case "date":
                        subscription.setDate(new Field());
                        currentField = subscription.getDate();
                        currentField.setDateValue(getFieldDateValueBasedOnOperator(currentOperator, publication.getDate()));
                        break;
                }

                currentField.setOperator(currentOperator);

                currentField.setField(field);

                operatorQuantity--;
                index++;
            }
        }
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    private String getFieldStringValueBasedOnOperator(String currentOperator, String publication) {
        if(currentOperator.equals("!=")) {
            List<String> copy = new ArrayList<>(Arrays.asList("Google", "Apple", "Tesla", "UIPath"));
            copy.remove(publication);
            return copy.get(random.nextInt(copy.size()));
        }
        else {
            return publication;
        }
    }

    private double getFieldNumericalValueBasedOnOperator(String operator, double publicationValue) {
        if(operator.equals("="))
            return publicationValue;
        else if(operator.contains("<"))
            return publicationValue + random.doubles(0.01, 5.0).findFirst().getAsDouble();
        else
            return publicationValue - random.doubles(0.01, 5.0).findFirst().getAsDouble();

    }

    private Date getFieldDateValueBasedOnOperator(String operator, Date publicationDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(publicationDate);

        if(operator.equals("="))
            return publicationDate;
        else if(operator.contains("<")) {
            c.add(Calendar.MINUTE, random.nextInt(30));
            return c.getTime();
        }
        else {
            c.add(Calendar.MINUTE, -random.nextInt(30));
            return c.getTime();
        }
    }
}
