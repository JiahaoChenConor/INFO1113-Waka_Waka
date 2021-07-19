package ghost;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * The main class, inherited class PApplet, used to run the app
 */
public class App extends PApplet {
    public static final int WIDTH = 448;
    public static final int HEIGHT = 576;
    private Run runner;
    

    public App() {}

    public Run getRunner(){
        return this.runner;
    }

    /**
     * Set up all images
     */
    public void setup() {
        frameRate(60);
        
        PImage playerClosed = this.loadImage("playerClosed.png");
        PImage playerUp= this.loadImage("playerUp.png");
        PImage playerDown = this.loadImage("playerDown.png");
        PImage playerLeft = this.loadImage("playerLeft.png");
        PImage playerRight = this.loadImage("playerRight.png");
        PImage chaser = this.loadImage("chaser.png");
        PImage ambusher = this.loadImage("ambusher.png");
        PImage ignorant = this.loadImage("ignorant.png");
        PImage whim = this.loadImage("whim.png");
        PImage frightened = this.loadImage("frightened.png");
        PImage fruit = this.loadImage("fruit.png");
        PImage superFruit = this.loadImage("superFruit.png");
        PImage soda = this.loadImage("soda-can.png");
        runner = new Run();
        PFont font = this.createFont("PressStart2P-Regular.ttf", 32);
        runner.setObjects(this, playerClosed, playerLeft, playerRight, playerUp, playerDown, fruit, superFruit, chaser, ambusher, ignorant, whim, frightened, soda, font);
        this.stroke(255);
    }

    /**
     * Set the width and height.
     */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Draw everything.
     */
    public void draw() {  
        background(0, 0, 0);
        if (!runner.isEnd()){
            runner.getPlayer().drawLives(this, (int) runner.getLives());
            runner.getMap().drawMap(this, "map.txt");
            runner.getPlayer().playerMove(this, runner.getPlayer(), keyPressed, keyCode, runner.getMap(), frameCount);
            Fruit.drawFruits(this, runner.getPlayer(), runner.getAllFruits(), runner.getAllGhosts(), frameCount);
            runner.getPlayer().drawPlayer(this, frameCount, runner.getAllGhosts());
            boolean isReset = Chaser.drawGhosts(this, runner.getModeLengths(), frameCount, runner.getFrightenedLen(), runner.getAllGhosts(), runner.getChasers(), runner.getPlayer(), runner.getMap());
            runner.setReset(isReset);
            runner.reset();   
        }
        runner.winOrOver(this, frameCount);
    }

    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }
}