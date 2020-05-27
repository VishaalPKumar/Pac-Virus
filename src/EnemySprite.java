/**
 * 
 */


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Abstract class that defines the main functionality of
 * an EnemySprite- There are two types of Enemies: Easy and Hard
 * The class extends the GameObj class
 * @author vishaalkumar
 *
 */
@SuppressWarnings("serial")
public abstract class EnemySprite extends GameObj {

    private static final String IMG_FILE = "files/enemies.png";
    private static BufferedImage img;
    
    
    //Constructor for an EnemySprite
    public EnemySprite(int px, int py) {
        super(px, py);
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    
    //Logic of the Enemy
    public abstract void tick(Doctor doctor, GameCourt gameCourt);

    //Can enemy move
    public boolean canMove(int nextX, int nextY, GameCourt gameCourt) {
        //If there is a tile, then the sprite cannot move!
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
    
    @Override
    public void render(Graphics g) {
        g.drawImage(img, x, y, 48, 48, null);

    }
    
    
}
