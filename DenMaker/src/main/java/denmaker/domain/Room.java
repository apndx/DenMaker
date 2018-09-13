/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import java.util.Random;

/**
 *
 * @author apndx
 */
public class Room {

    public Random random;
    public int height;
    public int width;
    public int starty;
    public int startx;

    public Room(int areaHeight, int areaWidth) {
        this.random = new Random();
        this.height = 13 - random.nextInt(11); //height between 3-15
        this.width = 20 - random.nextInt(16); //width between 5-20
        this.starty = random.nextInt(areaHeight-1)+1;
        this.startx = random.nextInt(areaWidth-1)+1;

    }

}
