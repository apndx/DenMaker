/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import java.util.Random;

/**
 * Makes a new Tile
 * content: # wall, " " empty, @ door
 * weight: random weight between 0-999
 * 
 * @author apndx
 */
public class Tile {

    public String content; 
    public Random random;
    public int weight;

    public Tile(String content) {
        this.content = content;
        this.random = new Random();
        this.weight = random.nextInt(1000);  
    }

}
