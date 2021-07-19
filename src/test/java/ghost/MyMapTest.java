package ghost;
import org.junit.jupiter.api.Test;

import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;

public class MyMapTest extends PApplet{
    @Test
    public void constructor() {
        assertNotNull(new MyMap(null, null));
    }

    @Test
    public void testLoadImage(){
        MyMap m = new MyMap(null, null);
        m.loadImage(null);
    }

    @Test
    public void testTransformFileTo2DList(){
        MyMap m = new MyMap(null, null);
        m.transformFileTo2DList(null);

        MyMap m2 = new MyMap("map.txt", null);
        m2.transformFileTo2DList("map.txt");
        m2.transformFileTo2DList("notExist");
    }

    @Test
    public void testDrawMap(){
        MyMap m = new MyMap(null, null);
        m.drawMap(null, null);

        m = new MyMap("map.txt", null);
        App app = new App();

        m.transformFileTo2DList("map.txt");
        m.drawMap(app, "map.txt");

        m.drawMap(null, "map.txt");
        m.drawMap(app, null);

        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        m.drawMap(app, "map.txt");
    }

    @Test
    public void testGetAllPositions(){
        MyMap m = new MyMap(null, null);
        m.getAllPositions(null, null, null);

        MyMap m1 = new MyMap("map.txt", null);
        App app = new App();
        Player player = new Player(10, 20, g, g, g, g, g, 1, app);
        m1.getAllPositions(app, "map.txt", player);

        m1.getAllPositions(null, "map.txt", player);
        m1.getAllPositions(app, null, player);
        m1.getAllPositions(app, "map.txt", null);
    }

    @Test
    public void testGetChasersList(){
        MyMap m = new MyMap(null, null);
        assertEquals(0, m.getChasersList().size(), "Expected 0");
    }

    @Test
    public void testGetAmbushersList(){
        MyMap m = new MyMap(null, null);
        assertEquals(0, m.getAmbushersList().size(), "Expected 0");
    }

    @Test
    public void testGetIgnorantList(){
        MyMap m = new MyMap(null, null);
        assertEquals(0, m.getIgnorantList().size(), "Expected 0");
    }

    @Test
    public void testGetWhimsList(){
        MyMap m = new MyMap(null, null);
        assertEquals(0, m.getWhimsList().size(), "Expected 0");
    }

    @Test
    public void testGetFruitsList(){
        MyMap m = new MyMap(null, null);
        assertEquals(0, m.getFruitsList().size(), "Expected 0");
    }

    @Test
    public void testGetSodaList(){
        MyMap m = new MyMap(null, null);
        assertEquals(0, m.getSodaList().size(), "Expected 0");
    }
    @Test
    public void testGetSuperFruitsList(){
        MyMap m = new MyMap(null, null);
        assertEquals(0, m.getSuperFruitsList().size(), "Expected 0");
    }

    @Test
    public void testIsWall(){
        MyMap m = new MyMap(null, null);
        assertEquals(true, m.isWall('1'), "Expected true");
        assertEquals(true, m.isWall('2'), "Expected true");
        assertEquals(true, m.isWall('3'), "Expected true");
        assertEquals(true, m.isWall('4'), "Expected true");
        assertEquals(true, m.isWall('5'), "Expected true");
        assertEquals(true, m.isWall('6'), "Expected true");

        assertEquals(false, m.isWall('7'), "Expected false");
    }

    @Test
    public void testJudgeWall(){
        MyMap m = new MyMap(null, null);
        assertNull(m.judgeWall(15, 15));
        assertNull(m.judgeWall(0, 0));

        MyMap m2 = new MyMap("map.txt", null);
        boolean[] actual = m2.judgeWall(16 * 7, 16 * 5);
        boolean[] expected = new boolean[]{false, false, true, true};
        for (int i = 0; i < actual.length; i++){
            assertEquals(expected[i], actual[i], "Expected" + expected[i]);
        }

        MyMap m3 = new MyMap("map.txt", null);
        assertNull(m3.judgeWall(15, 15));
        assertNull(m3.judgeWall(15, 16));
        assertNull(m3.judgeWall(16, 15));
        


    }


}