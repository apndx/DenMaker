/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import java.util.ArrayList;

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
    public ArrayList<Room> roomList;

    public Area() {
        this.areaHeight = 50;
        this.areaWidth = 150;
        this.tiles = new Tile[50][150];
        this.roomList = new ArrayList<>();

        //  initialising the array with tiles   
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                tiles[y][x] = new Tile("█", y, x, null);
            }
        }
    }

    public Area(int areaHeight, int areaWidth) {

        this.areaHeight = areaHeight;
        this.areaWidth = areaWidth;
        this.tiles = new Tile[areaHeight][areaWidth];
        this.roomList = new ArrayList<>();

        // initialising the array with tiles   
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                tiles[y][x] = new Tile("█", y, x, null);
            }
        }
    }

    /**
     * Changes the wall tiles back to solid form
     *
     */
    public void solidifyWalls() {

        for (Room roomtoCheck : this.roomList) {
            for (Tile toSolidify : roomtoCheck.roomWalls) {
                toSolidify.content = "█";
            }
        }
    }    
        /**
         * Finds entrances out of every room that has something to connect to in
         * it's vicinity (no further than one tile apart)
         *
         */
    public void outOfTheBox() {

        for (Room toGetOutOf : this.roomList) {
            ArrayList<Tile> potentialEntrances = new ArrayList<>();

            for (Tile toMakeEntranceOf : toGetOutOf.roomWalls) {

                int y = toMakeEntranceOf.y;
                int x = toMakeEntranceOf.x;

                try {
                    // does top roomtile has a corridor behind the wall?
                    if (this.tiles[y + 1][x].content.equals(".") && this.tiles[y - 1][x].content.equals(" ")) {
                        potentialEntrances.add(toMakeEntranceOf);
                    }
                    // left room, corridor?
                    if (this.tiles[y][x + 1].content.equals(".") && this.tiles[y][x - 1].content.equals(" ")) {
                        potentialEntrances.add(toMakeEntranceOf);
                    }
                    // bottom room, corridor?
                    if (this.tiles[y + 1][x].content.equals(".") && this.tiles[y - 1][x].content.equals(" ")) {
                        potentialEntrances.add(toMakeEntranceOf);
                    }
                    // right room, corridor
                    if (this.tiles[y][x - 1].content.equals(".") && this.tiles[y][x + 1].content.equals(" ")) {
                        potentialEntrances.add(toMakeEntranceOf);
                    }
                } catch (Exception e) {
                }

                if (potentialEntrances.isEmpty()) {
                    try {
                        // does room have another room behind the wall?
                        if (this.tiles[y + 1][x].content.equals(".") && this.tiles[y - 1][x].content.equals(".")) {
                            potentialEntrances.add(toMakeEntranceOf);
                        }
                        if (this.tiles[y][x + 1].content.equals(".") && this.tiles[y][x - 1].content.equals(".")) {
                            potentialEntrances.add(toMakeEntranceOf);
                        }
                        if (this.tiles[y + 1][x].content.equals(".") && this.tiles[y - 1][x].content.equals(".")) {
                            potentialEntrances.add(toMakeEntranceOf);
                        }
                        if (this.tiles[y][x - 1].content.equals(".") && this.tiles[y][x + 1].content.equals(".")) {
                            potentialEntrances.add(toMakeEntranceOf);
                        }

                    } catch (Exception e) {
                    }

                }

            }
            // corner cases, these are checked if the previous ones do not work
            if (!potentialEntrances.isEmpty()) {
                Tile entrance = potentialEntrances.get((int) (Math.random() * potentialEntrances.size()));
                entrance.content = " ";
            } else {
                ArrayList<Tile> cornerEntrances = new ArrayList<>();
                int starty = toGetOutOf.starty;
                int startx = toGetOutOf.startx;

                try {
                    if (this.tiles[starty - 1][startx - 1].content.equals(" ")) {
                        //left upper corner               
                        cornerEntrances.add(this.tiles[starty - 1][startx]);
                        cornerEntrances.add(this.tiles[starty][startx - 1]);
                    }
                    if (this.tiles[starty - 1][startx + toGetOutOf.width].content.equals(" ")) {
                        //right upper corner                
                        cornerEntrances.add(this.tiles[starty - 1][startx + toGetOutOf.width - 1]);
                        cornerEntrances.add(this.tiles[starty][startx + toGetOutOf.width]);
                    }
                    if (this.tiles[starty + toGetOutOf.height][startx - 1].content.equals(" ")) {
                        //left down corner                      
                        cornerEntrances.add(this.tiles[starty + toGetOutOf.height - 1][startx - 1]);
                        cornerEntrances.add(this.tiles[starty + toGetOutOf.height][startx]);
                    }
                    if (this.tiles[starty + toGetOutOf.height - 1][startx + toGetOutOf.width - 1].content.equals(" ")) {
                        //right down corner                    
                        cornerEntrances.add(this.tiles[starty + toGetOutOf.height - 1][startx + toGetOutOf.width]);
                        cornerEntrances.add(this.tiles[starty + toGetOutOf.height][startx + toGetOutOf.width - 1]);
                    }
                } catch (Exception e) {
                }

                if (!cornerEntrances.isEmpty()) {
                    Tile entranceCorner = cornerEntrances.get((int) (Math.random() * cornerEntrances.size()));
                    entranceCorner.content = " ";
                }
            }
        }
    }
}
