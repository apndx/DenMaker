/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author apndx
 */
public class MazeBuilder {

    public Area dungeonArea;

    public MazeBuilder(Area dungeonArea) {
        this.dungeonArea = dungeonArea;
    }

    public Area build() {

        ArrayList<Tile> neighbors = new ArrayList<>();

        Tile start = emptyFinder(1, 1);
        dungeonArea.tiles[start.y][start.x].setContent(" ");

        //neighbour check  
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0 || i != 0 && j != 0) {
                    continue;
                }
                Tile neighbor = dungeonArea.tiles[start.y + i][start.x + j];
                neighbor.setParent(start);

                try {

                    if (neighbor.content.equals(" ")) {
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }
                neighbors.add(neighbor);
            }
        }
        //Tile last = null;
        while (!neighbors.isEmpty()) {

            Tile next = neighbors.remove((int) (Math.random() * neighbors.size()));
            Tile facing = next.checkOpposite(dungeonArea);

            try {
                // if both walls
                if (next.content.equals("█")) {
                    if (facing.content.equals("█")) {

                        next.content = " ";
                        facing.content = " ";
                        facing.setParent(next);
                        dungeonArea.tiles[next.y][next.x].content = " ";
                        dungeonArea.tiles[facing.y][facing.x].content = " ";
                        //last = facing;
                        for (int k = -1; k <= 1; k++) {
                            for (int l = -1; l <= 1; l++) {

                                if (k == 0 && l == 0 || k != 0 && l != 0) {
                                    continue;
                                }
                                Tile neighbor2 = dungeonArea.tiles[facing.y + k][facing.x + l];
                                neighbor2.setParent(facing);
                                try {

                                    if (neighbor2.content.equals(" ")) {
                                        continue;
                                    }
                                } catch (Exception e) {
                                    continue;
                                }
                                neighbors.add(neighbor2);
                            }
                        }
                    }
                }

            } catch (Exception e) {
            }

        }
        return this.dungeonArea;
    }

    public Tile emptyFinder(int y, int x) {

        if (y < 1 || x < 1 || y > dungeonArea.areaHeight - 2 || x > dungeonArea.areaWidth - 2) {
            return null;
        }

        for (int i = y; i < dungeonArea.tiles.length - 2; i++) {
            for (int j = x; j < dungeonArea.tiles[i].length - 2; j++) {

                Tile tileNow = dungeonArea.tiles[i][j];

                if (tileNow.content.equals("█")) {

                    if (dungeonArea.tiles[i + 1][j].content.equals("█")
                            && dungeonArea.tiles[i][j - 1].content.equals("█")
                            && dungeonArea.tiles[i][j + 1].content.equals("█")
                            && dungeonArea.tiles[i - 1][j].content.equals("█")) {


                        return tileNow;
                    }
                }
            }
        }
        return null;
    }

}
