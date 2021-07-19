package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SuperFruitTest {
    @Test
    public void constructor() {
        assertNotNull(new SuperFruit(null, 1, 1));
    }

    @Test
    public void testIsSuperFruit(){
        SuperFruit sf = new SuperFruit(null, 1, 1);
        assertEquals(true, sf.isSuperFruit(), "true");
    }

    @Test
    public void testDraw(){
        SuperFruit sf = new SuperFruit(null, 1, 1);
        sf.draw(null);

        App app = new App();
        sf.draw(app);

        sf.delete();
        sf.draw(app);

    }
}