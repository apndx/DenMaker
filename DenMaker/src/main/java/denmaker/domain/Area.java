/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import java.util.ArrayList;

/**
 * Makes a new dungeon area
 * There is a constructor for default area of height 50 and width 150 
 * and a constructor for an area that takes height and width as parameters
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
                tiles[y][x] = new Tile("█", y, x, null); //█
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
    
}
