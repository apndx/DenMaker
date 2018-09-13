# Week 2

[Hour list](https://github.com/apndx/DenMaker/blob/master/Documentation/hours.md)


## What has happened during week 2

This week I started the actual code for my project. There are two packages, one for UI and one for domain.

First I made a simple text-UI, that is created started from a seperate main-class. The UI gets a scanner as a parameter, and a new logic is also created for the UI.

I made a domain package and started to put together some tools for maze creation.

First I made a Tile class that represents one square in the dungeon. Tile has a content (wall, floor or door), and a random weight.

Then I made a Room class, a Room has measurements and starting coordinates that are all created with a random generator.

Then there is an Area class that has measurements (height, width) and an array of Tiles. I made two constructors, first is for a default area, and another takes parametres for the height and width.

Also there is a Roombuilder class, which has an attribute of the area. Roombuilder can check if the rooms fit on the area and do not collide. Also it can take a parametre of room adding attempts and add try adding rooms to the area accordingly.

Then there is the Logic class that is working in between the different tools and the text-UI. Logic has the area that is worked on and a roombuilder. It can change the area, ask roombuilder to add rooms, and draw the area.

The text-UI asks the user parametres for the area measurements, and also the amount of room attempts. After that the area is drawn with the rooms.

I also started making tests for the Logic and RoomBUilder.


### What did I learn?
 

### Problems

I'm not sure if first creating the default area and then changing it is a good idea, but that was the workaround I came up with the problem of:

creating UI(has to have logic) -> creating logic -> logic has to had an area and a roombuilder -> roombuilder need an area -> area needs measurements that are given as parameters that are asked in the UI

### Questions

If there are any change suggestions about the structure of this program, they would be greatly appreciated.

I'm not sure where I should create the random generator, now both tile and room have their own randoms.

### Next week


