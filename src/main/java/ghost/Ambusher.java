package ghost;

import processing.core.PImage;

/**
 * A kind of ghost that inherits the class Chaser.
 */
public class Ambusher extends Chaser {

    /**
     * Constructs a new Ambusher object. The instance stores the parameters as its own properties.
     * @param x the x-axis position
     * @param y the y-axis position
     * @param ghost image of ambusher
     * @param frightened image of frightened ambusher
     * @param speed the speed of ambusher
     */
    public Ambusher(int x, int y, PImage ghost, PImage frightened, int speed) {
        super(x, y, ghost, frightened, speed);
    }

    @Override
    public void updateTarget(boolean isScatter, Player player, Chaser chaser){
        // here we do not use chaser, only whim use
        if (player == null){
            return;
        }
        
        int playerX = player.getX();
        int playerY = player.getY();

        if (isScatter){
            this.target = new int[]{28 * 16, 0};
        }else{
            if (player.getDirection() == 0){
                this.target[0] = playerX;
                this.target[1] = playerY - 4 * 16;
            }else if (player.getDirection() == 1){
                this.target[0] = playerX - 4 * 16;
                this.target[1] = playerY;
            }else if (player.getDirection() == 2){
                this.target[0] = playerX + 4 * 16;
                this.target[1] = playerY;
            }else if (player.getDirection() == 3){
                this.target[0] = playerX;
                this.target[1] = playerY + 4 * 16;
            }
        }
        checkTarget();
    }
    
}