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
public class Tile {

    public String content; // # wall, " " empty, @ door
    public Random random;
    public int weight; // 0-100 random weight

    public Tile(String content) {
        this.content = content;
        this.random = new Random();
        this.weight = random.nextInt(1000); //returns a new random int 0-999   
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

}
