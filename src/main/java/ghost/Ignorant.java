package ghost;

import processing.core.PImage;


/**
 * A kind of ghost that inherits the class Chaser.
 */
public class Ignorant extends Chaser {

    /**
     * Constructs a new Ignorant object. The instance stores the parameters as its own properties.
     * @param x the x-axis position
     * @param y the y-axis position
     * @param ghost image of Ignorant
     * @param frightened image of frightened Ignorant
     * @param speed the speed of Ignorant
     */
    public Ignorant(int x, int y, PImage ghost, PImage frightened, int speed) {
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
            this.target = new int[]{0, 35 * 16};
        }else{
            double distanceFromWaka = calculateDistance(this.x, this.y, playerX, playerY);
            if (distanceFromWaka >= Math.sqrt(2) * 8 * 16){
                this.target[0] = playerX;
                this.target[1] = playerY;
            }else{
                this.target = new int[]{0, 35 * 16};
            }
        }
        checkTarget();
    }
    
}