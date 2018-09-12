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
        this.height = 15 - random.nextInt(11); //height between 5-15
        this.width = 15 - random.nextInt(11); //width between 5-15
        this.starty = random.nextInt(areaHeight);
        this.startx = random.nextInt(areaWidth);

    }

}
