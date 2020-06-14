# Pac-Virus
Developed a custom version of Pac-Man using the Java Swing GUI framework that commemorates the work of health workers during the COVID19 pandemic. Utilized inheritance, complex data structuresand created an AI for the movement of enemy sprites. This project was used as my final submission for CIS 120 - Programming Language & Techniques

Score Recieved - 100/100

# Core Concepts Implemented :-
* 2D Arrays:-
  I will be using a 2-D array to represent the map of the game which can be represented by a grid of squares. 
  I plan to use a 2-D array of integers where each element stores a particular state of each square. 
  The following integers will represent:
    * 0 - Corresponds to a tile
    * 1 - Corresponds to a move-able area
    * 2 - Start location of the doctor
    * 3 - Start location for the enemies (Easy or Hard)
* Collections:-
  I will be using a TreeMap that stores the leader board of high-scores. Whenever the user decides to quit the game.
  They will be prompted to enter their name and their high score will be stored in a TreeMap where the 
  key will be their name and their value will be their high score. I decided to make the key their name so that the 
  same user can play the game multiple times and their highest score will be stored in the leader board once. 
  The leader board shows up in a pop-up window when the user quits the game. I have also used File I/O to store this 
  leader board in a text file called "highscores.txt"
* Inheritance/Sub-typing for Dynamic Dispatch:-
  
  I have used the GameObj class as the super class where all the game objects inherit from. The game objects are as follows:-
    1. Doctor
    2. Enemies
    3. Tiles
    4. Apples
  
  Furthermore I have created an EnemySprite abstract class that defines how an Enemy Object behaves.
  The EnemySprite abstract class itself also extends the GameObj class. 
  Since, there are 2 types of enemies - EasyEnemy and Hard Enemy, they both extend the EnemySprite abstract class. 
  The EnemySprite abstract class defines the required functions and fields for an Enemy object such as rendering, movement, logic, speed, directionality, etc.
  Then the EasyEnemy and HardEnemy objects will have different abilities - different speeds, movement patterns, etc.
  I have overridden their movement patterns accordingly to suit their difficulty class. 
  
# Images

