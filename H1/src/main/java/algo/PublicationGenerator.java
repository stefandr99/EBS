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

    public Publication generate(Pair<Integer, Integer> valueLimits, Pair<Integer, Integer> dropLimits, Pair<Integer, Integer> variationLimits) {
        String company = companies.get(random.nextInt(companies.size()));
        int value = random.ints(valueLimits.getKey(), valueLimits.getValue()).findFirst().getAsInt();
        int drop = random.ints(dropLimits.getKey(), dropLimits.getValue()).findFirst().getAsInt();
        int variation = random.ints(variationLimits.getKey(), variationLimits.getValue()).findFirst().getAsInt();
        Date date = dates.get(random.nextInt(dates.size()));

        return new Publication(company, value, drop, variation, date);
    }

    public List<Publication> generate(int size, Pair<Integer, Integer> valueLimits, Pair<Integer, Integer> dropLimits, Pair<Integer, Integer> variationLimits) {
        List<Publication> publications = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            publications.add(generate(valueLimits, dropLimits, variationLimits));
        }

        return publications;
    }
}
