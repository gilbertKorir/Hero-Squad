package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    private Hero setUpNewHero() {
        return new Hero("Trojan",23,"fuelling","innactive during raining");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void createInstanceOfHero_true() throws Exception{
        Hero hero=setUpNewHero();
        assertEquals(true,hero instanceof Hero);
    }
    @Test
    public void allInstancesAreFoundInHero() throws Exception{
        Hero hero=setUpNewHero();
        Hero firsthero = new Hero("Spider Bee",45,"Fuelling", "walking long distance");
        assertTrue(Hero.getHero().contains(hero));
        assertTrue(Hero.getHero().contains(firsthero));
    }

    @Test
    public void check_name_true(){
        Hero hero=setUpNewHero();
        Hero otherHero=new Hero("Trojan",23,"fuelling","innactive during raining");
        assertEquals(true,otherHero instanceof Hero);

    }
    public void check_age_true(){
        Hero hero=setUpNewHero();
        Hero otherHero=new Hero("Trojan",23,"fuelling","innactive during raining");
        assertEquals(23,otherHero.getAge());

    }

    @Test
    public void deleteAll() {
        Hero hero=setUpNewHero();
        Hero otherHero=new Hero("Trojan",23,"fuelling","innactive during raining");
        hero.clearAll();
        assertEquals(0,Hero.getHero().size());

    }
    @BeforeEach
    void setUp() {
    }
    @Test
    public void testIfInstanceIsUpdated() throws Exception {
        Hero hero=setUpNewHero();
        int formerID=hero.getId();
        boolean formerOccupied=hero.isOccupied();
        hero.updatehero(true);
        assertEquals(formerID,hero.getId());
        assertNotEquals(formerOccupied,hero.isOccupied());
    }
    
}