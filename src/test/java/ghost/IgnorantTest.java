package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IgnorantTest {
    @Test
    public void constructor() {
        assertNotNull(new Ignorant(30, 20, null, null, 1));
    }

    @Test
    public void testUpdateTarget(){
        Ignorant a = new Ignorant(30, 20, null, null, 1);
        a.updateTarget(true, null, null);

        Player player = new Player(20, 30, null, null, null, null, null, 1, null);
        a.updateTarget(true, player, null);

        Chaser chaser = new Chaser(50, 60, null, null, 1);
        a.updateTarget(true, player, chaser);
        assertEquals(0, a.getTarget()[0], "Expected 0");
        assertEquals(35 * 16, a.getTarget()[1], "Expected 35 * 16");

        a.updateTarget(false, player, chaser);
        assertEquals(0, a.getTarget()[0], "Expected 0");
        assertEquals(35 * 16, a.getTarget()[1], "Expected 35 * 16");

        Player player2 = new Player(200, 200, null, null, null, null, null, 1, null);
        a.updateTarget(false, player2, chaser);
        assertEquals(200, a.getTarget()[0], "Expected 200");
        assertEquals(200, a.getTarget()[1], "Expected 200");

        Player player3 = new Player(9999, 9999, null, null, null, null, null, 1, null);
        a.updateTarget(false, player3, chaser);
        assertEquals(448, a.getTarget()[0], "Expected 448");
        assertEquals(560, a.getTarget()[1], "Expected 560");
    }
    
}