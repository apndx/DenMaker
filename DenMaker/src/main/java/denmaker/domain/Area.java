/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    public Set<Tile> roomWalls;

    public Area() {
        this.areaHeight = 50;
        this.areaWidth = 150;
        this.tiles = new Tile[50][150];
        this.roomList = new ArrayList<>();
        this.roomWalls = new HashSet<>();

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
        this.roomWalls = new HashSet<>();

        // initialising the array with tiles   
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                tiles[y][x] = new Tile("█", y, x, null);
            }
        }
    }

    public void solidifyWalls() {

        for (Tile toSolidify : this.roomWalls) {
            toSolidify.content = "█";
        }
    }

    public void outOfTheBox() {

        for (Room toGetOutOf : this.roomList) {
            ArrayList<Tile> potentialEntrances = new ArrayList<>();

            for (Tile toMakeEntranceOf : toGetOutOf.roomWalls) {

                try {
                    int x = toMakeEntranceOf.x;
                    int y = toMakeEntranceOf.y;
                    if (this.tiles[y + 1][x].content.equals(" ") && this.tiles[y - 1][x].content.equals(" ")) {
                        potentialEntrances.add(toMakeEntranceOf);
                    }
                    if (this.tiles[y][x + 1].content.equals(" ") && this.tiles[y][x - 1].content.equals(" ")) {
                        potentialEntrances.add(toMakeEntranceOf);
                    }
                } catch (Exception e) {
                }

            }

            if (!potentialEntrances.isEmpty()) {
                Tile entrance = potentialEntrances.get((int) (Math.random() * potentialEntrances.size()));
                entrance.content = " ";
            }
        }
    }
}
