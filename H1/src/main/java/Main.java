import algo.PublicationGenerator;
import algo.SubscriptionGenerator;
import components.Publication;
import components.Subscription;
import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // --- date de intrare
        int subscriptionsSize = 10;
        int publicationsSize = 5;

        double companyPercent = 87.21;
        double valuePercent = 20.00;
        double dropPercent = 20.12;
        double variationPercent = 92.45754757;
        double datePercent = 92.81;

        double companyOperatorPercent = 50.00;
        String companyOperator = "=";
        double valueOperatorPercent = 50.00;
        String valueOperator = "!=";
        double dropOperatorPercent = 50.00;
        String dropOperator = ">";
        double variationOperatorPercent = 50.00;
        String variationOperator = ">";
        double dateOperatorPercent = 50.00;
        String dateOperator = "<=";

        // date de intrare ---

        int companyQuantityResult = 0, valueQuantityResult = 0, dropQuantityResult = 0, variationQuantityResult = 0, dateQuantityResult = 0;
        double companyPercentResult, valuePercentResult, dropPercentResult, variationPercentResult, datePercentResult;
        int companyOperatorQuantityResult = 0, valueOperatorQuantityResult = 0, dropOperatorQuantityResult = 0,
                variationOperatorQuantityResult = 0, dateOperatorQuantityResult = 0;
        double companyOperatorPercentResult, valueOperatorPercentResult, dropOperatorPercentResult,
                variationOperatorPercentResult, dateOperatorPercentResult;

        PublicationGenerator publicationGenerator = new PublicationGenerator();
        List<Publication> publications = publicationGenerator.generate(publicationsSize, new Pair<>(0.05, 3.0), new Pair<>(0.1, 0.5), new Pair<>(2.0, 6.9));

        SubscriptionGenerator subscriptions = new SubscriptionGenerator(subscriptionsSize);

        Map<String, Double> fieldFrequency = new HashMap<>();
        fieldFrequency.put("company", companyPercent);
        fieldFrequency.put("value", valuePercent);
        fieldFrequency.put("drop", dropPercent);
        fieldFrequency.put("variation", variationPercent);
        fieldFrequency.put("date", datePercent);

        Map<String, Pair<String, Double>> operatorFrequency = new HashMap<>();
        operatorFrequency.put("company", new Pair<>(companyOperator, companyOperatorPercent));
        operatorFrequency.put("value", new Pair<>(valueOperator, valueOperatorPercent));
        operatorFrequency.put("drop", new Pair<>(dropOperator, dropOperatorPercent));
        operatorFrequency.put("variation", new Pair<>(variationOperator, variationOperatorPercent));
        operatorFrequency.put("date", new Pair<>(dateOperator, dateOperatorPercent));

        subscriptions.generate(publications.get(0), fieldFrequency, operatorFrequency);

        for(Subscription subscription : subscriptions.getSubscriptions()) {
            if(subscription.getCompany() != null) {
                companyQuantityResult++;

                if(operatorFrequency.get("company") != null && subscription.getCompany().getOperator().equals(operatorFrequency.get("company").getKey())) {
                    companyOperatorQuantityResult++;
                }
            }

            if(subscription.getValue() != null) {
                valueQuantityResult++;

                if(operatorFrequency.get("value") != null && subscription.getValue().getOperator().equals(operatorFrequency.get("value").getKey())) {
                    valueOperatorQuantityResult++;
                }
            }

            if(subscription.getDrop() != null) {
                dropQuantityResult++;

                if(operatorFrequency.get("drop") != null && subscription.getDrop().getOperator().equals(operatorFrequency.get("drop").getKey())) {
                    dropOperatorQuantityResult++;
                }
            }

            if(subscription.getVariation() != null) {
                variationQuantityResult++;

                if(operatorFrequency.get("variation") != null && subscription.getVariation().getOperator().equals(operatorFrequency.get("variation").getKey())) {
                    variationOperatorQuantityResult++;
                }
            }

            if(subscription.getDate() != null) {
                dateQuantityResult++;

                if(operatorFrequency.get("date") != null && subscription.getDate().getOperator().equals(operatorFrequency.get("date").getKey())) {
                    dateOperatorQuantityResult++;
                }
            }
        }

        companyPercentResult = calculatePercent(subscriptionsSize, companyQuantityResult);
        valuePercentResult = calculatePercent(subscriptionsSize, valueQuantityResult);
        dropPercentResult = calculatePercent(subscriptionsSize, dropQuantityResult);
        variationPercentResult = calculatePercent(subscriptionsSize, variationQuantityResult);
        datePercentResult = calculatePercent(subscriptionsSize, dateQuantityResult);

        companyOperatorPercentResult = calculatePercent(companyQuantityResult, companyOperatorQuantityResult);
        valueOperatorPercentResult = calculatePercent(valueQuantityResult, valueOperatorQuantityResult);
        dropOperatorPercentResult = calculatePercent(dropQuantityResult, dropOperatorQuantityResult);
        variationOperatorPercentResult = calculatePercent(variationQuantityResult, variationOperatorQuantityResult);
        dateOperatorPercentResult = calculatePercent(dateQuantityResult, dateOperatorQuantityResult);

        System.out.println("Company request: " + companyPercent + "; result: " + companyPercentResult);
        System.out.println("Value request: " + valuePercent + "; result: " + valuePercentResult);
        System.out.println("Drop request: " + dropPercent + "; result: " + dropPercentResult);
        System.out.println("Variation request: " + variationPercent + "; result: " + variationPercentResult);
        System.out.println("Date request: " + datePercent + "; result: " + datePercentResult);

        System.out.println();
        System.out.println("Company operator request: " + companyOperatorPercent + "; result: " + companyOperatorPercentResult);
        System.out.println("Value operator request: " + valueOperatorPercent + "; result: " + valueOperatorPercentResult);
        System.out.println("Drop operator request: " + dropOperatorPercent + "; result: " + dropOperatorPercentResult);
        System.out.println("Variation operator request: " + variationOperatorPercent + "; result: " + variationOperatorPercentResult);
        System.out.println("Date operator request: " + dateOperatorPercent + "; result: " + dateOperatorPercentResult);

        PrintStream publicationFileStream = new PrintStream("publications.txt");
        for(Publication publication : publications) {
            StringBuilder sb = new StringBuilder();
            sb.append("{(company,\"").append(publication.getCompany()).append("\");(value,").append(publication.getValue())
                    .append(");(drop,").append(publication.getDrop()).append(");(variation,").append(publication.getVariation())
                    .append(");(date,").append(publication.getDate()).append(")}");

            publicationFileStream.println(sb);
        }

        PrintStream subscriptionFileStream = new PrintStream("subscriptions.txt");
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

            subscriptionFileStream.println(sb);
        }
    }

    private static double calculatePercent(int total, int resulted) {
        return (resulted * 100.00) / total;
    }
}
