package ghost;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * This class manage most fields and main methods.
 */
public class Run {
    private Player player;
    private List<Fruit> fruits = new ArrayList<>();
    private List<Fruit> superFruits = new ArrayList<>();

    private List<Fruit> sodaCans = new ArrayList<>();

    private List<Fruit> allFruits = new ArrayList<>();
    private MyMap map;
    private boolean end = false;
    private int endTime = Integer.MAX_VALUE;
    private String filename;
    private long lives;
    private long speed;
    private long frightenedLen;
    private List<Integer> modeLengths = new ArrayList<>();

    private List<Chaser> chasers = new ArrayList<>();
    private List<Chaser> ambushers = new ArrayList<>();
    private List<Chaser> ignorants = new ArrayList<>();
    private List<Chaser> whims = new ArrayList<>();

    private List<Chaser> allGhosts = new ArrayList<>();

    private boolean reset = false;
    private PFont font;

    public Run(){

    }

    /**
     * Set end
     * @param status status of end
     */
    public void setEnd(boolean status){
        this.end = status;
    }

    /**
     * get end value
     * @return end value
     */
    public boolean getEnd(){
        return this.end;
    }

    /**
     * Get the object of MyMap.
     * @return Get the object of MyMap.
     */
    public MyMap getMap(){
        return map;
    }

    /**
     * Get the object of Player.
     * @return Get the object of Player.
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Get the remaining lives of the player.
     * @return Get the number of lives of the player.
     */
    public int getLives(){
        return (int) lives;
    }

    /**
     * Get the frightened length.
     * @return Get the frightened length.
     */
    public int getFrightenedLen(){
        return (int) frightenedLen;
    }

    /**
     * Get the list of common fruits.
     * @return Get the list of common fruits.
     */
    public List<Fruit> getFruits(){
        return fruits;
    }
    
    /**
     * Get the list of super fruits.
     * @return Get the list of super fruits.
     */
    public List<Fruit> getSuperFruits(){
        return superFruits;
    }

    /**
     * Get the list of soda cans.
     * @return Get the list of soda cans.
     */
    public List<Fruit> getSodaCans(){
        return sodaCans;
    }

    /**
     * Get the list of all fruits.
     * @return Get the list of all fruits.
     */
    public List<Fruit> getAllFruits(){
        return allFruits;
    }

    /**
     * Get the list of all ghosts.
     * @return Get the list of all ghosts.
     */
    public List<Chaser> getAllGhosts(){
        return allGhosts;
    }

    /**
     * Set whether we need to reset all positions of ghosts and player.
     * @param status Set the reset field into new value.
     */
    public void setReset(boolean status){
        this.reset = status;
    }

    /**
     * Get reset value.
     * @return the value of the field reset.
     */
    public boolean getReset(){
        return this.reset;
    }

    /**
     * Get mode lengths.
     * @return Get a list of Integer represents mode lengths.
     */
    public List<Integer> getModeLengths(){
        return modeLengths;
    }

    /**
     * Get all chasers.
     * @return A list of all chasers.
     */
    public List<Chaser> getChasers(){
        return chasers;
    }

    /**
     * Read the .json file.
     */
    public void readJson(){
        JSONParser jsonParser = new JSONParser();
        try{
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("config.json"));
            this.filename = (String) jsonObject.get("map");
            this.lives = (long) jsonObject.get("lives");
            this.speed = (long) jsonObject.get("speed");
            this.frightenedLen = (long) jsonObject.get("frightenedLength");
            JSONArray jsonArray = (JSONArray) jsonObject.get("modeLengths");

            if (jsonArray != null){
                for (int i = 0; i < jsonArray.size(); i++){
                    this.modeLengths.add(Integer.parseInt(jsonArray.get(i).toString()));
                }
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Initialize objects of all required classes
     * @param app An object of App class which is a subclass of PApplet.
     * @param playerClosed An object of PImage which load the image of player closed.
     * @param playerLeft An object of PImage which load the image of player left opened.
     * @param playerRight An object of PImage which load the image of player right opened.
     * @param playerUp An object of PImage which load the image of player up opened.
     * @param playerDown An object of PImage which load the image of player down opened.
     * @param fruit An object of PImage which load the image of fruit.
     * @param superFruit An object of PImage which load the image of super fruit.
     * @param chaser An object of PImage which load the image of chaser.
     * @param ambusher An object of PImage which load the image of ambusher.
     * @param ignorant An object of PImage which load the image of ignorant.
     * @param whim An object of PImage which load the image of whim.
     * @param frightened An object of PImage which load the image of frightened ghost.
     * @param soda An object of PImage which load the image of soda.
     * @param font An object of Font.
     */ 
    public void setObjects(PApplet app, PImage playerClosed, PImage playerLeft, PImage playerRight, PImage playerUp, PImage playerDown, PImage fruit, PImage superFruit, PImage chaser, PImage ambusher, PImage ignorant, PImage whim, PImage frightened, PImage soda, PFont font){

        readJson();
        this.player = new Player(0, 0, playerClosed, playerLeft, playerRight, playerUp, playerDown, (int) this.speed, app);
        
        this.map = new MyMap(this.filename, app);
        this.map.getAllPositions(app, this.filename, player);
        this.font = font;
        List<int[]> fruitsPositions = this.map.getFruitsList();
        for (int[] p : fruitsPositions){
            this.fruits.add(new Fruit(fruit, p[0], p[1]));
        }

        List<int[]> superFruitsPositions = this.map.getSuperFruitsList();
        for (int[] p : superFruitsPositions){
            this.superFruits.add(new SuperFruit(superFruit, p[0], p[1]));
        }

        List<int[]> sodaPositions = this.map.getSodaList();
        for (int[] p : sodaPositions){
            this.sodaCans.add(new Soda(soda, p[0], p[1]));
        }
        allFruits.addAll(fruits);
        allFruits.addAll(superFruits);
        allFruits.addAll(sodaCans);

        List<int[]> chasersPositions = this.map.getChasersList();
        for (int[] p : chasersPositions){
            this.chasers.add(new Chaser(p[0], p[1], chaser, frightened, (int)this.speed));
        }

        List<int[]> ambushersPositions = this.map.getAmbushersList();
        for (int[] p : ambushersPositions){
            this.ambushers.add(new Ambusher(p[0], p[1], ambusher, frightened, (int) this.speed));
        }

        List<int[]> ignorantPositions = this.map.getIgnorantList();
        for (int[] p : ignorantPositions){
            this.ignorants.add(new Ignorant(p[0], p[1], ignorant, frightened, (int) this.speed));
        }

        List<int[]> whimsPositions = this.map.getWhimsList();
        for (int[] p : whimsPositions){
            this.whims.add(new Whim(p[0], p[1], whim, frightened, (int) this.speed));
        }

        allGhosts.addAll(chasers);
        allGhosts.addAll(ignorants);
        allGhosts.addAll(ambushers);
        allGhosts.addAll(whims);
    }

    /**
     * Reset the player and ghosts.
     */
    public void reset(){
        if (this.reset){
            this.lives -= 1;
            if (player != null){
                this.player.reset();
            }
            for (Chaser g : allGhosts){
                g.reset();
            }
            this.reset = false;
        }
    }

    /**
     * Judge the game is end or not.
     * @return If the game has ended, return true. Otherwise, return false.
     */
    public boolean isEnd(){
        return this.end;
    }

    /**
     * Set the end time.
     * @param endTime the end time.
     */
    public void setEndTime(int endTime){
        this.endTime = endTime;
    }

    /**
     * Get the end Time
     * @return End time.
     */
    public int getEndTime(){
        return this.endTime;
    }

    /**
     * Set lives
     * @param lives the number of lives
     */
    public void setLives(int lives){
        this.lives = lives;
    }


    /**
     * Judge win or over
     * @param app An object of App
     * @param frameCount Accumulated frame number of current time.
     */
    public void winOrOver(PApplet app, int frameCount){
    
        if (font != null){
            app.textFont(font);
        }
        
        int now = (int) frameCount / 60;

        if (now - endTime >= 10){
            if (app != null){
                app.setup();
            }
            
            endTime = Integer.MAX_VALUE;
            end = false;
        }


        boolean win = true;
        for (Fruit f : this.allFruits){
            if (f.beEat == false){
                win = false;
                break;
            }
        }

        if (win){
            if (app != null){
                app.text("YOU WIN", 112, 280);
            }
            

            if (end == false){
                endTime = now;
            }
            end = true;
            
        }

        boolean over = false;
        if (lives < 0){
            over = true;
        }

        if (over){
            if (app != null){
                app.text("GAME OVER", 95, 280);
            }
            

            if (end == false){
                endTime = now;
            }
            end = true;
        }

        
    }


}