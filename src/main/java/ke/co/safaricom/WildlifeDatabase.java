package ke.co.safaricom;

import java.util.ArrayList;
import java.util.List;

public class WildlifeDatabase {

    private static List<Animal> animals = new ArrayList<>();
    private static List<EndangeredAnimal> endangeredAnimals = new ArrayList<>();
    private static List<Sighting> sightings = new ArrayList<>();

    public static void setup() {
        // Database setup goes here
    }

    public static List<Animal> getAllAnimals() {
        return animals;
    }

    public static List<EndangeredAnimal> getAllEndangeredAnimals() {
        return endangeredAnimals;
    }

    public static void addSighting(Sighting sighting) {
        sightings.add(sighting);
    }
}

