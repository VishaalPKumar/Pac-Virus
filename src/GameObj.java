

import java.awt.Graphics;
import java.awt.Rectangle;

/** 
 * An object in the game. 
 *
 * Game objects exist in the game court. They have a position, and a 
 * default constructor and a render method that each Game object must implement
 */

@SuppressWarnings("serial")
public abstract class GameObj extends Rectangle {
    
    /*
     * Current position of the object (in terms of graphics coordinates)
     */
    private int px; 
    private int py;

    /**
     * Constructor
     */
    public GameObj(int px, int py) {
        setBounds(px,py,48,48);
        this.px = px;
        this.py = py;
    }

    /*** GETTERS **********************************************************************************/
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }
    

    /*** SETTERS **********************************************************************************/
    public void setPx(int px) {
        this.px = px;
    }

    public void setPy(int py) {
        this.py = py;
    }


    public abstract void render(Graphics g);
}