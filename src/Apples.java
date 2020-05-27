

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Class to represent the apples in Pacman
//In this case, the apples are replaced with hearts

@SuppressWarnings("serial")
public class Apples extends GameObj {

    private static final String IMG_FILE = "files/heart.png";

    private static BufferedImage img;

    //Constructor
    public Apples(int px, int py) {
        super(px,py);

        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, x + 18, y + 18, 12, 12, null);
    }

}
