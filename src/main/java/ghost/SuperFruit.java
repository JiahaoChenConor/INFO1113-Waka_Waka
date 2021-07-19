package ghost;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class is super fruit which can frighten the ghosts.
 */
public class SuperFruit extends Fruit {
    /**
     * Constructs a new SuperFruit object. The instance stores the parameters as its own properties.
     * @param superFruit the image of super fruit
     * @param x the x-axis position
     * @param y the y-axis position
     */
    public SuperFruit(PImage superFruit, int x, int y){
        super(superFruit, x, y);
    }

    @Override
    public boolean isSuperFruit(){
        return true;
    }

    @Override
    public void draw(PApplet app){
        if (app == null){
            return;
        }
        if (isExist()){
            if (this.fruit != null){
                app.image(this.fruit, this.x, this.y);
            }
        }
    }
    
}