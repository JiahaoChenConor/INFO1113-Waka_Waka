package ghost;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;


/**
 * This is a kind of fruit class that will be eaten by ghosts.
 */
public class Fruit {
    protected int x;
    protected int y;
    protected PImage fruit;
    protected boolean beEat = false;

    /**
     * Constructs a new Fruit object. The instance stores the parameters as its own properties.
     * @param fruit the image of fruit
     * @param x x-axis position
     * @param y y-axis position
     */
    public Fruit(PImage fruit, int x, int y){
        this.fruit = fruit;
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x-axis position of the fruit.
     * @return The x-axis position of the fruit.
     */
    public int getX(){
        return this.x;
    }

    /**
     * Get the y-axis position of the fruit.
     * @return The y-axis position of the fruit.
     */
    public int getY(){
        return this.y;
    }

    /**
     * Add a fruit to a List of objects of fruits.
     * @param fruit An object of PImage
     * @param x x-axis position of this fruit.
     * @param y y-axis position of this fruit.
     * @param fruits The List of objects of fruits.
     */
    public static void addFruit(PImage fruit, int x, int y, List<Fruit> fruits){
        if (fruits == null){
            return;
        }
        fruits.add(new Fruit(fruit, x, y));
    }

    /**
     * Whether this fruit is eaten.
     * @return Whether this fruit is eaten. If it is eaten, return false. Otherwise, return true.
     */
    public boolean isExist(){
        return !this.beEat;
    }

    /**
     * When a fruit is eaten, set its field 'beEat' to true.
     */
    public void delete(){
        this.beEat = true;
    }

    /**
     * Draw the fruit.
     * @param app An object of App class which is a subclass of PApplet.
     */
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


    /**
     * Whether this fruit is a super fruit.
     * @return If this fruit is a super fruit, return true. Otherwise, return false.
     */
    public boolean isSuperFruit(){
        return false;
    }

    /**
     * Whether this fruit is soda can.
     * @return If this fruit is a soda can, return true. Otherwise, return false.
     */
    public boolean isSoda(){
        return false;
    }


    /**
     * Draw all fruits including super fruits.
     * @param app An object of App class which is a subclass of PApplet.
     * @param player An object of Player class.
     * @param allFruits The list of all fruits.
     * @param allGhosts A list of all ghosts.
     * @param frameCount The number of current frame.
     */
    public static void drawFruits(PApplet app, Player player, List<Fruit> allFruits, List<Chaser> allGhosts, int frameCount){
        if (app == null || player == null || allFruits == null || allGhosts == null){
            return;
        }
        String type = player.eat(allFruits, allGhosts);
        if (type != null){
            if (type.equals("superFruit") || type.equals("soda")){
                player.setStartFrightenedTime((int) frameCount / 60);
            }
        }

        for (Fruit f : allFruits){
            f.draw(app);
        }

    }

}