/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import java.util.Random;

/**
 * Makes a new room
 * 
 * height: either random height between 3-15 or a parameter
 * width: either random width between 5-20 or a parameter
 * starty and startx: starting coordinates of the room within the dungeon area, either random or parameters
 * 
 * @author apndx
 */
public class Room {
    
    public int height;
    public int width;
    public int starty;
    public int startx;
    
    public Room(int height, int width, int starty, int startx) {
        this.height = height;
        this.width = width;
        this.starty = starty;
        this.startx = startx;    
    }
    

    public Room(int areaHeight, int areaWidth) {
        Random random = new Random();
        this.height = 13 - random.nextInt(11); 
        this.width = 20 - random.nextInt(16); 
        this.starty = random.nextInt(areaHeight-1)+1;
        this.startx = random.nextInt(areaWidth-1)+1;
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
 
}
