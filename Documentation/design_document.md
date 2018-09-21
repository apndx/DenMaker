# Design document


## Description

DenMaker creates random dungeons that the user can adjust with some parametres. 

I will use the method described by Bob Nystrom in his [blog entry](http://journal.stuffwithstuff.com/2014/12/21/rooms-and-mazes/). First there is an area that is all solid. Then  random rooms will be created on the area, the amount of room adding attempts can be given by a parameter. After the rooms have been added, the remaining solid matter will be filled with mazes. After this the seperate areas are connected to each other, and the dead ends of the maze are removed.


## Algorithms

Initial ideas for algorithms and estimates for time / space requirements:

* Algorithm for random room creation: time O(n) where n is the amount of tries, space O(1)
* Algorithm for the labyrinth: some kind of randomized Prim could do the job, time O(n + m log n), space: O(n) for all the tiles in the maze + surrounding wall tiles inf a minimum heap 
* Algorithm to create a spanning tree: find tiles that can join two areas that are not yet connected, and make a connection, perhaps some variation of Kruskal, time O(n log n), space O(n)
* Algorithm to trim the dead ends: here we could go through all tiles, and if an empty tile is surrounded from three directions, it is changed into a wall tile, time O(n), space O(1)


## Datastructures

Here are the initial ideas for datastructures needed and initial estimations of their space requirements (n is the amount of tiles on the area). I will start by using the structures offered by Java, and gradually replace them with my own.

* Array to keep track of the tiles - space O(n)
* List for the neighbourgs of each tile - space  O(n)
* Datastructure that keeps track of the seperate areas, a spanning tree -space  O(n)
* Minimum heap for the tiles that have a random weight - space O(n)
* Queue for the Prim - space O(n)


## Input

* User can give parameters for the amount of room creation attempts when starting the dungeon creation


## Output

* I will start by printing the dungeon as ASCII in a text file (Wall matter: #, doors @, caves empty). If time allows, I might look into more advanced graphic implementation 

The resulting dungeon will be demonstrated in seperate files that portray the construction of the dungeon. 

* First: creation of the rooms
* Second: mazes added
* Third: seperate areas are joined
* Fourth: dead ends are removed - final stage


## Sources:

* [Rooms and Mazes](http://journal.stuffwithstuff.com/2014/12/21/rooms-and-mazes/)
* [Wikipedia](https://en.wikipedia.org/wiki/Maze_generation_algorithm)
* [Tirakirja](https://github.com/pllk/tirakirja/blob/master/tirakirja.pdf) by Antti Laaksonen
* Course material by Jyrki Kivinen for Datastructures and algorithms course Spring 2018
* [Maze Generation with Prim's Algorithm](http://jonathanzong.com/blog/2012/11/06/maze-generation-with-prims-algorithm)
