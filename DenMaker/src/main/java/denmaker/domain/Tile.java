/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import java.util.Random;

/**
 * Makes a new Tile content: █ wall, " " empty, @ door weight: random weight
 * between 0-999
 *
 * @author apndx
 */
public class Tile {

    public String content;
    public int weight;
    public int y;
    public int x;
    public Tile parent;

    public Tile(String content, int y, int x, Tile parent) {
        this.content = content;
        Random random = new Random();
        this.weight = random.nextInt(1000);
        this.y = y;
        this.x = x;
        this.parent = parent;
    }


    public void setParent(Tile parent) {
        this.parent = parent;
    }

    public Tile checkOpposite(Area dungeonArea) {

        if (this.parent == null) {
            return null;
        }

        int differenceY = this.y-parent.y;
        int differenceX = this.x-parent.x;

        if (differenceY != 0) {
            try {
                return dungeonArea.tiles[this.y + differenceY][this.x];

            } catch (Exception e) {
                return null;
            }

        } else if (differenceX != 0) {
            try {
                return dungeonArea.tiles[this.y][this.x + differenceX];
            } catch (Exception e) {
                return null;
            }

        } else {
            return null;
        }
    }

    public void setContent(String content) {
        this.content = content;
    }

}
