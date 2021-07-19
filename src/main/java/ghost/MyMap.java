package ghost;
import java.util.*;

import java.io.*;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The class MyMap contains everything through parsing map file.
 */
public class MyMap {
    
    private String filename;
    private PImage downLeft;
    private PImage downRight;
    private PImage horizontal;
    private PImage vertical;
    private PImage upLeft;
    private PImage upRight;
    private char[][] mapList;
    private boolean[] fourDirections;
    private List<int[]> fruits = new ArrayList<>();
    private List<int[]> superFruits = new ArrayList<>();
    private List<int[]> soda = new ArrayList<>();
    private List<int[]> chasers = new ArrayList<>();
    private List<int[]> ambushers = new ArrayList<>();
    private List<int[]> ignorant = new ArrayList<>();
    private List<int[]> whims = new ArrayList<>();

    /**
     * Constructs a new MyMap object. The instance stores the parameters as its own properties.
     * @param filename the file contains all information about map
     * @param app An object of class App
     */
    public MyMap(String filename, PApplet app){
        this.filename = filename;
        transformFileTo2DList(this.filename);
        if (app != null){
            loadImage(app);
        }
    }

    /**
     * Load all images
     * @param app An object of class App.
     */
    public void loadImage(PApplet app){
        if (app == null){
            return;
        }
        this.horizontal = app.loadImage("horizontal.png");
        this.vertical = app.loadImage("vertical.png");
        this.upLeft = app.loadImage("upLeft.png");
        this.upRight = app.loadImage("upRight.png");
        this.downLeft = app.loadImage("downLeft.png");
        this.downRight = app.loadImage("downRight.png");
    }
    /**
     * Transform the file into a 2D list of string.
     * @param filename The name of the file contains map.
     */
    public void transformFileTo2DList(String filename) {
        if (filename == null){
            return;
        }
        
        char[][] lines = new char[36][28];

        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String str;
            int lineNum = 0;
            while ((str = in.readLine()) != null) {
                for (int column = 0 ; column < 28 ; column ++){
                    lines[lineNum][column] = str.charAt(column);
                }
                lineNum ++;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("This map file does not exist");
        }
        this.mapList = lines;
    }

    /**
     * Draw the map.
     * @param app An object of class App
     * @param filename the name of map file
     */
    public void drawMap(PApplet app, String filename){
        if (app == null || filename == null){
            return;
        }
        for (int i = 0; i < 36; i ++){
            for (int j = 0; j < 28; j ++){
                char num = this.mapList[i][j];
                if (num == '1'){
                    if (horizontal != null){
                        app.image(horizontal, j * 16, i * 16);
                    }
                    
                }else if (num == '2'){
                    if (vertical != null){
                        app.image(vertical, j * 16, i * 16);
                    }
                    
                }else if (num == '3'){

                    if (upLeft != null){
                        app.image(upLeft, j * 16, i * 16);
                    }
                    
                }else if (num == '4'){

                    if (upRight != null){
                        app.image(upRight, j * 16, i * 16);
                    }
                    
                }else if (num == '5'){

                    if (downLeft != null){
                        app.image(downLeft, j * 16, i * 16);
                    }
                    
                }else if (num == '6'){

                    if (downRight != null){
                        app.image(downRight, j * 16, i * 16);
                    }
                    
                }
            }
        }   
    }

    /**
     * Get original positions of all ghosts, player and fruit.
     * @param app An object of App class which is a subclass of PApplet.
     * @param filename The name of the file contains map.
     * @param player An object of Player class
     */
    public void getAllPositions(PApplet app, String filename, Player player){
        if (filename == null || player == null){
            return;
        }
        for (int i = 0; i < 36; i ++){
            for (int j = 0; j < 28; j ++){
                char num = this.mapList[i][j];
                if (num == '7'){
                    int[] position = new int[]{j * 16, i * 16};
                    this.fruits.add(position); 
                }else if (num == '8'){
                    this.superFruits.add(new int[]{j * 16, i * 16});
                }else if (num == 'p'){
                    player.setPosition(j * 16, i * 16);
                }else if (num == 'c'){
                    int[] position = new int[]{j * 16, i * 16};
                    this.chasers.add(position);
                }else if (num == 'a'){
                    int[] position = new int[]{j * 16, i * 16};
                    this.ambushers.add(position);
                }else if (num == 'i'){
                    int[] position = new int[]{j * 16, i * 16};
                    this.ignorant.add(position);
                }else if (num == 'w'){
                    int[] position = new int[]{j * 16, i * 16};
                    this.whims.add(position);
                }else if (num == 's'){
                    int[] position = new int[]{j * 16, i * 16};
                    this.soda.add(position);
                }
            }
        }   
    }

    /**
     * Get all positions of chasers.
     * @return A list of array which contains x-asis position and y-asis position.
     */
    public List<int[]> getChasersList(){
        return this.chasers;
    }

    /**
     * Get all positions of ambushers.
     * @return A list of array which contains x-asis position and y-asis position.
     */
    public List<int[]> getAmbushersList(){
        return this.ambushers;
    }


    /**
     * Get all positions of ignorant.
     * @return A list of array which contains x-asis position and y-asis position.
     */
    public List<int[]> getIgnorantList(){
        return this.ignorant;
    }

    /**
     * Get all positions of whims.
     * @return A list of array which contains x-asis position and y-asis position.
     */
    public List<int[]> getWhimsList(){
        return this.whims;
    }

    /**
     * Get all positions of fruits.
     * @return A list of array which contains x-asis position and y-asis position.
     */
    public List<int[]> getFruitsList(){
        return this.fruits;
    }

    /**
     * Get all positions of soda can.
     * @return A list of array which contains x-asis position and y-asis position.
     */
    public List<int[]> getSodaList(){
        return this.soda;
    }

    /**
     * Get all positions of super fruits.
     * @return A list of array which contains x-asis position and y-asis position.
     */
    public List<int[]> getSuperFruitsList(){
        return this.superFruits;
    }

    /**
     * Determine if it is a wall 
     * @param c a number or letter in the file
     * @return if it represents wall, return true. Otherwise return false.
     */
    public boolean isWall(char c){
        return c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6'; 
    }

    /**
     * Determine whether the four directions of the player's current location are walls.
     * @param x The x-asis position.
     * @param y The y-asis position.
     * @return A boolean array, [up is wall or not, left is wall or not, right is wall or not, down is wall or not]
     */
    public boolean[] judgeWall(int x, int y){
        
        if (x % 16 == 0 && y % 16 == 0){
            int row = (int) y / 16;
            int column = (int) x / 16;

            if (mapList == null){
                return null;
            }

            // Since the map is surrounded by walls, the player’s surroundings will not cross the border
            this.fourDirections = new boolean[]{isWall(mapList[row - 1][column]), isWall(mapList[row][column - 1]),
                isWall(mapList[row][column + 1]), isWall(mapList[row + 1][column])};
        }      
        
        return fourDirections;
    }
}