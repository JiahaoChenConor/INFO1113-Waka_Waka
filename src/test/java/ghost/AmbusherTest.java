package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmbusherTest {
    @Test
    public void constructor() {
        assertNotNull(new Ambusher(30, 20, null, null, 1));
    }

    @Test
    public void testUpdateTarget(){
        Ambusher a = new Ambusher(30, 20, null, null, 1);
        a.updateTarget(true, null, null);

        Player player = new Player(20, 30, null, null, null, null, null, 1, null);
        a.updateTarget(true, player, null);

        Chaser chaser = new Chaser(50, 60, null, null, 1);
        a.updateTarget(true, player, chaser);
        assertEquals(28 * 16, a.getTarget()[0], "Expected 28 * 16");
        assertEquals(0, a.getTarget()[1], "Expected 0");

        player.up();
        a.updateTarget(false, player, chaser);
        // System.out.println(a.getTarget()[0]);
        // System.out.println(a.getTarget()[1]);
        assertEquals(20, a.getTarget()[0], "Expected 20");
        assertEquals(0, a.getTarget()[1], "Expected 0");


        player.left();
        a.updateTarget(false, player, chaser);
        assertEquals(0, a.getTarget()[0], "Expected 0");
        assertEquals(30, a.getTarget()[1], "Expected 30");

        player.right();
        a.updateTarget(false, player, chaser);
        assertEquals(84, a.getTarget()[0], "Expected 84");
        assertEquals(30, a.getTarget()[1], "Expected 30");

        player.down();
        a.updateTarget(false, player, chaser);
        assertEquals(20, a.getTarget()[0], "Expected 20");
        assertEquals(94, a.getTarget()[1], "Expected 94");
    }

}