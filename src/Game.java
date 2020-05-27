import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This is the class where the game is run
 * It also contains the code for File I/O and high-scores.
 * @author vishaalkumar
 *
 */


public class Game implements Runnable {

    BufferedReader read;
    BufferedWriter writer;


    public void run() {

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("PAC-VIRUS");


        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);

        // Control Panel
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // How to play button
        final JButton instructions = new JButton("How to Play?");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
                court.setGameState(GameState.INSTRUCTIONS);             
            }
        });
        control_panel.add(instructions);

        // Reset Button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();               
                court.setGameState(GameState.RUNNING);
            }
        });
        control_panel.add(reset);

        //Quit Game Button
        final JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
                Map<String, Integer> scoreChart = new TreeMap<String, Integer>();
                if (court.getHighScore() != 0) {

                    //Prompt to store high score
                    int highScore = court.getHighScore();
                    String name = JOptionPane.showInputDialog("Enter your name to store"
                            + " your high score!");

                    if (name !=  null && name.trim() != "") {
                        //Populating TreeMap
                        try {
                            read = new BufferedReader(new FileReader("highscore.txt")); 
                            for (String scoreString = read.readLine(); 
                                    scoreString != null; scoreString = read.readLine()) {
                                scoreString = scoreString.trim();
                                String[] split = scoreString.split(" ");
                                scoreChart.put(split[0], Integer.parseInt(split[1]));
                            }


                        }  catch (IOException ioException) {
                            System.out.println(ioException.getMessage()); 
                        }

                        //Adding high score
                        //If user has already played the game then only update score
                        //if current high score is greater than old high score
                        if (scoreChart.containsKey(name)) {
                            if (highScore > scoreChart.get(name)) {
                                scoreChart.put(name, highScore);
                            }
                        } else {
                            scoreChart.put(name, highScore);
                        }
                    }

                    //Write the new scoreChart back to the txt file
                    try {
                        writer = new BufferedWriter(new FileWriter("highscore.txt"));
                        for (Entry<String, Integer> entry : scoreChart.entrySet()) {
                            String output = entry.getKey() + " " + entry.getValue();
                            writer.write(output + "\n"); 
                        }
                        writer.close(); 
                    } catch (IOException ioException) {
                        System.out.println(ioException.getMessage());
                    }
                }
                //Displaying table of high scores
                if (!scoreChart.isEmpty()) {
                    TreeMap<String, Integer> sortedMap = 
                            new TreeMap<String, Integer>(new MapValueComparator(scoreChart));
                    sortedMap.putAll(scoreChart);
                    int counter = 1;
                    String display = "High Score Chart :- \n";
                    for (Entry<String, Integer> entry : sortedMap.entrySet()) {
                        String displayLine = counter + ". " + 
                                entry.getKey() + " - " + entry.getValue();
                        display = display + "\n" + displayLine;
                        counter++;
                    }
                    JOptionPane.showMessageDialog(null, display);                  
                }

                System.exit(0);

            }
        });
        control_panel.add(quit);

        // Put the frame on the screen

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }

    //inner class that help to sort the TreeMap by value instead of by key.
    //sorts in descending order since it is used to sort the high-score TreeMap.
    class MapValueComparator implements Comparator<String> {

        Map<String, Integer> currentMap;

        public MapValueComparator(Map<String, Integer> map) {
            this.currentMap = map;
        }

        public int compare(String keyA, String keyB) {
            int valueA = currentMap.get(keyA);
            int valueB = currentMap.get(keyB);
            if (valueA < valueB) {
                return 1;
            } else {
                return -1;
            }
        }

    }
}

