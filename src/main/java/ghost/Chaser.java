package ghost;

import processing.core.PImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import processing.core.PApplet;

/**
 * A kind of Ghost.
 */
public class Chaser {

    protected int x;
    protected int y;

    protected int startX;
    protected int startY;

    protected int goHorizontal;
    protected int goVertical;

    protected PImage ghost;
    protected PImage frightened;
    protected boolean isAlive = true;

    protected int speed;
    protected int[] target = new int[2];
    protected boolean isFrightened = false;
    protected boolean isInvisible = false;
    protected int startFrightenedTime = Integer.MAX_VALUE;
    
    /**
     * Constructs a new Chaser object. The instance stores the parameters as its own properties.
     * @param x the x-axis position
     * @param y the y-axis position
     * @param ghost image of chaser
     * @param frightened image of frightened chaser
     * @param speed the speed of chaser
     */
    public Chaser(int x, int y, PImage ghost, PImage frightened, int speed){
        this.x = x;
        this.y = y;

        this.startX = x;
        this.startY = y;

        this.ghost = ghost;
        this.frightened = frightened;
        this.speed = speed;
    }

    /**
     * Move the chaser according to field 'goHorizon' and field 'goVertical'
     * <p>
     * When goHorizon is greater than 0, it means y-axis plus goHorizon which is positive, go down, vice versa.
     * When goVertical is greater than 0, it means x-axis plus goVertical which is positive, go right, vice versa.
     * </p>
     */
    public void move(){
        this.x += this.goHorizontal;
        this.y += this.goVertical;
    }

    /**
     * Get the x-asis position of the chaser
     * @return the x-asis position of the chaser
     */
    public int getX(){
        return this.x;
    }

    /**
     * Get the y-asis position of the chaser
     * @return the y-asis position of the chaser
     */
    public int getY(){
        return this.y;
    }

    /**
     * Get the target of chaser
     * @return an array contains two integers, the first element is x-axis position and the second one is y-axis position.
     */
    public int[] getTarget(){
        return this.target;
    }

    /**
     * Set the status of this ghost.
     * @return frightened is true, false is not frightened.
     */
    public boolean getFrightened(){
        return this.isFrightened;
    }
        
    /**
     * Set the status of this ghost.
     * @param status true is frightened, false is not frightened.
     */
    public void setFrightened(boolean status){
        this.isFrightened = status;
    }

    /**
     * Draw the ghost
     * If any reference parameter is null, do nothing.
     * @param app An object of App class which is a subclass of PApplet.
     */
    public void draw(PApplet app){
        if (app == null){
            return;
        }

        if (!this.isFrightened && this.isAlive){
            app.image(this.ghost, this.x - 5, this.y - 5);
        }else if (this.isFrightened && this.isAlive){
            app.image(this.frightened, this.x - 5, this.y - 5);
        }
        
    }

    /**
     * Draw the line between the ghost and its target
     * If any reference parameter is null, do nothing.
     * @param app An object of App class which is a subclass of PApplet.
     */
    public void drawLine(PApplet app){

        if (this.isAlive){
            if (app != null){
                if (!this.isFrightened){
                    app.line(this.x, this.y, target[0], target[1]);
                }else{
                    if (this.goHorizontal > 0){
                        app.line(this.x, this.y, this.x + 16, this.y);
                    }else if (this.goHorizontal < 0){
                        app.line(this.x, this.y, this.x - 16, this.y);
                    }else if (this.goVertical > 0){
                        app.line(this.x, this.y, this.x, this.y + 16);
                    }else if (this.goVertical < 0){
                        app.line(this.x, this.y, this.x, this.y - 16);
                    }
                }
            }
        }
        
    }

    /**
     * Judge this ghost is alive or not
     * @return If this ghost is still alive, return true, otherwise return false.
     */
    public boolean getIsAlive(){
        return this.isAlive;
    }

    /**
     * Set the ghosts is alive of not
     * @param status alive or dead
     */
    public void setAlive(boolean status){
        this.isAlive = status;
    }

    /**
     * Make this ghost die
     */
    public void die(){
        this.isAlive = false;
    }

    /**
     * Get the ghost is invisible or not.
     * @return If this ghost is invisible, return true, otherwise return false.
     */
    public boolean getIsInvisible(){
        return this.isInvisible;
    }

    /**
     * Set the invisible status.
     * @param status Set the ghost's field 'invisible' into value of status.
     */
    public void setInvisible(boolean status){
        this.isInvisible = status;
    }

    /**
     * Set the position of this ghost
     * @param x x-axis
     * @param y y-axis
     */
    public void setPosition(int x, int y){
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
     * Calculate the distance between two positions 
     * @param startX x-axis of the start position
     * @param startY y-axis of the start position
     * @param endX x-axis of the end position
     * @param endY y-axis of the end position
     * @return return the distance between two sets of coordinates
     */
    public double calculateDistance(int startX, int startY, int endX, int endY){
        return Math.sqrt((startX - endX) * (startX  - endX) + (startY - endY) * (startY - endY));
    }

    /**
     * Set the mode of the player.
     * If any reference parameter is null, do nothing.
     * @param isScatter whether the player is in scatter mode.
     * @param player An object of the class Player.
     * @param map An object of the class MyMap.
     * @param chaser An object of the class Chaser.
     */
    public void setMode(boolean isScatter, Player player, MyMap map, Chaser chaser){
        // here we do not use chaser, only whim use
        if (player == null){
            return;
        }

        if (this.x % 16 == 0 && this.y % 16 == 0){
            updateTarget(isScatter, player, chaser);
            List<String> priorities = constructPriorities();
            setPriorityDirection(priorities, map);
        }
    }   

    /**
     * Update the target according to different modes.
     * <p>
     * Upper left corner (0, 0), Upper right corner (28 * 16, 0), Bottom left (0, 35 * 16), Bottom right (28 * 16, 35 * 16)<br>
     * If any reference parameter is null, do nothing.
     * </p>
     * @param isScatter If it is true, the mode is 'scatter'.If it is false, the mode is 'chase'.
     * @param player An object of Player.
     * @param chaser An object of the class Chaser.
     */
    public void updateTarget(boolean isScatter, Player player, Chaser chaser){
        // here we do not use chaser, only whim use
        if (player == null){
            return;
        }
 
        int playerX = player.getX();
        int playerY = player.getY();

        if (isScatter){
            this.target = new int[]{0, 0};

        }else{
            this.target[0] = playerX;
            this.target[1] = playerY;
        }
        checkTarget();
    }

    /**
     *  If the target location is outside the bounds of the grid, the closest point is used.
     */
    public void checkTarget(){
        if (this.target[0] < 0){
            this.target[0] = 0;
        }else if (this.target[0] > 28 * 16){
            this.target[0] = 28 * 16;
        }

        if (this.target[1] < 0){
            this.target[1] = 0;
        }else if (this.target[1] > 35 * 16){
            this.target[1] = 35 * 16;
        }
    }

    /**
     * Whether one direction can go.
     * If any reference parameter is null, do nothing.
     * @param direction The string of one direction.
     * @param map An object of MyMap
     * @return Whether this direction can go.
     */
    public boolean canGo(String direction, MyMap map){

        if (direction == null){
            return false;
        }

        boolean[] walls = map.judgeWall(this.x, this.y);
        // If you want to go up, there must be no wall above you 
        //    and your current direction cannot be down(can't step back, unless there is no way to go)
        if (direction.equals("Up")){
            if (!walls[0] && !(this.goVertical > 0)){
                return true;
            }else{
                return false;
            }
        }else if (direction.equals("Left")){
            if (!walls[1] && !(this.goHorizontal > 0)){
                return true;
            }else{
                return false;
            }
        }else if (direction.equals("Right")){
            if (!walls[2] && !(this.goHorizontal < 0)){
                return true;
            }else{
                return false;
            }
        }else if (direction.equals("Down")){
            if (!walls[3] && !(this.goVertical < 0)){
                return true;
            }else{
                return false;
            }
        }

        return false;
    }

    /**
     * Construct a list of string represents the priority in the four directions.
     * @return a list of string represents the priority in the four directions.
     */
    public List<String> constructPriorities(){
        if (!this.isFrightened){
            double up = calculateDistance(target[0], target[1], this.x, this.y - 16);
            double down = calculateDistance(target[0], target[1], this.x, this.y + 16);
            double left = calculateDistance(target[0], target[1], this.x - 16, this.y);
            double right = calculateDistance(target[0], target[1], this.x + 16, this.y);

            Map<Double,String> map = new TreeMap<>();
            map.put(up, "Up");
            map.put(down, "Down");
            map.put(left, "Left");
            map.put(right, "Right");
        
            Set<Double> keySet = map.keySet();
            List<String> ans = new ArrayList<>();

            for (Double key : keySet) {
                ans.add(map.get(key));
                if (up == key.doubleValue() && !ans.contains("Up")){
                    ans.add("Up");
                }
                if (down == key.doubleValue() && !ans.contains("Down")){
                    ans.add("Down");
                }
                if (left == key.doubleValue() && !ans.contains("Left")){
                    ans.add("Left");
                }
                // map must contains "Right", because Right is put at last, if there is repeated value,
                //  "Right" value will replace it
            }

            return ans;

        }else{
            List<String> ans = new ArrayList<>();
            ans.add("Up");
            ans.add("Down");
            ans.add("Left");
            ans.add("Right");
            Collections.shuffle(ans);

            return ans;
        }
        
    }

    /**
     * Change the displacement in the corresponding direction.
     * If any reference parameter is null, do nothing.
     * @param direction direction String, including "Left" "Right" "Up" "Down".
     */
    public void setDirection(String direction){
        if (direction == null){
            return;
        }

        if (direction.equals("Up")){
            this.goHorizontal = 0;
            this.goVertical = 0 - this.speed;
        }else if (direction.equals("Down")){
            this.goHorizontal = 0;
            this.goVertical = this.speed;
        }else if (direction.equals("Left")){
            this.goHorizontal = 0 - this.speed;
            this.goVertical = 0;
        }else if (direction.equals("Right")){
            this.goHorizontal = this.speed;
            this.goVertical = 0;
        }
    }

    /**
     * Change the displacement of the chaser in the most suitable direction. In short, change the direction of the ghost.
     * @param priorities A list of string represents directions.
     * @param map An object of MyMap.
     */
    public void setPriorityDirection(List<String> priorities, MyMap map){
        if (priorities == null || map == null){
            return;
        }
        
        for (String priority : priorities){
            if (canGo(priority, map)){
                setDirection(priority);
                return;
            }
        }

        // // If there is no way to go, go in the opposite direction of the current direction.
        // this.goHorizontal = 0 - this.goHorizontal;
        // this.goVertical = 0 - this.goVertical;
    }
    


    /**
     * prefix sum of the list modeLengths
     * If any reference parameter is null, return null.
     * <p>
     * For example : <br>
     * modeLengths: [2, 20, 2, 30, 2, 100] <br>
     * prefixSum: [0,2, 22, 24, 54, 56, 156]<br>
     * so the number in prefix sum represents the current time(second)
     * If the current time is 52, 52 % 156 = 52 and 52 is between 24 and 54.
     * </p>
     * @param modeLengths The list of integer represents the length of every mode.
     * @return The prefix sum of the list modeLengths.If modeLengths is null, return null.
     */
    public static int[] prefixSum(List<Integer> modeLengths){
        if (modeLengths == null){
            return null;
        }
        int[] res = new int[modeLengths.size() + 1];
        res[0] = 0;
        for (int i = 1; i < res.length; i++){
            res[i] = res[i - 1] + modeLengths.get(i - 1);
        }

        return res;
    }

    /**
     * Draw all ghosts according to their modes.
     * If any reference parameter is null, return false.
     * @param app An object of App class which is a subclass of PApplet.
     * @param modeLengths The list of integer represents the length of every mode.
     * @param frameCount The number of current frame.
     * @param frightenedLen The length of frightened.
     * @param allGhosts The list of all ghosts.
     * @param chasers The list of chasers.
     * @param player An object of Player.
     * @param map An object of MyMap.
     * @return If the player is eaten by the ghost, return true, If not, return false. If any reference parameter is null, return false.
     */
    public static boolean drawGhosts(PApplet app, List<Integer> modeLengths, int frameCount, int frightenedLen, List<Chaser> allGhosts, List<Chaser> chasers, Player player, MyMap map){
        if (app == null || modeLengths == null || allGhosts == null || chasers == null || player == null ||  map == null){
            return false;
        }
        
        int[] prefix = prefixSum(modeLengths);
        int second = (int) frameCount / 60;

        if (second - player.getStartFrightenedTime() >= frightenedLen){
            for (Chaser g : allGhosts){
                g.setFrightened(false);
                g.setInvisible(false);
            }
            
            player.setFrightened(false);
            player.setStartFrightenedTime(Integer.MAX_VALUE);
        }

        second -= frightenedLen * player.getFrightenedTimes();
        int now = -1;
        for (int i = 0; i < prefix.length - 1; i++){
            int start = prefix[i];
            int end = prefix[i + 1];
            if (second >= start && second < end){
                now = i;
                break;
            }
        }

        for (Chaser g : allGhosts){
            if ((Math.abs(g.getX() - player.getX()) < 16 && g.getY() == player.getY()) || (Math.abs(g.getY() - player.getY()) < 16) && g.getX() == player.getX()){                
                if (!g.isFrightened && g.getIsAlive()){
                    return true;
                }else{
                    g.die();
                }
            }

            if (now % 2 == 0){
                if (chasers.size() == 0){
                    g.setMode(true, player, map, null);
                }else{  
                    g.setMode(true, player, map, chasers.get(0));
                }
                
            }
            else{
                if (chasers.size() == 0){
                    g.setMode(false, player, map, null);
                }else{  
                    g.setMode(false, player, map, chasers.get(0));
                }
                
            }
            g.move();

            if (!g.getIsInvisible()){
                g.draw(app);
            }
            
            
        }
        return false;
    }

}