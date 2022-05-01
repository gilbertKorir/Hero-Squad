import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import models.Hero;
import models.Squad;
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
        //initial array of the heroes
        Hero firsthero = new Hero("Spider Bee",45,"Fuelling", "walking long distance");
        Hero secondthero = new Hero("Melvic monker",30,"Flying", "Innactive during raining");
        Hero thirdthero = new Hero("Trojan",55,"Hearing", "Fears a bomb");
//        Squad squad1 = new Squad("king",5,"sexism",);
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
            model.put("hero", lookedHero);
            ArrayList<Hero> heroes = Hero.getHero();
            model.put("heroes", heroes);
            return new ModelAndView(model, "hero-page.hbs");
        }, new HandlebarsTemplateEngine());
        //delete hero
        get("/hero/:id/delete",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete=Integer.parseInt(request.params(":id"));
            Hero lookedHero=Hero.findById(idOfHeroToDelete);
            for (int i=idOfHeroToDelete;i<Hero.getHero().size();i++){
                Hero.getHero().get(i).setId(Hero.getHero().get(i).getId()-1);
            }
            lookedHero.deleteHero();
            ArrayList<Hero> heroes = Hero.getHero();
            model.put("heroes", heroes);
            return new ModelAndView(model,"hero-page.hbs");
        },new HandlebarsTemplateEngine());

        // Generate squad data and routings
        get("/squads/delete",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Squad.clearAll();
            ArrayList<Hero> heroes = Hero.getHero();
            for (int i=0; i<heroes.size(); i++){
                heroes.get(i).updatehero(false);
            }
            model.put("squads",Squad.getSquads());
            return new ModelAndView(model,"squad-page.hbs");
        }, new HandlebarsTemplateEngine());

        get("/create/squad",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> heroes = Hero.getHero();
            ArrayList<Hero> heroList = new ArrayList<>();
            for (int i=0; i<heroes.size();i++){
                if(heroes.get(i).isOccupied()==false){
                    heroList.add(heroes.get(i));
                }
            }
            model.put("heroes", Hero.getHero());
        return new ModelAndView(model, "form-squad.hbs");
        }, new HandlebarsTemplateEngine());

        post("squads/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int maxsize = Integer.parseInt(request.queryParams("size"));
            String cause = request.queryParams("cause");
            ArrayList<Hero> heroes = new ArrayList<>();
            if(request.queryParamsValues("heroes")!=null){
                String[] heroesList = request.queryParamsValues("heroes");
                for(int i=0; i<heroesList.length;i++){
                    Hero addhero =Hero.findById(Integer.parseInt(heroesList[i]));
                    if(heroes.size()<maxsize){
                        addhero.updatehero(true);
                        heroes.add(addhero);
                    }
                }
            }
            Squad newsquad = new Squad(name,maxsize,cause,heroes);
            model.put("squads", Squad.getSquads());
            return new ModelAndView(model, "form-squad.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squad",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("squads", Squad.getSquads());
            return new ModelAndView(model, "squad-page.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squads/:id",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idtolook =  Integer.parseInt(request.queryParams(":id"));
            Squad lookedsquad = Squad.findById(idtolook);
            model.put("squad", lookedsquad);
            ArrayList<Squad> squads = Squad.getSquads();
            model.put("squads", squads);
            return new ModelAndView(model, "squad-page.hbs");
        }, new HandlebarsTemplateEngine());
    }
}



























