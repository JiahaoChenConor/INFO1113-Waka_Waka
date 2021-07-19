package ghost;


import processing.core.PImage;

/**
 * 
 * A kind of ghost that inherits the class Chaser.
 */
public class Whim extends Chaser {

    /**
     * Constructs a new Whim object. The instance stores the parameters as its own properties.
     * @param x the x-axis position
     * @param y the y-axis position
     * @param ghost image of Whim
     * @param frightened image of frightened Whim
     * @param speed the speed of Whim
     */
    public Whim(int x, int y, PImage ghost, PImage frightened, int speed) {
        super(x, y, ghost, frightened, speed);
    }

    @Override
    public void updateTarget(boolean isScatter, Player player, Chaser chaser){
        if (player == null || chaser == null){
            return;
        }
        if (isScatter){
            this.target = new int[]{28 * 16, 35 * 16};
        }else{
            
            int[] twoSpacesAheadWaka = new int[2];
            
            if (player.getDirection() == 0){
                twoSpacesAheadWaka[0] = player.getX();
                twoSpacesAheadWaka[1] = player.getY() - 16 * 2;
            } else if (player.getDirection() == 1){
                twoSpacesAheadWaka[0] = player.getX() - 16 * 2;
                twoSpacesAheadWaka[1] = player.getY();
            }else if (player.getDirection() == 2){
                twoSpacesAheadWaka[0] = player.getX() + 16 * 2;
                twoSpacesAheadWaka[1] = player.getY();
            }else if (player.getDirection() == 3){
                twoSpacesAheadWaka[0] = player.getX();
                twoSpacesAheadWaka[1] = player.getY() + 16 * 2;
            }

            
            this.target[0] = chaser.getX() + 2 * (twoSpacesAheadWaka[0] - chaser.getX());
            this.target[1] = chaser.getY() + 2 * (twoSpacesAheadWaka[1] - chaser.getY());

            checkTarget();


        }
    }
}