package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SodaTest {
    @Test
    public void constructor() {
        assertNotNull(new Soda(null, 1, 1));
    }

    @Test
    public void testIsSoda(){
        Soda s = new Soda(null, 1, 1);
        assertEquals(s.isSoda(), true);
    }
}