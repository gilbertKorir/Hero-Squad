package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SquadTest {
    @AfterEach
    void tearDown() {
    }
    private Squad setUpNewSquad() {
        ArrayList<Hero> heroes=new ArrayList<Hero>();
        Hero firsthero = new Hero("Spider Bee",45,"Fuelling", "walking long distance");
        Hero secondthero = new Hero("Melvic monker",30,"Flying", "Innactive during raining");

        heroes.add(firsthero);
        heroes.add(secondthero);
        return new Squad("colbox",5, "plantiff",heroes);
    }
    @Test
    public void returnAllInstances_true() throws Exception {
        Squad squad=setUpNewSquad();
        Squad otherSquad=setUpNewSquad();
        assertEquals(3,Squad.getSquads().size());
    }

    @Test
    public void deleteAllSquads() {
        Squad squad=setUpNewSquad();
        Squad otherSquad=setUpNewSquad();
        Squad.clearAll();
        assertEquals(0,Squad.getSquads().size());

    }
    @Test
    public void allSquadsContainedInSquad() throws Exception {
        Squad squad=setUpNewSquad();
        Squad otherSquad=setUpNewSquad();
        assertTrue(Squad.getSquads().contains(squad));
        assertTrue(Squad.getSquads().contains(otherSquad));
    }
    @Test
    public void check_squad_cause(){
        Squad squad=setUpNewSquad();
//        Squad mysquad = new Squad("colbox",5, "plantiff", heroes);
        assertEquals(true, squad instanceof Squad);

    }
    @BeforeEach
    void setUp() {
    }

}