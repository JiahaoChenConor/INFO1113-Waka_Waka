package ghost;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WhimTest {
    @Test
    public void testUpdateTarget(){
        Whim w = new Whim(10, 20, null, null, 1);
        Player player = new Player(20, 30, null, null, null, null, null, 1, null);
        Chaser chaser = new Chaser(50, 60, null, null, 1);
        w.updateTarget(true, null, null);
        w.updateTarget(false, null, null);

        w.updateTarget(true, player, null);
        w.updateTarget(false, player, null);

        w.updateTarget(true, null, chaser);
        w.updateTarget(false, null, chaser);

        w.updateTarget(true, player, chaser);
        assertEquals(28 * 16, w.getTarget()[0], "Expected 28 * 16");
        assertEquals(35 * 16, w.getTarget()[1], "Expected 35 * 16");
        
        // direction is 0
        player.up();
        w.updateTarget(false, player, chaser);
        assertEquals(0, w.getTarget()[0], "Expected 0");
        assertEquals(0, w.getTarget()[1], "Expected 0");


        player.left();
        w.updateTarget(false, player, chaser);
        assertEquals(0, w.getTarget()[0], "Expected 0");
        assertEquals(0, w.getTarget()[1], "Expected 0");

        player.right();
        w.updateTarget(false, player, chaser);
        assertEquals(54, w.getTarget()[0], "Expected 54");
        assertEquals(0, w.getTarget()[1], "Expected 0");

        player.down();
        w.updateTarget(false, player, chaser);
        assertEquals(0, w.getTarget()[0], "Expected 0");
        assertEquals(64, w.getTarget()[1], "Expected 64");
    }
}