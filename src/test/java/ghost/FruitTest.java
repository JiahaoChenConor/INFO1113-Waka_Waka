package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class FruitTest {
    @Test
    public void constructor() {
        assertNotNull(new Fruit(null, 20, 30));
    }

    @Test
    public void testGetX() {
        Fruit f = new Fruit(null, 20, 30);
        assertEquals(20 ,f.getX(), "Expected 20");
    }

    @Test
    public void testGetY() {
        Fruit f = new Fruit(null, 20, 30);
        assertEquals(30 ,f.getY(), "Expected 30");
    }

    @Test
    public void testAddFruit(){
        Fruit.addFruit(null, 20, 30, null);

        List<Fruit> ls = new ArrayList<>();
        assertEquals(0, ls.size(), "Expected 0");
        Fruit.addFruit(null, 20, 30, ls);
        assertEquals(1, ls.size(), "Expected 1");
    }

    @Test
    public void testIsExist(){
        Fruit f = new Fruit(null, 20, 30);
        assertEquals(true, f.isExist(), "Expected true");
    }

    @Test
    public void testDelete(){
        Fruit f = new Fruit(null, 20, 30);
        f.delete();
        assertEquals(false, f.isExist(), "Expected false");
    }

    @Test
    public void testDraw(){
        Fruit f = new Fruit(null, 20, 30);
        f.draw(null);

        App app = new App();
        f.draw(app);

        f.delete();
        f.draw(app);
    }

    @Test
    public void testIsSuperFruit(){
        Fruit f = new Fruit(null, 20, 30);
        assertEquals(false, f.isSuperFruit(), "Expected false");
    }

    @Test
    public void testIsSoda(){
        Fruit f = new Fruit(null, 20, 30);
        assertEquals(false, f.isSoda(), "Expected false");
    }

    @Test
    public void testDrawFruits(){
        Fruit.drawFruits(null, null, null, null, 1);

        Player player = new Player(0, 0, null, null, null, null, null, 0, null);
        Fruit superFruit = new SuperFruit(null, 0, 0);
        Soda soda = new Soda(null, 0, 0);

        List<Chaser> allGhosts = new ArrayList<>();
        allGhosts.add(new Chaser(20, 30, null, null, 0));
        List<Fruit> allFruits = new ArrayList<>();
        allFruits.add(superFruit);

        App app = new App();
        Fruit.drawFruits(app, player, allFruits, allGhosts, 60);

        List<Fruit> allFruits2 = new ArrayList<>();
        allFruits.add(soda);
        Fruit.drawFruits(app, player, allFruits2, allGhosts, 60);

    }
}