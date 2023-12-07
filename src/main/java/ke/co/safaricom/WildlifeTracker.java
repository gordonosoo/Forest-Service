package ke.co.safaricom;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

public class WildlifeTracker {

    public static void main(String[] args) {
        Spark.staticFileLocation("/public");

        // Database setup
        WildlifeDatabase.setup();

        // Routes
        Spark.get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/index.vtl");
            model.put("animals", WildlifeDatabase.getAllAnimals());
            model.put("endangeredAnimals", WildlifeDatabase.getAllEndangeredAnimals());
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        Spark.post("/sighting", (request, response) -> {
            try {
                String type = request.queryParams("type");
                String location = request.queryParams("location");
                String rangerName = request.queryParams("rangerName");

                if (type.equals("animal")) {
                    int animalId = Integer.parseInt(request.queryParams("animalId"));
                    WildlifeDatabase.addSighting(new AnimalSighting(animalId, location, rangerName));
                } else if (type.equals("endangeredAnimal")) {
                    int endangeredAnimalId = Integer.parseInt(request.queryParams("endangeredAnimalId"));
                    String health = request.queryParams("health");
                    String age = request.queryParams("age");
                    WildlifeDatabase.addSighting(new EndangeredAnimalSighting(endangeredAnimalId, location, rangerName, health, age));
                } else {
                    throw new InvalidSightingTypeException("Invalid sighting type");
                }

                response.redirect("/");
                return null;
            } catch (IncompleteFormException | NumberFormatException e) {
                Map<String, Object> model = new HashMap<>();
                model.put("error", e.getMessage());
                model.put("template", "templates/error.vtl");
                return new ModelAndView(model, "templates/layout.vtl");
            }
        }, new VelocityTemplateEngine());
    }
}
