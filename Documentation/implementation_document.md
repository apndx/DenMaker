# Project implementation

The project has followed quite well the [initial plan](https://github.com/apndx/DenMaker/blob/master/Documentation/design_document.md)

The user can choose either a denmaking with drawing, or test option in the main manu.
Den-making phases: 

1. First there is an area that is all solid, user can give width and height as parametres
2. Random rooms are added, user can give amount of room adding attempts as a parameter
3. Solid matter is filled with a maze, the algoright for this is a variation of a Prim
4. Rooms are connected to the dungeon
5. Dead ends are trimmed

## Package structure

The program consist of three parts:

1. Datastructures
2. Domain
3. UI

### Datastructures

1. ArrayList - used in almost every class to some extent (home made implementation still under construction)
2. Random Generator - used to determine room sizes, and to which direction to continue the maze (home made implementation still under construction)

Minimum heap nor a queue were not needed as I first had planned. An arraylist with randomness was used to replace these.


### Domain

1. Tile 
2. Room
3. Area
4. Logic
5. Roombuilder
6. Mazebuilder
  
### UI

1. Main - starts the program textUI 
2. TextUI - Menu options: 1. Create a new Den, 2. Test mode, 3. Quit

# Algorithms



# Complexity and performance



# Improvement suggestions

At the moment some rooms may remain isolated, this can happen when the amount of rooms in the dungeon increases but area size remains the same.

# Sources

* [Rooms and Mazes](http://journal.stuffwithstuff.com/2014/12/21/rooms-and-mazes/)
* [Wikipedia](https://en.wikipedia.org/wiki/Maze_generation_algorithm)
* [Tirakirja](https://github.com/pllk/tirakirja/blob/master/tirakirja.pdf) by Antti Laaksonen
* Course material by Jyrki Kivinen for Datastructures and algorithms course Spring 2018
* [Maze Generation with Prim's Algorithm](http://jonathanzong.com/blog/2012/11/06/maze-generation-with-prims-algorithm)


