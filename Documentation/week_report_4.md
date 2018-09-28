# Week 4

[Hour list](https://github.com/apndx/DenMaker/blob/master/Documentation/hours.md)


## What has happened during week 4

This week I seem to be struggling with one-off problems. I fixed the asymmetrical area by forcing the area parameters to be always odd numbers. Also now the roomwalls are regocnised, as I made them differ from the corridor and room content. At the same time stored the wall tiles to use them for opening entrances for the rooms. I also made a maze trimming algorithm that trims the parts of the maze that lead nowhere. 

My entrance creating method sometimes left some isolated rooms, when both two rooms that are next to each other open entrances in between them. I tried to avoid this problem by preferring the corridor entrances. This does not yet work perfectly, but well enough that I try to concentrate on other things now.

I also made some fixes to the maze algorithm, now it fills the area better even if the area is crowded with rooms. 

I started working on testing document and implementation document, mainly getting the idea of their outline.

I also started working on my own array list, as it is my most used data structure in almost every class. I also removed a set datastructure from the program, as it was only used in one method and was easy to replace with an array list.

### What did I learn?
 
It is still quite hard to get my head around the diffecences of coordinates, array indexes and length of things, but I think I'm slowly getting there. While doing tests I found several bugs that I had not noticed before, if only I had done these tests sooner had I spared a lot of effort elsewhere.

### Problems

At the moment my code is full of copy paste, I need to start doing a lot of refactoring and tidying. I am also still not quite convinced that the entrance algorithm works as it should.

### Next week

I will continue working on my own datastructures and start doing tests on the performance. I am also looking forward to get a glimpse of a project someone else has done.

