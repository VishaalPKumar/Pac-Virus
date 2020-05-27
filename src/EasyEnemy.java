
import java.util.Random;

/**
 * @author vishaalkumar
 *
 */
@SuppressWarnings("serial")
public class EasyEnemy extends EnemySprite {
    
    private int dir = -1;
    private Random randomGenerator; // the random number generator for movement
    
    //State of the current direction of movement
    private int right = 0;
    private int left = 1;
    private int up = 2;
    private int down = 3;
    
    private int speed = 3;

    

    public EasyEnemy(int px, int py) {
        super(px, py);
        randomGenerator = new Random();
        dir = randomGenerator.nextInt(4);
    }

    @Override
    public void tick(Doctor doctor, GameCourt gameCourt) {
        if (dir == right) {
            if (canMove(x + speed, y, gameCourt)) {
                x += speed;
            } else {
                dir = randomGenerator.nextInt(4);
            }
        } else if (dir == left) {
            if (canMove(x - speed, y, gameCourt)) {
                x -= speed;
            } else {
                dir = randomGenerator.nextInt(4);
            }
        } else if (dir == up) {
            if (canMove(x, y - speed, gameCourt)) {
                y -= speed;
            } else {
                dir = randomGenerator.nextInt(4);
            }
        } else if (dir == down) {
            if (canMove(x, y + speed, gameCourt)) {
                y += speed;
            } else {
                dir = randomGenerator.nextInt(4);
            }
        }     
    }



    
}
