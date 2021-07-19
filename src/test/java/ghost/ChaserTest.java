package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


public class ChaserTest {

    @Test
    public void constructor() {
        assertNotNull(new Chaser(30, 20, null, null, 1));
    }

    @Test
    public void testGetX() {
        Chaser g = new Chaser(20, 30, null, null, 1);
        assertEquals(20 ,g.getX(), "Expected 20");
    }

    @Test
    public void testGetY() {
        Chaser g = new Chaser(20, 30, null, null, 1);
        assertEquals(30, g.getY(), "Expected 30");
    }

    @Test
    public void testGetTarget() {
        Chaser g = new Chaser(20, 30, null, null, 1);
        assertEquals(0, g.getTarget()[0], "Expected 0");
        assertEquals(0, g.getTarget()[1], "Expected 0");
    }

    @Test
    public void testMove() {
        Chaser g = new Chaser(20, 30, null, null, 1);
        g.setDirection("Up");
        g.move();
        assertEquals(29, g.getY(), "Expected 29");

        g.setDirection("Down");
        g.move();
        assertEquals(30, g.getY(), "Expected 30");

        g.setDirection("Left");
        g.move();
        assertEquals(19, g.getX(), "Expected 19");

        g.setDirection("Right");
        g.move();
        assertEquals(20, g.getX(), "Expected 30");
    }

    @Test
    public void testGetFrightened(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        assertEquals(false, g.getFrightened(), "Expected false");
    }

    @Test
    public void testSetFrightened(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        g.setFrightened(true);
        assertEquals(true, g.getFrightened(), "Expected true");
    }

    // how to test draw, drawLine
    @Test
    public void testDraw(){

        Chaser g = new Chaser(20, 30, null, null, 1);
        g.draw(null);
        
        g.setFrightened(false);
        g.draw(null);

        g.die();
        g.draw(null);
        
        g.setAlive(true);
        g.setFrightened(true);
        g.draw(null);

        g.die();
        g.draw(null);
    }

    @Test
    public void testDrawLine(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        g.drawLine(null);
        g.die();
        g.draw(null);
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        Chaser g2 = new Chaser(20, 30, null, null, 1);
        g2.drawLine(app);
        g2.die();
        g2.drawLine(app);

        Chaser g3 = new Chaser(20, 30, null, null, 1);
        g3.setFrightened(true);
        g3.setDirection("Up");
        g3.drawLine(app);

        g3.setDirection("Left");
        g3.drawLine(app);

        g3.setDirection("Right");
        g3.drawLine(app);

        g3.setDirection("Down");
        g3.drawLine(app);

        
    }

    @Test
    public void testIsAlive(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        assertEquals(true, g.getIsAlive(), "Expected true");
    }

    @Test
    public void testDie(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        g.die();
        assertEquals(false, g.getIsAlive(), "Expected false");
    }

    @Test void testSetAlive(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        g.setAlive(false);;
        assertEquals(false, g.getIsAlive(), "Expected false");

        g.setAlive(true);;
        assertEquals(true, g.getIsAlive(), "Expected true");
    }

    @Test
    public void testGetInvisible(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        assertEquals(false, g.getIsInvisible(), "Expected false");
    }

    @Test
    public void testSetInvisible(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        g.setInvisible(true);
        assertEquals(true, g.getIsInvisible(), "Expected true");
    }

    @Test
    public void testSetPosition(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        g.setPosition(16, 32);
        assertEquals(16, g.getX(), "Expected 16");
        assertEquals(32, g.getY(), "Expected 32");
    }

    @Test
    public void testReset(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        g.setPosition(16, 32);
        g.reset();
        assertEquals(20, g.getX(), "Expected 16");
        assertEquals(30, g.getY(), "Expected 32");
    }

    @Test
    public void testCalculateDistance(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        double distance = g.calculateDistance(10, 10, 20, 20);
        assertEquals(Math.sqrt(200), distance, "Expected sqrt(200)");
    }

    @Test 
    public void testSetMode(){
        Chaser g = new Chaser(16 * 8, 16 * 9, null, null, 1);
        g.setMode(true, null, null, null);

        Player player = new Player(16 * 5, 16 * 5, null, null, null, null, null, 1, null);
        MyMap map = new MyMap("map.txt", null);

        g.setMode(true, player, map, null);
        assertEquals(0, g.target[0], "Expected 0");
        assertEquals(0, g.target[1], "Expected 0");

        g.setMode(false, player, map, null);
        assertEquals(80, g.target[0], "Expected 80");
        assertEquals(80, g.target[1], "Expected 80");

        g.setPosition(15, 16);
        g.setMode(true, player, map, null);
        g.setMode(false, player, map, null);

        g.setPosition(16, 15);
        g.setMode(true, player, map, null);
        g.setMode(false, player, map, null);

        g.setPosition(15, 15);
        g.setMode(true, player, map, null);
        g.setMode(false, player, map, null);
        
    }

    @Test 
    public void testUpdateTarget(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        g.updateTarget(true, null, null);
        assertEquals(0, g.getTarget()[0], "Expected 0");
        assertEquals(0, g.getTarget()[1], "Expected 0");

        Player player = new Player(50, 60, null, null, null, null, null, 1, null);
        g.updateTarget(false, player, null);
        assertEquals(50, g.getTarget()[0], "Expected 50");
        assertEquals(60, g.getTarget()[1], "Expected 60");

        g.updateTarget(true, player, null);
        assertEquals(0, g.getTarget()[0], "Expected 0");
        assertEquals(0, g.getTarget()[1], "Expected 0");

    }

    @Test
    public void testCheckTarget(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        Player player = new Player(999999, 999999, null, null, null, null, null, 1, null);
        g.updateTarget(false, player, null);
        assertEquals(28 * 16, g.getTarget()[0], "Expected 28 * 16");
        assertEquals(35 * 16, g.getTarget()[1], "Expected 35 * 16");
    }

    @Test
    public void testCanGo(){
        Chaser g = new Chaser(16 * 9, 16 * 4, null, null, 1);
        Chaser g2 = new Chaser(16 * 1, 16 * 6, null, null, 1);
        assertEquals(false, g.canGo(null, null));
        MyMap map = new MyMap("map.txt", null);
        map.transformFileTo2DList("map.txt");
        assertEquals(false, g.canGo("Up", map), "Excepted false");
        assertEquals(true, g.canGo("Left", map), "Excepted true");
        assertEquals(true, g.canGo("Right", map), "Excepted true");
        assertEquals(false, g.canGo("Down", map), "Excepted false");
        
        assertEquals(true, g2.canGo("Up", map), "Excepted true");
        assertEquals(false, g2.canGo("Left", map), "Excepted false");
        assertEquals(false, g2.canGo("Right", map), "Excepted false");
        assertEquals(true, g2.canGo("Down", map), "Excepted true");

        assertEquals(false, g2.canGo("ddddd", map), "Excepted false");

    }
    
    @Test
    public void testConstructPriorities(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        List<String> priorities = new ArrayList<>();
        priorities.add("Up");
        priorities.add("Left");
        priorities.add("Right");
        priorities.add("Down");
        List<String> actual = g.constructPriorities();
        for (int i = 0; i < actual.size(); i++){
            assertEquals(priorities.get(i), actual.get(i), "Expected" + priorities.get(i)); 
        }

        g.setFrightened(true);
        g.constructPriorities();

        Chaser g2 = new Chaser(30, 30, null, null, 1);
        List<String> actual2 = g2.constructPriorities();
        List<String> priorities2 = new ArrayList<>();
        priorities2.add("Left");
        priorities2.add("Up");
        priorities2.add("Right");
        priorities2.add("Down");
        for (int i = 0; i < actual2.size(); i++){
            assertEquals(priorities2.get(i), actual2.get(i), "Expected" + priorities.get(i)); 
        }

        Chaser g3 = new Chaser(16 * 4, 16 * 4, null, null, 1);
        Player player = new Player(16 * 4, 16 * 4, null, null, null, null, null, 1, null);
        g3.setMode(false, player, new MyMap("map.txt", null), null);
        List<String> actual3 = g3.constructPriorities();
        List<String> priorities3 = new ArrayList<>();
        priorities3.add("Right");
        priorities3.add("Up");
        priorities3.add("Down");
        priorities3.add("Left");

        for (int i = 0; i < actual3.size(); i++){
            assertEquals(priorities3.get(i), actual3.get(i), "Expected" + priorities.get(i)); 
        }



    }

    @Test
    public void testSetDirection(){
        Chaser g = new Chaser(20, 30, null, null, 1);
        g.setDirection(null);
        // need more
    }

    // testSetPriorityDirection
    @Test
    public void testSetPriorityDirection(){
        Chaser g = new Chaser(16 * 6, 16 * 5, null, null, 1);
        g.setPriorityDirection(null, null);

        MyMap map = new MyMap("map.txt", null);
        List<String> priorities = new ArrayList<>();
        priorities.add("Up");
        priorities.add("Left");
        priorities.add("Right");
        priorities.add("Down");

        map.transformFileTo2DList("map.txt");
        g.setPriorityDirection(priorities, map);
        g.setPriorityDirection(null, map);
        g.setPriorityDirection(priorities, null);

        
    }

    @Test
    public void testPrefixSum(){
        assertNull(Chaser.prefixSum(null));

        List<Integer> ls = new ArrayList<>();
        ls.add(3);
        ls.add(4);
        ls.add(2);
        int[] actual = Chaser.prefixSum(ls);
        assertEquals(0, actual[0], "Expected 0");
        assertEquals(3, actual[1], "Expected 3");
        assertEquals(7, actual[2], "Expected 7");
        assertEquals(9, actual[3], "Expected 9");
    }

    @Test
    public void testDrawGhosts(){
        
        assertEquals(false, Chaser.drawGhosts(null, null, 1, 1, null, null, null, null), "Excepted false");
        
        App app = new App();
        List<Integer> modelLengths = new ArrayList<>();
        modelLengths.add(5);
        modelLengths.add(5);
        List<Chaser> allGhosts = new ArrayList<>();
        Player player = new Player(16 * 6, 16 * 6, null, null, null, null, null, 1, null);
        MyMap map = new MyMap("map.txt", null);
        Chaser chaser = new Chaser(16 * 6, 16 * 6, null, null, 1);
        allGhosts.add(chaser);

        assertEquals(false, Chaser.drawGhosts(null, modelLengths, 60, 2, allGhosts, allGhosts, player, map), "Excepted false");
        assertEquals(false, Chaser.drawGhosts(app, null, 60, 2, allGhosts, allGhosts, player, map), "Excepted false");
        assertEquals(false, Chaser.drawGhosts(app, modelLengths, 60, 2, null, allGhosts, player, map), "Excepted false");
        assertEquals(false, Chaser.drawGhosts(app, modelLengths, 60, 2, allGhosts, null, player, map), "Excepted false");
        assertEquals(false, Chaser.drawGhosts(app, modelLengths, 60, 2, allGhosts, allGhosts, null, map), "Excepted false");
        assertEquals(false, Chaser.drawGhosts(app, modelLengths, 60, 2, allGhosts, allGhosts, player, null), "Excepted false");
        
        boolean res = Chaser.drawGhosts(app, modelLengths, 60, 2, allGhosts, allGhosts, player, map);
        boolean res2 = Chaser.drawGhosts(app, modelLengths, 60, Integer.MIN_VALUE, allGhosts, allGhosts, player, map);
        assertEquals(true, res, "Expected true");
        assertEquals(true, res2, "Expected true");

        chaser.setFrightened(true);
        boolean res5 = Chaser.drawGhosts(app, modelLengths, 60, 2, allGhosts, allGhosts, player, map);
        assertEquals(false, res5, "Expected false");

        List<Chaser> allGhosts2 = new ArrayList<>();
        Chaser chaser2 = new Chaser(16 * 4, 16 * 4, null, null, 1);
        allGhosts2.add(chaser2);

        chaser2.setFrightened(true);
        chaser2.setInvisible(true);
        boolean res3 = Chaser.drawGhosts(app, modelLengths, 60, 2, allGhosts2, new ArrayList<Chaser>(), player, map);
        assertEquals(false, res3, "Expected false");

        boolean res4 = Chaser.drawGhosts(app, modelLengths, 360, 2, allGhosts2, new ArrayList<Chaser>(), player, map);
        assertEquals(false, res4, "Expected false");

        boolean res6 = Chaser.drawGhosts(app, modelLengths, 60, 2, allGhosts2, allGhosts2, player, map);
        assertEquals(false, res6, "Expected false");

        boolean res7 = Chaser.drawGhosts(app, modelLengths, 360, 2, allGhosts2, allGhosts2, player, map);
        assertEquals(false, res7, "Expected false");


    };
}