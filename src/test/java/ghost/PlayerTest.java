package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class PlayerTest {
    @Test
    public void testSetX(){
        Player p = new Player(10, 10, null, null, null, null, null, 1, null);
        p.setX(16);
        assertEquals(16, p.getX(), "Expected 16");
    }

    @Test
    public void testSetY(){
        Player p = new Player(10, 10, null, null, null, null, null, 1, null);
        p.setY(16);
        assertEquals(16, p.getY(), "Expected 16");
    }

    @Test
    public void testGetUnavailableDir(){
        Player p = new Player(10, 10, null, null, null, null, null, 1, null);
        assertEquals(-1, p.getUnavailableDir(), "Expected -1");
    }

    @Test
    public void testSetUnavailableDir(){
        Player p = new Player(10, 10, null, null, null, null, null, 1, null);
        p.setUnavailableDir(1);
        assertEquals(1, p.getUnavailableDir(), "Expected 1");
    }

    @Test
    public void constructor() {
        App a = new App();
        Player p = new Player(10, 10, null, null, null, null, null, 1, a);
        assertNotNull(p);
        assertNull(p.getPlayerClosed());
        assertNull(p.getPlayerLeft());
        assertNull(p.getPlayerRight());
        assertNull(p.getPlayerUp());
        assertNull(p.getPlayerDown());
    }

    @Test
    public void testGetX() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        assertEquals(10, p.getX(), "Expected 10");
    }

    @Test
    public void testGetY() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        assertEquals(20, p.getY(), "Expected 20");
    }

    @Test
    public void testDirectionNum() {
        int up = Player.directionNum(38);
        assertEquals(0, up, "Expected 0");

        int left = Player.directionNum(37);
        assertEquals(1, left, "Expected 1");

        int right = Player.directionNum(39);
        assertEquals(2, right, "Expected 2");

        int down = Player.directionNum(40);
        assertEquals(3, down, "Expected 3");

        int unknown = Player.directionNum(50);
        assertEquals(-1, unknown, "Expected -1");

    }

    @Test
    public void testGetStartFrightenedTime() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        assertEquals(Integer.MAX_VALUE, p.getStartFrightenedTime(), "Expected Integer.MAX_VALUE");
    }

    @Test
    public void testSetStartFrightenedTime() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.setStartFrightenedTime(12);
        assertEquals(12, p.getStartFrightenedTime(), "Expected 12");
    }

    @Test
    public void testGetIsFrightened() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        assertEquals(false, p.getIsFrightened(), "Expected false");
    }

    @Test
    public void testSetIsFrightened() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.setFrightened(true);
        assertEquals(true, p.getIsFrightened(), "Expected true");
    }

    @Test
    public void testSetPosition() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.setPosition(30, 30);
        assertEquals(30, p.getX(), "Expected 30");
        assertEquals(30, p.getY(), "Expected 30");
    }

    @Test
    public void testReset() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.setPosition(10, 20);
        p.setPosition(30, 30);
        p.reset();

        assertEquals(10, p.getX(), "Expected 10");
        assertEquals(20, p.getY(), "Expected 20");
    }

    @Test
    public void testGetFrightenedTimes() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        assertEquals(0, p.getFrightenedTimes(), "Expected 0");
        p.setFrightened(true);
        assertEquals(1, p.getFrightenedTimes(), "Expected 1");
    }

    @Test
    public void testGetDebug(){
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        assertEquals(false, p.getDebug(), "Expected false");
        
    }
    @Test
    public void testSetDebug(){
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.setDebug(true);
        assertEquals(true, p.getDebug(), "Expected true");

        
    }

    @Test
    public void testDebugMode() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.debugMode(null, null);

        List<Chaser> chasers = new ArrayList<>();
        chasers.add(new Chaser(0, 0, null, null, 1));
        p.setDebug(true);
        p.debugMode(null, chasers);
        
    }

    @Test
    public void testDrawPlayer() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.drawPlayer(null, 1, null);

        List<Chaser> chasers = new ArrayList<>();
        chasers.add(new Chaser(0, 0, null, null, 1));
        Player p2 = new Player(10, 20, null, null, null, null, null, 1, null);
        p2.drawPlayer(null, 1, chasers);

        Player p3 = new Player(10, 20, null, null, null, null, null, 1, null);
        p3.drawPlayer(null, 9, chasers);

        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        p3.setFrightened(true);
        p3.drawPlayer(null, 9, chasers);
    }

    @Test
    public void testDrawLives() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.drawLives(null, 3);

        App app = new App();
        p.drawLives(app, 3);
    }

 

    @Test
    public void testEat() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        assertNull(p.eat(null, null));

        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit(null, 10, 20));
        List<Chaser> chasers = new ArrayList<>();
        chasers.add(new Chaser(0, 0, null, null, 1));
        assertEquals("fruit", p.eat(fruits, chasers), "Expected String fruit");

        List<Fruit> superFruits = new ArrayList<>();
        superFruits.add(new SuperFruit(null, 10, 20));
        assertEquals("superFruit", p.eat(superFruits, chasers), "Expected String superFruit");

        List<Fruit> sodaCans = new ArrayList<>();
        sodaCans.add(new Soda(null, 10, 20));
        assertEquals("soda", p.eat(sodaCans, chasers), "Expected String soda");

    }

    // receive keyboard
    @Test
    public void testReceiveKeyBoard() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.receiveKeyboard(1, null);

        App app = new App();
        p.receiveKeyboard(0, app);
        assertEquals(p.getDirection(), 0, "Expected 0");

        p.receiveKeyboard(1, app);
        assertEquals(p.getDirection(), 1, "Expected 0");

        p.receiveKeyboard(2, app);
        assertEquals(p.getDirection(), 2, "Expected 0");

        p.receiveKeyboard(3, app);
        assertEquals(p.getDirection(), 3, "Expected 0");

        p.receiveKeyboard(4, app);
        assertEquals(p.getDirection(), 3, "Expected 0");
    }

    @Test
    public void testUp() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.up();
        assertEquals(0, p.getDirection(), "Expected 0");
    }

    @Test
    public void testLeft() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.left();
        assertEquals(1, p.getDirection(), "Expected 1");
    }

    @Test
    public void testRight() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.right();
        assertEquals(2, p.getDirection(), "Expected 2");
    }

    @Test
    public void testDown() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.down();
        assertEquals(3, p.getDirection(), "Expected 3");
    }

    @Test
    public void testMove() {
        Player p = new Player(10, 20, null, null, null, null, null, 1, null);
        p.left();
        p.move();
        assertEquals(9, p.getX(), "Expected 9");
        assertEquals(20, p.getY(), "Expected 20");
    }

    @Test
    public void testPlayerMove() {
        Player player = new Player(16 * 1, 16 * 4, null, null, null, null, null, 1, null);
        App app = new App();

        MyMap map = new MyMap("map.txt", null);

        player.playerMove(app, null, true, 37, null, 1);
        player.playerMove(null, null, true, 37, null, 1);
        player.playerMove(null, player, true, 37, null, 1);
        player.playerMove(null, null, true, 37, map, 1);

        assertEquals(0, player.getDirection(), "Expected 0");

        player.playerMove(app, player, false, 37, map, 1);

        player.playerMove(app, player, true, 37, map, 1);

        assertEquals(0, player.getDirection(), "Expected 0");
        player.playerMove(app, player, true, 38, map, 1);

        assertEquals(0, player.getDirection(), "Expected 0");
        player.playerMove(app, player, true, 39, map, 1);

        assertEquals(2, player.getDirection(), "Expected 2");
        player.playerMove(app, player, true, 40, map, 1);

        assertEquals(2, player.getDirection(), "Expected 2");

        player.playerMove(app, player, true, 32, map, 1);

        assertEquals(2, player.getDirection(), "Expected 2");

        player.playerMove(app, player, true, 1, map, 1);

        assertEquals(2, player.getDirection(), "Expected 2");

        player.setStartFrightenedTime(0);
        player.playerMove(app, player, true, 0, map, 50);
        assertEquals(2, player.getDirection(), "Expected 2");

        player.setUnavailableDir(1);
        player.setX(3 * 16);
        player.setY(8 * 16);
        player.playerMove(app, player, true, 37, map, 1);

        assertEquals(1, player.getDirection(), "Expected 1");
        player.playerMove(app, player, true, 38, map, 1);

        assertEquals(1, player.getDirection(), "Expected 1");
        player.playerMove(app, player, true, 39, map, 1);

        assertEquals(1, player.getDirection(), "Expected 1");
        player.playerMove(app, player, true, 40, map, 1);

        assertEquals(1, player.getDirection(), "Expected 1");
        player.playerMove(app, player, true, 1, map, 1);

        assertEquals(1, player.getDirection(), "Expected 1");

    }
    
}