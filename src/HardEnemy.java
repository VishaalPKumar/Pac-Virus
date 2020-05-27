
/**
 * This is the class for the more difficult enemies
 * These extend the EnemySprite class and are different from
 * normal enemies - they move slower but are smart!
 * They are able to follow the doctor around. 
 * @author vishaalkumar
 *
 */
@SuppressWarnings("serial")
public class HardEnemy extends EnemySprite {

    private EnemyState state = EnemyState.SMART;
    private int lastDir = -1;
    
    //State of the current direction of movement
    private int right = 0;
    private int left = 1;
    private int up = 2;
    private int down = 3;
    
    private int speed = 2;


    public HardEnemy(int px, int py) {
        super(px, py);
    }

    @Override
    public void tick(Doctor doctor, GameCourt gameCourt) {
        int docX = doctor.x;
        int docY = doctor.y;

        if (state == EnemyState.SMART) {
            //This is the code for the AI of the Enemy
            //Simply follows the doctor wherever it goes. 

            boolean move = false; //If stuck, will move in the last direction that was stored
            if (x < docX) {
                if (canMove(x + speed, y, gameCourt)) {
                    x += speed;
                    move = true;
                    lastDir = right;
                }
            }
            if (x > docX) {
                if (canMove(x - speed, y, gameCourt)) {
                    x -= speed;
                    move = true;
                    lastDir = left;
                }
            }
            if (y < docY) {
                if (canMove(x, y + speed, gameCourt)) {
                    y += speed;
                    move = true;
                    lastDir = down;
                }
            }

            if (y > docY) {
                if (canMove(x, y - speed, gameCourt)) {
                    y -= speed;
                    move = true;
                    lastDir = up;
                }
            }

            if (x == docX && y == docY) {
                move = true;
            }
            if (!move) {
                //This is a special enemy state:
                //When the enemy is stuck while following the player, it will move 
                //in the last stored direction
                state = EnemyState.PATH;
            }
        } else if (state == EnemyState.PATH) {
            //Continue to move in the last known direction if stuck
            if (lastDir == right) {
                if (y < docY) {
                    if (canMove(x, y + speed, gameCourt)) {
                        y += speed;
                        state = EnemyState.SMART;
                    }
                } else {
                    if (canMove(x, y - speed, gameCourt)) {
                        y -= speed;
                        state = EnemyState.SMART;
                    }
                }
                if (canMove(x + speed, y, gameCourt)) {
                    x += speed;                     
                }
            } else if (lastDir == left) {
                if (y < docY) {
                    if (canMove(x, y + speed, gameCourt)) {
                        y += speed;
                        state = EnemyState.SMART;
                    }
                } else {
                    if (canMove(x, y - speed, gameCourt)) {
                        y -= speed;
                        state = EnemyState.SMART;
                    }
                }
                if (canMove(x - speed, y, gameCourt)) {
                    x -= speed;                     
                }
            } else if (lastDir == up) {
                if (x < docX) {
                    if (canMove(x + speed, y, gameCourt)) {
                        x += speed;
                        state = EnemyState.SMART;
                    }
                } else {
                    if (canMove(x - speed,y, gameCourt)) {
                        x -= speed;
                        state = EnemyState.SMART;
                    }
                }
                if (canMove(x,y - speed, gameCourt)) {
                    y -= speed;                     
                }
            } else if (lastDir == down) {
                if (x < docX) {
                    if (canMove(x + speed, y, gameCourt)) {
                        x += speed;
                        state = EnemyState.SMART;
                    }
                } else {
                    if (canMove(x - speed,y, gameCourt)) {
                        x -= speed;
                        state = EnemyState.SMART;
                    }
                }

                if (canMove(x, y + speed, gameCourt)) {
                    y += speed;                     
                }

            }

        }
    }

}
