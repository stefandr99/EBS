package algo;

import components.Publication;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublicationGenerator extends Generator{
    public PublicationGenerator() {
        super();
    }

    public Publication generate(Pair<Double, Double> valueLimits, Pair<Double, Double> dropLimits, Pair<Double, Double> variationLimits) {
        String company = companies.get(random.nextInt(companies.size()));
        double value = random.doubles(valueLimits.getKey(), valueLimits.getValue()).findFirst().getAsDouble();
        double drop = random.doubles(dropLimits.getKey(), dropLimits.getValue()).findFirst().getAsDouble();
        double variation = random.doubles(variationLimits.getKey(), variationLimits.getValue()).findFirst().getAsDouble();
        Date date = dates.get(random.nextInt(dates.size()));

        return new Publication(company, value, drop, variation, date);
    }

    public List<Publication> generate(int size, Pair<Double, Double> valueLimits, Pair<Double, Double> dropLimits, Pair<Double, Double> variationLimits) {
        List<Publication> publications = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            publications.add(generate(valueLimits, dropLimits, variationLimits));
        }

        return publications;
    }
}
