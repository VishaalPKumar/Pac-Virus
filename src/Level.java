

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author vishaalkumar
 * 
 * The class for the Level
 */
public class Level {

    //This field stores the map in a 2D array
    //0 - Corresponds to a tile
    //1 - Corresponds to a move-able area
    //2 - Start location of the doctor
    //3 - Start location for the enemies (Easy or Hard)
    private int[][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, 
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, 
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 0}, 
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0}, 
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0}, 
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0}, 
            {0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 0}, 
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0}, 
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0}, 
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0}, 
            {0, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 0}, 
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, 
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private Tile[][] tiles;

    private List<Apples> apples;

    private List<EnemySprite> enemies;

    private Random randomGenerator;

    Doctor doctor;

    //Level constructor
    public Level(Doctor doctor) {
        randomGenerator = new Random();
        this.doctor = doctor;
        apples = new ArrayList<>();
        enemies = new ArrayList<>();
        tiles = new Tile[15][15];
        //Drawing out the map based on the values in the map array
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    //Tile              
                    tiles[i][j] = new Tile(i * 48,j * 48);
                } else if (map[i][j] == 2) {
                    //Doctor
                    doctor.x = i * 48;
                    doctor.y = i * 48;
                } else if (map[i][j] == 3) {
                    //Enemy 
                    //randomly assigned as Hard or Easy
                    int rand = randomGenerator.nextInt(2);
                    if (rand == 0) {
                        enemies.add(new HardEnemy(i * 48,j * 48));
                    } else {
                        enemies.add(new EasyEnemy(i * 48,j * 48));
                    }
                } else {
                    apples.add(new Apples(i * 48, j * 48));
                }
            }
        }
    }

    //Level logic
    public void tick(GameCourt gameCourt) {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).tick(doctor, gameCourt);
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (tiles[i][j] != null) {
                    tiles[i][j].render(g);
                }
            }
        }
        for (int i = 0; i < apples.size(); i++) {
            apples.get(i).render(g);
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(g);
        }
    }


    /**
     * @return the tiles
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * @return the apples
     */
    public List<Apples> getApples() {
        return apples;
    }

    /**
     * @return the enemies
     */
    public List<EnemySprite> getEnemies() {
        return enemies;
    }

    /**
     * @return the number of tiles that are walls
     */
    public int numberOfWallTiles() {
        int counter = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (tiles[i][j] != null) {
                    counter++;
                }
            }   
        }
        return counter;
    }

    /**
     * @return the number of enemies
     */
    public int numberOfEnemies() {
        return getEnemies().size();
    }

    /**
     * @return the number of apples
     */
    public int numberOfApples() {
        return getApples().size();

    }



}
