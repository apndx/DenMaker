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

## UML-graph

[The structure of the program](https://github.com/apndx/DenMaker/blob/master/Documentation/UML_attributes.png)


### Datastructures

1. ArrayList - used in almost every class to some extent

Minimum heap nor a queue were not needed as I first had planned. An arraylist with randomness was used to replace these. Also the Java random generator was used in the end instead of a home made version.


### Domain

The domain package contains the building blocks and tools for den making.

1. Tile - Each Area coordinate has a Tile, that has content, x- and y-coordinates, a parent (null at first) and region
2. Room - Room has height, width, starty and startx (up-left-corner of the room) and a list of roomwalls.
3. Area - Area has height, width, two dimensional Array of tiles, list of rooms, how many separete roomregions there are and a list or performance values about the creation process.
4. Logic - Logic has an Area, RoomBuilder, Mazebuilder and a Benchmark, and is generally working between the userinterface and other parts of the program.
5. Roombuilder - has the Area that is under construction. Function of the class is to add the rooms to the area.
6. Mazebuilder - has the Area that is under construction, also current coordinates and mazeregion.
7. Benchmark - has the logic, as it is needed for testing all the functions. Also has lists form testresults lists, and average performances. 
  
### UI

1. Main - starts the program textUI 
2. TextUI - Menu options: 1. Create a new Den, 2. Test mode, 3. Quit


### JavaDoc

JavaDoc can be generated with a command:

```
mvn javadoc:javadoc
```

After this the JavaDoc can be browsed from the file _target/site/apidocs/index.html_


# Algorithms



# Complexity and performance



# Improvement suggestions

At the moment some rooms may remain isolated, this can happen when the amount of rooms in the dungeon increases but area size remains the same.


### Creating jar from the project
Command:

```
mvn package
```

Generates to the _target_ forder a jar -file  _DenMaker-1.0-SNAPSHOT.jar_



# Sources

* [Rooms and Mazes](http://journal.stuffwithstuff.com/2014/12/21/rooms-and-mazes/)
* [Wikipedia](https://en.wikipedia.org/wiki/Maze_generation_algorithm)
* [Tirakirja](https://github.com/pllk/tirakirja/blob/master/tirakirja.pdf) by Antti Laaksonen
* Course material by Jyrki Kivinen for Datastructures and algorithms course Spring 2018
* [Maze Generation with Prim's Algorithm](http://jonathanzong.com/blog/2012/11/06/maze-generation-with-prims-algorithm)
* [Random Generation](https://crypto.stackexchange.com/questions/51686/how-to-determine-the-next-number-from-javas-random-method)

