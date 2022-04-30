import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import models.Hero;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        //start session
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("username", request.session().attribute("username"));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // Heroes data form
        get("/heroes/delete", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            Hero.clearAll();
            model.put("heroes", Hero.getHero());
            return new ModelAndView(model, "hero-page.hbs");
        }, new HandlebarsTemplateEngine());
        get("/create/hero", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            return modelAndView(model, "form-hero.hbs");
            }, new HandlebarsTemplateEngine());
        post("/heroes/new", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String powers = request.queryParams("powers");
            String weakness = request.queryParams("weakness");
            Hero newhero = new Hero(name, age,powers, weakness);
            model.put("heroes", newhero);
            return new ModelAndView(model, "form-hero.hbs");
        }, new HandlebarsTemplateEngine());
        get("/hero", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> heroes = Hero.getHero();
            model.put("heroes",heroes);
            return modelAndView(model, "hero-page.hbs");
        }, new HandlebarsTemplateEngine());
        get("/heroes/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idTolook = Integer.parseInt(request.queryParams(":id"));
            Hero lookedHero = Hero.findById(idTolook);
            model.put("heroes", lookedHero);
            return new ModelAndView(model, "hero-page.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
