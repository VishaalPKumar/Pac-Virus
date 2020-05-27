

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

//Class that is used to create an instance of the Doctor object
//This is the main character

@SuppressWarnings("serial")
public class Doctor extends GameObj {
    

    private static final String IMG_FILE = "files/doctor.png";
    private static BufferedImage img;
    
    //Fields for the Doctor
    private boolean right,left,up,down;
    private int speed = 4;

    //Constructor
    public Doctor(int px, int py) {
        super(px, py);
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

    }

    //Main logic for the Doctor object - movement, interactions, etc.
    public void tick(GameCourt gameCourt) {
        
        //Movement controls
        if (right && canMove(x + speed, y, gameCourt)) {
            x += speed;          
        }
        if (left && canMove(x - speed, y, gameCourt)) {
            x -= speed;          
        }
        if (up && canMove(x, y - speed, gameCourt)) {
            y -= speed;          
        }
        if (down && canMove(x, y + speed, gameCourt)) {
            y += speed;          
        }

        Level level = gameCourt.getLevel();
        List<Apples> apples = level.getApples();
        List<EnemySprite> enemies = level.getEnemies();
        
        //Score tracking and collision with enemies

        int highScore = gameCourt.getHighScore();
        
        
        for (int i = 0; i < apples.size(); i++) {
            if (this.intersects(apples.get(i))) {
                apples.remove(i);
                gameCourt.incrementScore();
                gameCourt.setStatus("Highscore: " + highScore + 
                        "     Current Score: " + gameCourt.getScore());
                break;
            }
        }
        if (apples.size() == 0) {
            gameCourt.setGameState(GameState.WON);
        }
        for (int i = 0; i < enemies.size();i++) {
            EnemySprite enemy = enemies.get(i);
            if (enemy.intersects(this)) {
                gameCourt.setGameState(GameState.LOST);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, x + 6, y + 6, 36, 36, null);

    }

    //Helper method to check if the doctor can move. 
    private boolean canMove(int nextX, int nextY, GameCourt gameCourt) {
        Rectangle bounds = new Rectangle(nextX, nextY, width, height);
        Level level = gameCourt.getLevel();
        Tile[][] tiles = level.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != null) {
                    if (bounds.intersects(tiles[i][j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @return Check if doctor is moving right
     */
    public boolean isRight() {
        return right;
    }

    /**
     * @param right Setting the state of the doctor's movement
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * @return Check if doctor is moving left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * @param left Setting the state of the doctor's movement
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * @return Check if doctor is moving up
     */
    public boolean isUp() {
        return up;
    }

    /**
     * @param up Setting the state of the doctor's movement
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * @return Check if doctor is moving down
     */
    public boolean isDown() {
        return down;
    }

    /**
     * @param down Setting the state of the doctor's movement
     */
    public void setDown(boolean down) {
        this.down = down;
    }





}
