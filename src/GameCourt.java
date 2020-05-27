

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another.
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
    private Doctor doctor;
    private Level level;
    private int score;
    private int highScore;

    private boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."

    //    private BufferedReader read; 
    //    private BufferedWriter writer; 

    // Game constants
    public static final int COURT_WIDTH = 720;
    public static final int COURT_HEIGHT = 720;
    public static final int DOCTOR_VELOCITY = 4;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    //Interface variable
    private GameState gameState = GameState.START;

    public GameCourt(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(new Color(45, 220, 133));

        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); 

        // Enable keyboard focus on the court area.
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {


                if (e.getKeyCode() == KeyEvent.VK_ENTER && 
                        gameState == GameState.START) {
                    //If user is on the start screen
                    gameState = GameState.RUNNING;
                    playing = true;
                } else if (gameState == GameState.RUNNING) {
                    //if the game is running, wait for user directions
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        doctor.setLeft(true);
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        doctor.setRight(true);
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        doctor.setDown(true);
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        doctor.setUp(true);
                    }
                } else if (gameState == GameState.LOST) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        //if the game over screen is displayed
                        reset();
                        gameState = GameState.RUNNING;
                        playing = true;
                    }
                } else if (gameState == GameState.WON) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        //if the user has won the game
                        reset();
                        gameState = GameState.RUNNING;
                        playing = true;

                    }
                } else if (gameState == GameState.INSTRUCTIONS) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        //when user is viewing instructions
                        reset();
                        gameState = GameState.RUNNING;
                        playing = true;
                    }
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                    doctor.setLeft(false);

                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

                    doctor.setRight(false);

                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

                    doctor.setDown(false);

                } else if (e.getKeyCode() == KeyEvent.VK_UP) {

                    doctor.setUp(false);

                }
            }
        });

        this.status = status;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {

        if (score > highScore) {
            highScore = score;
        }
        setScore(0);
        doctor = new Doctor(COURT_WIDTH / 2, COURT_HEIGHT / 2);
        level = new Level(doctor);
        playing = true;
        status.setText("Highscore: " + highScore + 
                "     Current Score: " + score);

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
            doctor.tick(this);
            level.tick(this);

            // update the display
            repaint();
        }

    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Set the status of the window to the String text
     */
    public void setStatus(String text) {
        this.status.setText(text);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }


    public void incrementScore() {
        this.score++;
    }
    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the high score
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }



    /**
     * @return the gameState
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * @param gameState the gameState to set
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * paint the introduction screen
     */
    public void paintIntroduction(Graphics g) {
        super.paintComponent(g);

        String imageString = "files/home.png";
        BufferedImage img = null;
        try {
            if (img == null) {
                img = ImageIO.read(new File(imageString));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        g.drawImage(img,0,0,null);
        playing = false;
        status.setText("Start Screen");

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * paint the game over screen
     */
    public void paintGameOver(Graphics g) {
        super.paintComponent(g);

        String imageString = "files/game-over.png";

        BufferedImage img = null;
        try {
            if (img == null) {
                img = ImageIO.read(new File(imageString));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        g.drawImage(img,0,0,null);

        playing = false;
        status.setText("Welcome to Pac-Virus!");

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * paint the instructions screen
     */  
    public void paintIntrucstions(Graphics g) {
        super.paintComponent(g);

        String imageString = "files/instructions.png";

        BufferedImage img = null;
        try {
            if (img == null) {
                img = ImageIO.read(new File(imageString));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        g.drawImage(img,0,0,null);
        playing = false;
        status.setText("How To Play. Press Enter to return back to the game!");

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * paint the win screen
     */  
    public void paintWinScreen(Graphics g) {
        super.paintComponent(g);

        String imageString = "files/win-screen.png";

        BufferedImage img = null;
        try {
            if (img == null) {
                img = ImageIO.read(new File(imageString));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        g.drawImage(img,0,0,null);
        playing = false;
        status.setText("Press Enter to play again!");

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        //if statements to determine which screen to paint
        if (gameState == GameState.START) {
            paintIntroduction(g);
        } else if (gameState == GameState.RUNNING) {
            super.paintComponent(g);
            doctor.render(g);
            level.render(g);
        } else if (gameState == GameState.LOST) {
            paintGameOver(g);        
            status.setText("You Lost, Press Enter to Play Again!");
        } else if (gameState == GameState.INSTRUCTIONS) {
            paintIntrucstions(g);
        } else if (gameState == GameState.WON) {
            paintWinScreen(g);
        }
    }
}
