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
public class Logic {

    public Tile[][] tiles;

    public Logic() {
        tiles = new Tile[50][150];

        // initialising the array with tiles   
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                tiles[y][x] = new Tile("#");
            }
        }
    }

    public void drawArea() {

        for (int y = 0; y <tiles.length; y++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int x = 0; x < tiles[y].length; x++) {
                stringBuilder.append(tiles[y][x].getContent());
            }

//            if (stringBuilder.toString().trim().isEmpty()) {
//                continue;
//            }
            System.out.println(stringBuilder);
        }
    }

    
    
}
