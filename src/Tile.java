

import java.awt.Color;
import java.awt.Graphics;

//Class that is used to create an instance of the Tile object
//A Tile are the places where the objects cannot move

@SuppressWarnings("serial")
public class Tile extends GameObj {
       
    public Tile(int px, int py) {
        super(px, py);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y, width, height);
    }

}
