package ghost;
import processing.core.PImage;
import java.util.List;
import processing.core.PApplet;

/**
 * The class Player represents Waka. 
 */
public class Player {

    private int x;
    private int y;

    private int startX;
    private int startY;
    private boolean setStart = true;
    private PImage playerClosed;
    private PImage player;

    private PImage playerLeft;
    private PImage playerRight;
    private PImage playerUp;
    private PImage playerDown;
    private int goVertical;
    private int goHorizon;
    private int direction;
    private int speed;
    private boolean isFrightened = false;
    private boolean debug = false;

    private int unavailableDir = -1;

    private int startFrightenedTime = Integer.MAX_VALUE;
    private int frightenedTimes = 0;
    
    private int previousKeyCode = 0;
    private int nowKeyCode = 0;

    /**
     * Constructs a new Player object. The instance stores the parameters as its own properties.
     * @param x x-axis position 
     * @param y y-axis position
     * @param player the image of player
     * @param playerLeft the image of playerLeft
     * @param playerRight the image of playerRight
     * @param playerUp the image of playerUp
     * @param playerDown the image of playerDown
     * @param speed the speed of player
     * @param app An object of class App
     */
    public Player(int x, int y, PImage player, PImage playerLeft, PImage playerRight, PImage playerUp, PImage playerDown, int speed, PApplet app){
        this.x = x;
        this.y = y;
        this.playerClosed = player;
        this.player = playerLeft;
        this.playerLeft = playerLeft;
        this.playerRight = playerRight;
        this.playerUp = playerUp;
        this.playerDown = playerDown;

        this.speed = speed;
        this.goVertical = 0;
        this.goHorizon = 0;

    }

    /**
     * set x-axis positions
     * @param x x-axis position
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * set y-axis position
     * @param y y-axis position
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Set unavailable direction.
     * @param d direction number
     */
    public void setUnavailableDir(int d){
        this.unavailableDir = d;
    }

    /**
     * Get unavailable direction.
     * @return unavailable direction number.
     */
    public int getUnavailableDir(){
        return this.unavailableDir;
    }

    /**
     * Receive keyboard input and convert it into the corresponding direction
     * 
     *       0 represents up <br>
     *       1 represents left <br>
     *       2 represents right <br>
     *       3 represents down <br>
     * @param key keyCode, up: 38, left: 37, right: 39, down: 40
     * @return return the number representing the direction button in keyboard. If the keyCode not in 38, 37, 39, 40 return -1.
     */
    public static int directionNum(int key){
        if (key == 38){
            return 0;
        }else if (key == 37){
            return 1;
        }else if (key == 39){
            return 2;
        }else if (key == 40){
            return 3;
        }
        return -1;
    }

    /**
     * return the PImage object
     * @return return the PImage object
     */
    public PImage getPlayerClosed(){
        return this.playerClosed;
    }

    /**
     * return the PImage object
     * @return return the PImage object
     */
    public PImage getPlayerLeft(){
        return this.playerLeft;
    }

    /**
     * return the PImage object
     * @return return the PImage object
     */
    public PImage getPlayerRight(){
        return this.playerRight;
    }

    /**
     * return the PImage object
     * @return return the PImage object
     */
    public PImage getPlayerUp(){
        return this.playerUp;
    }

    /**
     * return the PImage object
     * @return return the PImage object
     */
    public PImage getPlayerDown(){
        return this.playerDown;
    }



    /**
     * Get the x-asis position of the player 
     * @return the x-asis position of the player
     */
    public int getX(){
        return this.x;
    }

    /**
     * Get the y-asis position of the player 
     * @return the y-asis position of the player
     */
    public int getY(){
        return this.y;
    }

    /**
     * Get the direction of the player, 0 represents up, 1 represents left, 2 represents right, 3 represents down
     * @return the direction of the player
     */
    public int getDirection(){
        return this.direction;
    }

    /**
     * Get the start frightened time of the player 
     * @return the start frightened time of the player
     */
    public int getStartFrightenedTime(){
        return this.startFrightenedTime;
    }

    /**
     * Set the start frightened time of the player 
     * @param time the start frightened time of the player
     */
    public void setStartFrightenedTime(int time){
        this.startFrightenedTime = time;
    }

    /**
     * Get the status of the player : frightened or not.
     * @return The status of the player, true means frightened, false means not frightened.
     */
    public boolean getIsFrightened(){
        return this.isFrightened;
    }

    /**
     * Set the status of the player : frightened or not.
     * @param status Whether frightened, true means frightened, false means not frightened.
     */
    public void setFrightened(boolean status){
        this.isFrightened = status;
        if (status == true){
            this.frightenedTimes ++ ;
        }
    }

    /**
     * Set the start position of the player.
     * @param x x-axis coordinate of the player.
     * @param y y-axis coordinate of the player.
     */
    public void setPosition(int x, int y){
        if (setStart){
            this.startX = x;
            this.startY = y;
            setStart = false;
        }

        this.x = x;
        this.y = y;
    }

    /**
     * Reset the player's position to the initial position.
     */
    public void reset(){
        this.x = this.startX;
        this.y = this.startY;
    }

    /**
     * Get the frightened times of the player.
     * @return The frightened times of the player.
     */
    public int getFrightenedTimes(){
        return this.frightenedTimes;
    }

    /**
     * return the status of debug
     * @return the status of debug
     */
    public boolean getDebug(){
        return this.debug;
    }

    /**
     * Set debug mode.
     * @param status the status of debug.
     */
    public void setDebug(boolean status){
        this.debug = status;
    }

    /**
     * Start the debug mode according to the value of the field: debug.
     * <p>
     * If any reference parameter is null, do nothing.
     * </p>
     * @param app An object of App class which is a subclass of PApplet.
     * @param allGhosts A list of all objects of ghosts.
     */
    public void debugMode(PApplet app, List<Chaser> allGhosts){
        if (allGhosts == null){
            return;
        }

        if (this.debug){
            for (Chaser g : allGhosts){
                if (app != null){
                    
                    g.drawLine(app);
                }
            }
        }
    }

    /**
     * Draw the player and the line between the player and all ghosts according to whether debug mode is enabled.
     * <p>
     * In one 8 frame, draw the player of closed mouth and in the next 8 frame, draw the player with open mouth.<br>
     * If any reference parameter is null, do nothing.
     * </p>
     * @param app An object of App class which is a subclass of PApplet
     * @param frameCount The number of current frame
     * @param allGhosts The list of objects of all ghosts 
     */
    public void drawPlayer(PApplet app, int frameCount, List<Chaser> allGhosts){
        if (allGhosts == null){
            return;
        }
        if ((frameCount / 8) % 2 == 0){
            if (app != null){
                app.image(this.playerClosed, this.x - 5, this.y - 5);
            }
            
        }else{
            if (app != null){
                app.image(this.player, this.x - 5, this.y - 5);
            }
            
        }

        debugMode(app, allGhosts);
    
    }
    
    /**
     * Draw the remaining lives of the player on the bottom of the screen.
     * @param app An object of App class which is a subclass of PApplet
     * @param lives The remaining number of lives
     */
    public void drawLives (PApplet app, int lives){
        if (app == null){
            return;
        }
        for (int i = 0; i < lives; i++){
            if (this.playerRight != null){
                app.image(this.playerRight, 12 + i * 28, 34 * 16);
            }
            
        }
    }

    /**
     * Eat the fruit in the current position and return the type of the fruit.
     * If any reference parameter is null, return false.
     * @param fruits The list of all fruits including super fruits
     * @param ghosts The list of all ghosts
     * @return If it is a common fruit return "fruit", if it is a super fruit, return "superFruit". If it is a soda can, return "soda".
     */
    public String eat(List<Fruit> fruits, List<Chaser> ghosts) {
        if (fruits == null || ghosts == null){
            return null;
        }

        for (Fruit f : fruits){
            if (f.getX() == this.x && f.getY() == this.y && f.isExist()){
                f.delete();
                if (f.isSuperFruit()){
                    for (Chaser g : ghosts){
                        g.setFrightened(true);
                    }
                    setFrightened(true);
                    
                    return "superFruit";
                }
                if (f.isSoda()){
                    for (Chaser g : ghosts){
                        g.setFrightened(true);
                        g.setInvisible(true);
                    }
                    setFrightened(true);

                    return "soda";
                }
            }
        }
        return "fruit";
    }


    /**
     * Receive keyboard input and convert it into the corresponding direction
     * Then change the direction of the player and change the displacement in a specific direction
     * If any reference parameter is null, do nothing.
     * @param dir The number representation of direction
     * @param app An object of App class which is a subclass of PApplet
     */
    public void receiveKeyboard(int dir, PApplet app){
        if (app == null){
            return;
        }

        if (dir == 0){
            up();
            this.player = this.playerUp;
        }else if (dir == 1){
            left();
            this.player = this.playerLeft;
        }else if (dir == 2){
            right();
            this.player = this.playerRight;
        }else if (dir == 3){
            down();
            this.player = this.playerDown;
        }
    }

    /**
     * Change up displacement and change the direction of player into up.
     */
    public void up(){
        this.goVertical = 0 - this.speed;
        this.goHorizon = 0;
        this.direction = 0;
    }

    /**
     * Change left displacement and change the direction of player into left.
     */
    public void left(){
        this.goHorizon = 0 - this.speed;
        this.goVertical = 0;
        this.direction = 1;
    }

    /**
     * Change right displacement and change the direction of player into right.
     */
    public void right(){
        this.goHorizon = this.speed;
        this.goVertical = 0;
        this.direction = 2;
    }

    /**
     * Change down displacement and change the direction of player into down.
     */
    public void down(){
        this.goVertical = this.speed;
        this.goHorizon = 0;
        this.direction = 3;
    }

    /**
     * Move the player according to field 'goHorizon' and field 'goVertical'    <br>
     * When goHorizon is greater than 0, it means y-axis plus goHorizon which is positive, go down, vice versa. <br>
     * When goVertical is greater than 0, it means x-axis plus goVertical which is positive, go right, vice versa.
     */
    public void move(){
        this.x += this.goHorizon;
        this.y += this.goVertical; 
    }


    /**
     * Move the player according to the keyCode
     * If any reference parameter is null, do nothing.
     * @param app An object of App class which is a subclass of PApplet.
     * @param player An object of Player class
     * @param keyPressed Whether press the keyboard, pressed is true, not pressed is false
     * @param keyCode The number of the button pressed in keyboard
     * @param map An object of MyMap class
     * @param frameCount The number of current frame.
     */
    public void playerMove(PApplet app, Player player, boolean keyPressed, int keyCode, MyMap map, int frameCount){
        if (app == null || player == null || map == null){
            return;
        }

        boolean[] walls = null;
        if (player.getX() % 16 == 0 && player.getY() % 16 == 0){
            walls = map.judgeWall(player.getX(), player.getY());
            if (unavailableDir >= 0 && !walls[unavailableDir]){
                player.receiveKeyboard(unavailableDir, app);
            }
        }
        
        this.nowKeyCode = keyCode;
        if (this.nowKeyCode == 32 && this.previousKeyCode == 0){
            this.debug = !this.debug;
            this.previousKeyCode = this.nowKeyCode;
            return;
        }
        this.previousKeyCode = this.nowKeyCode;

        if (keyPressed){

            if (player.getX() % 16 == 0 && player.getY() % 16 == 0){
                int dir = Player.directionNum(keyCode);
                
                if (dir == -1){
                    return;
                }

                walls = map.judgeWall(player.getX(), player.getY());
                if (!walls[dir]){
                    player.receiveKeyboard(dir, app);
                }else{
                    unavailableDir = dir;
                }
            }else{
                int dir = Player.directionNum(keyCode);
                unavailableDir = dir;
            }
        }

        if (walls == null || !walls[player.getDirection()]){
            player.move();
        }
    }

}