=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: vishaalk
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the three core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays:-
  I will be using a 2-D array to represent the map of the game which can be represented by a grid of squares. 
  I plan to use a 2-D array of integers where each element stores a particular state of each square. 
  The following integers will represent:
    0 - Corresponds to a tile
    1 - Corresponds to a move-able area
    2 - Start location of the doctor
    3 - Start location for the enemies (Easy or Hard)


  2. Collections:-
  I will be using a TreeMap that stores the leader board of high-scores. Whenever the user decides to quit the game.
  They will be prompted to enter their name and their high score will be stored in a TreeMap where the 
  key will be their name and their value will be their high score. I decided to make the key their name so that the 
  same user can play the game multiple times and their highest score will be stored in the leader board once. 
  The leader board shows up in a pop-up window when the user quits the game. I have also used File I/O to store this 
  leader board in a text file called "highscores.txt"
  

  3. Inheritance/Sub-typing for Dynamic Dispatch:-
  
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
  

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  1. Apples.java - Class to represent the apples in Pacman. In this case, the apples are replaced with hearts
  				   to suit the main theme of the game. The class extends the GameObj super class. 
  
  2. Doctor.java - Class for the main character of the game. The class contains the all the fields and logic
  				   of a Doctor object. The class extends the GameObj super class. 
  
  3. EnemySprite.java - An Abstract class that defines how an Enemy Object behaves. Has abstract methods that describes the
  						the movement logic each type of Enemy must implement. The class extends the GameObj super class. 
  
  4. EasyEnemy.java - This class extends the EnemySprite abstract class. Used to create easy enemies.
  
  5. HardEnemy.java -  This is the class for the more difficult enemies.These extend the EnemySprite class and 
  					   are different from normal enemies - they move slower but are smart. They are able to follow 
  					   the doctor around ( a form of AI I have implemented)

  6. EnemyState.java - an enumeration for the current state of the enemy object. makes it easier to represent state
  					   instead of using integers
  
  7. Game.java - This is the class where the game is run. It also contains the code for File I/O and high-scores.
  
  8. GameCourt.java - This class holds the primary game logic for how different objects interact with one another.
  
  9. GameState.java - Enumeration for the current state of the game
  
  10. Level.java - The class for the Level. Responsible for populating the map - creating the walls, enemies, the doctor, etc.
   
  11. Tile.java - Class that is used to create an instance of the Tile object.
  				  A Tile are the places where the objects cannot move
  
  12. GameObj.java - Game objects exist in the game court. They have a position, and a 
  					 default constructor and a render method that each Game object must implement. 
  


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  Nothing specifically. I think formulating a proposal before starting really helped me to plan out my game. 
  Furthermore the planning I needed to do for hw07 actually helped me to plan out this game. 


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I like my design. I feel that I have created multiple classes that each implement a certain independent functionality well. 
  I do not have any public fields and all the fields are properly encapsulated. 



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  I have used Google Images to get images for the hearts, doctor and enemies. 
  I used Canva to design the Win screen, Losing screen, Intro screen, and How to play screen. 
