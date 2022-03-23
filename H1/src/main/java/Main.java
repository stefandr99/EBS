import algo.SubscriptionGenerator;
import components.Subscription;
import javafx.util.Pair;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        SubscriptionGenerator subscriptions = new SubscriptionGenerator(10);

        Map<String, Integer> fieldFrequency = new HashMap<>();
        fieldFrequency.put("company", 20);
        fieldFrequency.put("value", 20);
        fieldFrequency.put("drop", 20);
        fieldFrequency.put("variation", 90);
        fieldFrequency.put("date", 90);

        Map<String, Pair<String, Integer>> operatorFrequency = new HashMap<>();
        operatorFrequency.put("company", new Pair<>("=", 50));
        operatorFrequency.put("value", new Pair<>("<=", 50));
        operatorFrequency.put("drop", new Pair<>("<=", 50));
        operatorFrequency.put("variation", new Pair<>("<=", 50));
        operatorFrequency.put("date", new Pair<>("=", 50));

        subscriptions.generate(fieldFrequency, operatorFrequency);

        for(Subscription s : subscriptions.getSubscriptions()) {
            StringBuilder sb = new StringBuilder();
            sb.append("{ ");

            if(s.getCompany() != null) {
                sb.append(s.getCompany().toString());
                sb.append("; ");
            }

            if(s.getValue() != null) {
                sb.append(s.getValue().toString());
                sb.append("; ");
            }

            if(s.getDrop() != null) {
                sb.append(s.getDrop().toString());
                sb.append("; ");
            }

            if(s.getVariation() != null) {
                sb.append(s.getVariation().toString());
                sb.append("; ");
            }

            if(s.getDate() != null) {
                sb.append(s.getDate().toString());
                sb.append("; ");
            }

            sb.append("}");

            System.out.println(sb);
        }
    }
}
