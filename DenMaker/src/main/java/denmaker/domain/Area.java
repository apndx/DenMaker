/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import denmaker.datastructures.OwnArrayList;

/**
 * Makes a new dungeon area There is a constructor for default area of height 50
 * and width 150 and a constructor for an area that takes height and width as
 * parameters
 *
 * @author apndx
 */
public class Area {

    public int areaHeight;
    public int areaWidth;
    public Tile[][] tiles;
    public OwnArrayList<Room> roomList;
    public int seperateRooms;
    public OwnArrayList<Double> performance; // 0 rooms, 1 maze, 2 entrances, 3 trimming

    public Area() {
        this(49, 149);
    }

    public Area(int areaHeight, int areaWidth) {

        this.areaHeight = areaHeight;
        this.areaWidth = areaWidth;
        this.tiles = new Tile[areaHeight][areaWidth];
        this.roomList = new OwnArrayList<>();
        this.performance = new OwnArrayList<>();

        // initialising the array with tiles   
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                tiles[y][x] = new Tile(0, y, x, null);
            }
        }
    }

    /**
     * Changes the wall tiles back to solid form
     *
     */
    public void solidifyWalls() {

        for (int i = 0; i < this.roomList.size(); i++) {
            Room roomtoCheck = this.roomList.get(i);
            for (int j = 0; j < roomtoCheck.roomWalls.size(); j++) {
                Tile toSolidify = roomtoCheck.roomWalls.get(j);
                toSolidify.content = 0;
            }
        }
    }

    /**
     * Finds entrances out of every room that has something to connect to in
     * it's vicinity (no further than one tile apart)
     *
     */
    public void outOfTheBox() {

        for (int i = 0; i < this.roomList.size(); i++) {
            Room toGetOutOf = this.roomList.get(i);
            OwnArrayList<Tile> potentialEntrances = new OwnArrayList<>();

            for (int j = 0; j < toGetOutOf.roomWalls.size(); j++) {
                Tile toMakeEntranceOf = toGetOutOf.roomWalls.get(j);

                int y = toMakeEntranceOf.y;
                int x = toMakeEntranceOf.x;

                try {
                    // do top roomtile have maze behind the wall?
                    if (this.tiles[y + 1][x].content == 1 && this.tiles[y - 1][x].content == 1
                            && this.tiles[y - 1][x].region < 0) {
                        toMakeEntranceOf.region = this.tiles[y - 1][x].region;
                        potentialEntrances.add(toMakeEntranceOf);
                    } // left
                    else if (this.tiles[y][x + 1].content == 1 && this.tiles[y][x - 1].content == 1
                            && this.tiles[y][x - 1].region < 0) {
                        toMakeEntranceOf.region = this.tiles[y][x - 1].region;
                        potentialEntrances.add(toMakeEntranceOf);
                    } // bottom 
                    else if (this.tiles[y + 1][x].content == 1 && this.tiles[y - 1][x].content == 1
                            && this.tiles[y - 1][x].region < 0) {
                        toMakeEntranceOf.region = this.tiles[y - 1][x].region;
                        potentialEntrances.add(toMakeEntranceOf);
                    } // right
                    else if (this.tiles[y][x - 1].content == 1 && this.tiles[y][x + 1].content == 1
                            && this.tiles[y][x + 1].region < 0) {
                        toMakeEntranceOf.region = this.tiles[y][x + 1].region;
                        potentialEntrances.add(toMakeEntranceOf);
                    }
                } catch (Exception e) {
                }

            }

            if (potentialEntrances.size() != 0) {

                Tile entrance = potentialEntrances.get((int) (Math.random() * potentialEntrances.size()));
                entrance.content = 1;

            } else {
                // corner cases, these are checked if the previous ones do not work
                OwnArrayList<Tile> cornerEntrances = new OwnArrayList<>();
                int starty = toGetOutOf.starty;
                int startx = toGetOutOf.startx;

                try {
                    if (this.tiles[starty - 1][startx - 1].content == 1) {
                        //left upper corner               
                        cornerEntrances.add(this.tiles[starty - 1][startx]);
                        cornerEntrances.add(this.tiles[starty][startx - 1]);
                    }
                    if (this.tiles[starty - 1][startx + toGetOutOf.width].content == 1) {
                        //right upper corner                
                        cornerEntrances.add(this.tiles[starty - 1][startx + toGetOutOf.width - 1]);
                        cornerEntrances.add(this.tiles[starty][startx + toGetOutOf.width]);
                    }
                    if (this.tiles[starty + toGetOutOf.height][startx].content == 1) {
                        //left down corner                      
                        cornerEntrances.add(this.tiles[starty + toGetOutOf.height - 1][startx - 1]);
                        cornerEntrances.add(this.tiles[starty + toGetOutOf.height][startx]);
                    }
                    if (this.tiles[starty + toGetOutOf.height][startx + toGetOutOf.width].content == 1) {
                        //right down corner                    
                        cornerEntrances.add(this.tiles[starty + toGetOutOf.height - 1][startx + toGetOutOf.width]);
                        cornerEntrances.add(this.tiles[starty + toGetOutOf.height][startx + toGetOutOf.width - 1]);
                    }
                } catch (Exception e) {
                }

                if (cornerEntrances.size() != 0) {
                    Tile entranceCorner = cornerEntrances.get((int) (Math.random() * cornerEntrances.size()));
                    entranceCorner.content = 1;
                }
            }
        }
    }
}
