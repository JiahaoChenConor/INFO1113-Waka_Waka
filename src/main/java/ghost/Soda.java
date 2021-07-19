package ghost;

import processing.core.PImage;

/**
 * This is a kind of food which can make all ghosts invisible.
 */
public class Soda extends Fruit {

    /**
     * Constructs a new Soda object. The instance stores the parameters as its own properties.
     * @param fruit the image of soda
     * @param x the x-axis position
     * @param y the y-axis position
     */
    public Soda(PImage fruit, int x, int y) {
        super(fruit, x, y);
    }

    @Override
    public boolean isSoda(){
        return true;
    }
    
}