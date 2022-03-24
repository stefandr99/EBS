package algo;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Generator {
    List<String> companies;

    final List<Date> dates;

    Random random;

    public Generator() {
        companies = Arrays.asList("Google", "Apple", "Tesla", "UIPath");
        dates = Arrays.asList(Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(60)), Date.from(Instant.now().plusSeconds(120)));
        random = new Random();
    }

    public List<String> getCompanies() {
        return companies;
    }

    public List<Date> getDates() {
        return dates;
    }

    public Random getRandom() {
        return random;
    }
}
