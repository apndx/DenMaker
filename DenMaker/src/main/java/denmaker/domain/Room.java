/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import denmaker.datastructures.OwnArrayList;
import java.util.Random;

/**
 * Makes a new room
 *
 * height: either random height between 3-15 or a parameter width: either random
 * width between 5-20 or a parameter starty and startx: starting coordinates of
 * the room within the dungeon area, either random or parameters
 *
 * @author apndx
 */
public class Room {

    public int height;
    public int width;
    public int starty;
    public int startx;
    public OwnArrayList<Tile> roomWalls;

    public Room(int height, int width, int starty, int startx) {
        this.height = height;
        this.width = width;
        this.starty = starty;
        this.startx = startx;
        this.roomWalls = new OwnArrayList<>();
    }

    public Room(int areaHeight, int areaWidth) {
        Random random = new Random();
        this.height = 13 - random.nextInt(11);
        this.width = 20 - random.nextInt(16);
        this.starty = random.nextInt(areaHeight - 1) + 1;
        this.startx = random.nextInt(areaWidth - 1) + 1;
        this.roomWalls = new OwnArrayList<>();
    }

    public void setStarty(int starty) {
        this.starty = starty;
    }

    public void setStartx(int startx) {
        this.startx = startx;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Area addRoomWalls(Area dungeonArea) {

        // upper and bottom wall
        for (int i = this.startx; i < this.startx + this.width; i++) {
            this.roomWalls.add(dungeonArea.tiles[this.starty - 1][i]);
            dungeonArea.tiles[this.starty - 1][i].content = 2;
            this.roomWalls.add(dungeonArea.tiles[this.starty + this.height][i]);
            dungeonArea.tiles[this.starty + this.height][i].content = 2;
        }

        // left and right wall
        for (int i = this.starty; i < this.starty + this.height; i++) {
            this.roomWalls.add(dungeonArea.tiles[i][this.startx - 1]);
            dungeonArea.tiles[i][this.startx - 1].content = 2;
            this.roomWalls.add(dungeonArea.tiles[i][this.startx + this.width]);
            dungeonArea.tiles[i][this.startx + this.width].content = 2;
        }
        return dungeonArea;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.height;
        hash = 71 * hash + this.width;
        hash = 71 * hash + this.starty;
        hash = 71 * hash + this.startx;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (this.height != other.height) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.starty != other.starty) {
            return false;
        }
        if (this.startx != other.startx) {
            return false;
        }
        return true;
    }

}
