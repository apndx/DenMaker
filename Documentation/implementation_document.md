# Project implementation

The project has followed the [initial plan](https://github.com/apndx/DenMaker/blob/master/Documentation/design_document.md) quite nicely.

The user can choose in the main manu either to create a Den with a print out of the result, or test algorithm performance times. Den-making phases: 

1. First there is an area that is all solid, user can give width and height as parametres
2. Random rooms are added, user can give amount of room adding attempts as a parameter
3. Solid matter is filled with a maze, the algoritm for this is a variation of a Prim
4. Rooms are connected to the den
5. Dead ends are trimmed

## Package structure

The program consist of three parts:

1. Datastructures 
2. Domain
3. UI

## UML-graph

[The structure of the program](https://github.com/apndx/DenMaker/blob/master/Documentation/UML_attributes.png)


### Datastructures

1. OwnArrayList - used in almost every class to some extent

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


### Analyse of the algorithms

After benchmarking I decided to see how the results compare with an analysis of the code.


#### RoomBuilder

* Affecting parametres: how many room adding attempts
* Methods used: RoomBuilder.addRooms()

For every room the algorithm creates a room, checks all the coordinates within the room, mostly 13 x 20 = 260. If the area is free, all the coordinates are changed so that they make a room, again max 260. After this the surrounding walls are changed to walltiles, this takes max 2 x 18 + 2 x 11 = 58 rows. The other rows make around 20 lines, so all together the requirement is around around 600 codelines / room.

Every room has the same maximum, so this makes the time requirement linear, O(n). Also the space requirement is linear O(n), where the n is the amount of rooms that pass the test as suitable one. The rooms are saved in a list, and each room tile is checked. The roombuilder also has the area which it gets from the logic, and after adding the rooms returns the new version to logic. Area object is the place, where the list of added rooms is stored as an arraylist and the tiles are also stored in the area as an twodimensional array. Each of these take linearly more space as the amounts grow.

#### MazeBuilder

* Affecting parametres: how many Tiles in the Den Area
* Methods used: MazeBuilder.emptyFinder(), MazeBuilder.build(), tile.checkOpposite(), Area.solidifyWalls()

In the worst scenario emptyFinder method (finds the starting point of the maze) will have to check every tile of the area, so it has a time requirement on O(n).

The mazebuilding starts finding neighbor tiles, each tile has maximum of four neighbors. The neighbors are stored in an OwnArraylist, which (usually) takes a costant time O(1). After that a random Tile is chosen and removed from OwnArrayList, and in the worst scenario all neighbors on the list need to be moved to the left. A Tile facing the chosen neighbor is found next in constant time. If some conditions are met, there are a few rows of operations, and again a neighbor search, again new neighbors are added to the list if they are still solid. 

In the end wall sodifying changes the Tile content of every room wall Tile, this takes max 58 code rows /room, so the time requirement for this is linear O(n).

When we calculate all these parts together, we get O(n) + O(m + 4n2n) (this is about the average amount of neighbors on the list) + O(n), and this makes the time requirement O(n^2).

For a regular Prim algorithm the time requirement is O(n + m log n) where n is the amount of tiles and m the amount of edges, and the data structure in use is a heap.

I think my algoritghm is slowed down by OwnArrayList, and on a hindsight I really should have implemented a heap to do the job here.

The Mazebuilder requires space for its neighbor lists, which can be four times the amount of tiles of the area, which makes it linear O(n) where the n is the amount of tiles. Mazebuilder makes the maze building search in the area, which it gets from the logic where it again returns it after the maze is built. 

#### Opening the entrances in Area 

* Affecting parametres: amount of rooms on the area
* Methods used: Area.outOfTheBox() 

This method tries to find an entrance for each room. 

In the worst scenario, the method checks each room wall tile once, and for corner tiles two times. This makes the method time requirement linear O(n) where the parameter is the amount of rooms in the area. This seems to be in correlation with the tests, after the unefficient regionc ombiner has been removed.

As space requirement goes, outOfTheBox -method uses the roomList of the area and makes a new Tile list of potentia entrances. The size of this debends of the amount of rooms, and is max 58 tiles / room. This makes the space requirement linear O(n) where the n is the amount of rooms.

#### Trimming the maze

* Affecting parametres: Tile amount
* Methods used: MazeBuilder.deadEndTrimmer(), MazeBuilder.deadEndHelper()

The method goes through all tiles of the area. If the tile has four or three solid tiles surrounding it, it is a dead end. Then deadends are removed recursively, until there come's an tile, that has less than three solid blocks surrounding it.

The time requirement is quite hard to grasp, as the operations in the while loop can take quite many rounds and happen quite often debending of the structure of the maze, and it is only done when a dead end is found. However, the benchmark testing seems to suggest that the performance of this algorith is near linear, so I guess these operations are rare enough. O(n) it might be then.

Space requirement for trimming is O(1), as this method does not need to save other than a couple of tiles and variables.


### Useful functions for a project clone

#### Creating jar from the project

Command:

```
mvn package
```

Generates to the _target_ forder a jar -file  _DenMaker-1.0-SNAPSHOT.jar_


#### JavaDoc

JavaDoc can be generated with a command:

```
mvn javadoc:javadoc
```

After this the JavaDoc can be browsed from the file _target/site/apidocs/index.html_


## Improvement suggestions

At the moment some rooms may remain isolated, this is more likely to happen in a very crowded area where there are a lot of rooms next to each other. It would be good to properly address this, and for example make a union find structure to guarantee the integrity of the den. Some of the den functions could be seperated as their own classes, now it is a bit messy where everything happens. Also the unit testing could be improved.


## Sources

* [Rooms and Mazes](http://journal.stuffwithstuff.com/2014/12/21/rooms-and-mazes/)
* [Wikipedia](https://en.wikipedia.org/wiki/Maze_generation_algorithm)
* [Tirakirja](https://github.com/pllk/tirakirja/blob/master/tirakirja.pdf) by Antti Laaksonen
* Course material by Jyrki Kivinen for Datastructures and algorithms course Spring 2018
* [Maze Generation with Prim's Algorithm](http://jonathanzong.com/blog/2012/11/06/maze-generation-with-prims-algorithm)
* [Random Generation](https://crypto.stackexchange.com/questions/51686/how-to-determine-the-next-number-from-javas-random-method)

