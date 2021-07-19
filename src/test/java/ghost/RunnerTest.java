package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;

public class RunnerTest {
    
    @Test
    public void testConstructor(){
        Run runner = new Run();
        assertNotNull(runner);
    }

    @Test
    public void testEnd(){
        Run runner = new Run();
        assertEquals(false, runner.getEnd(), "Expected false");
    }

    @Test
    public void testSetEnd(){
        Run runner = new Run();
        runner.setEnd(true);
        assertEquals(true, runner.getEnd(), "Expected true");
    }

    @Test
    public void testGetMap(){
        Run runner = new Run();
        assertNull(runner.getMap());
    }

    @Test
    public void testGetPlayer(){
        Run runner = new Run();
        assertNull(runner.getPlayer());
    }

    @Test
    public void testGetLives(){
        Run runner = new Run();
        assertEquals(0, runner.getLives(), "Expected 0");
    }

    @Test
    public void testGetFrightenedLen(){
        Run runner = new Run();
        assertEquals(0, runner.getFrightenedLen(), "Expected 0");
    }

    @Test
    public void testGetFruits(){
        Run runner = new Run();
        assertEquals(0, runner.getFruits().size(), "Expected 0");
    }

    @Test
    public void testGetSuperFruits(){
        Run runner = new Run();
        assertEquals(0, runner.getSuperFruits().size(), "Expected 0");
    }

    @Test
    public void testGetSodaCans(){
        Run runner = new Run();
        assertEquals(0, runner.getSodaCans().size(), "Expected 0");
    }

    @Test
    public void testGetAllFruits(){
        Run runner = new Run();
        assertEquals(0, runner.getAllFruits().size(), "Expected 0");
    }

    @Test
    public void testGetAllGhosts(){
        Run runner = new Run();
        assertEquals(0, runner.getAllGhosts().size(), "Expected 0");
    }

    @Test
    public void testGetReset(){
        Run runner = new Run();
        assertEquals(false, runner.getReset(), "Expected false");
    }

    @Test
    public void testSetReset(){
        Run runner = new Run();
        
        runner.setReset(true);
        assertEquals(true, runner.getReset(), "Expected true");

        runner.setReset(false);
        assertEquals(false, runner.getReset(), "Expected false");
        
    }

    @Test
    public void testGetModeLengths(){
        Run runner = new Run();

        assertEquals(0, runner.getModeLengths().size(), "Expected 0");
    }

    @Test
    public void testGetChasers(){
        Run runner = new Run();

        assertEquals(0, runner.getChasers().size(), "Expected 0");
    }

    @Test
    public void testReadJson(){
        Run runner = new Run();
        runner.readJson();
    }

    @Test
    public void testSetObjects(){
        Run runner = new Run();
        runner.setObjects(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        assertEquals(303, runner.getAllFruits().size(), "Expected 303");
        assertEquals(8, runner.getSuperFruits().size(), "Expected 8");
        assertEquals(4, runner.getAllGhosts().size(), "Expected 4");
        assertEquals(3, runner.getSodaCans().size(), "Expected 3");
        
    }

    @Test
    public void testReset(){
        Run runner = new Run();
        runner.setReset(true);
        runner.reset();

        
        runner.setObjects(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        runner.setReset(true);
        runner.reset();
        assertEquals(303, runner.getAllFruits().size(), "Expected 303");
        assertEquals(8, runner.getSuperFruits().size(), "Expected 8");
        assertEquals(4, runner.getAllGhosts().size(), "Expected 4");
        assertEquals(3, runner.getSodaCans().size(), "Expected 3");
        
    }

    @Test
    public void testIsEnd(){
        Run runner = new Run();
        assertEquals(false, runner.isEnd(), "Expected false");
    }
    
    @Test
    public void testGetEndTime(){
        Run runner = new Run();
        assertEquals(Integer.MAX_VALUE, runner.getEndTime(), "Expected Integer.MAX_VALUE");

    }

    @Test
    public void testSetEndTime(){
        Run runner = new Run();
        runner.setEndTime(1);
        assertEquals(1, runner.getEndTime(), "Expected 1");

    }

    @Test
    public void testSetLives(){
        Run runner = new Run();
        runner.setLives(2);
        assertEquals(2, runner.getLives(), "Expected 2");
    }

    @Test
    public void TestWinOrOver(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        
        Run runner = new Run();
        runner.winOrOver(app, 0);
        runner.setEndTime(0);
        runner.winOrOver(app, 600);
        
        runner.setLives(-1);
        runner.winOrOver(app, 400);
        runner.setEnd(false);
        runner.winOrOver(app, 400);


        runner.setObjects(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        runner.winOrOver(app, 0);

        runner.setEnd(false);
    }
}