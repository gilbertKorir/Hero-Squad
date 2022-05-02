package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SquadTest {
    private Squad setUpNewSquad() {
        ArrayList<Hero> heroes=new ArrayList<Hero>();
        Hero firsthero = new Hero("Spider Bee",45,"Fuelling", "walking long distance");
        Hero secondthero = new Hero("Melvic monker",30,"Flying", "Innactive during raining");
//        Hero hero=new Hero();
//        Hero otherHero=new Hero();
        heroes.add(firsthero);
        heroes.add(secondthero);
        return new Squad("queen",5, "plantiff",heroes);
    }
    @Test
    public void returnAllInstances_true() throws Exception {
        Squad squad=setUpNewSquad();
        Squad otherSquad=setUpNewSquad();
        assertEquals(2,Squad.getSquads().size());
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}