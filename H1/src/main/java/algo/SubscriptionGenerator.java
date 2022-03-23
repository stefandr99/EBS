package algo;

import components.Subscription;
import items.Field;
import javafx.util.Pair;

import java.time.Instant;
import java.util.*;

public class SubscriptionGenerator extends Generator {
    private int index;
    private List<String> operators;
    private final List<String> fields;
    private List<Subscription> subscriptions;
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

    public void generate(Map<String, Integer> fieldFrequency, Map<String, Pair<String, Integer>> operatorFrequency) {
        for (String field : fields) {
            operators = new ArrayList<>(Arrays.asList("=", "!=", "<", "<=", ">", ">="));
            int quantity = (int) Math.round(size * fieldFrequency.get(field) / 100.00);

            String operator = operatorFrequency.get(field).getKey();
            operators.remove(operator);
            int operatorPercentage = operatorFrequency.get(field).getValue();
            int operatorQuantity = (int) Math.ceil(quantity * operatorPercentage / 100.00);

            for (int j = 0; j < quantity; j++) {
                Subscription subscription = subscriptions.get(index % size);
                switch (field) {
                    case "company":
                        subscription.setCompany(new Field());
                        currentField = subscription.getCompany();
                        currentField.setValue(companies.get(random.nextInt(getCompanies().size()))); // company name
                        break;
                    case "value":
                        subscription.setValue(new Field());
                        currentField = subscription.getValue();
                        currentField.setNumericalValue(2.33);
                        break;
                    case "drop":
                        subscription.setDrop(new Field());
                        currentField = subscription.getDrop();
                        currentField.setNumericalValue(2.33);
                        break;
                    case "variation":
                        subscription.setVariation(new Field());
                        currentField = subscription.getVariation();
                        currentField.setNumericalValue(2.33);
                        break;
                    case "date":
                        subscription.setDate(new Field());
                        currentField = subscription.getDate();
                        currentField.setDateValue(Date.from(Instant.now()));
                        break;
                }

                currentField.setField(field); // field type

                if (operatorQuantity > 0) {
                    currentField.setOperator(operator);
                } else {
                    currentField.setOperator(operators.get(random.nextInt(operators.size())));
                }

                operatorQuantity--;
                index++;
            }
        }
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }
}
