package models;

import java.util.ArrayList;

public class Squad {
    private int maxsize;
    private String name;
    private String cause;
    private int id;
    private ArrayList<Hero> heroes;
    public static ArrayList<Squad> squads = new ArrayList<Squad>();

    public Squad(String name, int maxsize, String cause, ArrayList<Hero> heroes) {
        this.maxsize = maxsize;
        this.name = name;
        this.cause = cause;
        this.heroes = heroes;
        squads.add(this);
        this.id = squads.size();
    }
    public static ArrayList<Squad> getSquads(){
        return squads;
    }
    public ArrayList<Hero> getHero(){
        return heroes;
    }
    public void setId(int id){
        this.id = id;
    }
    public void deleteSquad(){
        squads.remove(id-1);
    }
    public static void clearAll(){
        squads.clear();
    }
    public static Squad findById(int id){
        try{
            return squads.get(id-1);
        }catch (IndexOutOfBoundsException exception){
            return null;
        }
    }
    public int getId(){
        return id;
    }
    public int getMaxsize(){
        return maxsize;
    }
    public String getName(){
        return name;
    }
    public String getCause(){
        return cause;
    }
}































