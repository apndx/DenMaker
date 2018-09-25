/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import java.util.ArrayList;

/**
 * MazeBuilder carves a maze through the remaining wall matter in the dungeon,
 * this is done after generating the rooms
 *
 * @author apndx
 */
public class MazeBuilder {

    public Area dungeonArea;

    public MazeBuilder(Area dungeonArea) {
        this.dungeonArea = dungeonArea;
    }

    /**
     * Carves a maze through the remaining wall matter in the dungeon, based on
     * Prim
     *
     * @return Area returns the area that was worked on, now with a maze
     */
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

                try {
                    if (neighbor.content.equals(" ")) {
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }
                neighbors.add(neighbor);
                neighbor.setParent(start);
            }
        }
        //Tile last = null; we don't need this info yet, but maybe later
        while (!neighbors.isEmpty()) {

            Tile next = neighbors.remove((int) (Math.random() * neighbors.size()));
            Tile facing = next.checkOpposite(dungeonArea);

            try {
                // if both tiles are wall tiles and not on the edges
                if (next.content.equals("█")) {
                    if (facing.content.equals("█") && facing.y < dungeonArea.areaHeight - 1 && facing.x < dungeonArea.areaWidth - 1) {

                        next.content = " ";
                        facing.content = " ";
                        facing.setParent(next);
                        dungeonArea.tiles[next.y][next.x].content = " ";
                        dungeonArea.tiles[facing.y][facing.x].content = " ";
                        //last = facing; we don't need this info yet, but maybe later
                        for (int k = -1; k <= 1; k++) {
                            for (int l = -1; l <= 1; l++) {

                                if (k == 0 && l == 0 || k != 0 && l != 0) {
                                    continue;
                                }
                                Tile neighbor2 = dungeonArea.tiles[facing.y + k][facing.x + l];

                                try {
                                    if (neighbor2.content.equals(" ")) {
                                        continue;
                                    }
                                } catch (Exception e) {
                                    continue;
                                }
                                neighbors.add(neighbor2);
                                neighbor2.setParent(facing);
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return this.dungeonArea;
    }

    /**
     * Finds a starting point for the maze, starting point needs to be on the
     * area, not on the edge and not in a room
     *
     * @param y Suggested starting coordinate y
     * @param x Suggested starting coordinate x
     * @return Tile returns the Tile that is the starting point, or null
     */
    public Tile emptyFinder(int y, int x) {

        //if we get bad parametres, we change them to default starting point
        if (y < 1 || x < 1 || y > dungeonArea.areaHeight - 2 || x > dungeonArea.areaWidth - 2) {
            y = 1;
            x = 1;
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

    public Area deadEndKiller() {

        for (int y = 1; y < this.dungeonArea.areaHeight-2; y++) {
            for (int x = 1; x < this.dungeonArea.areaWidth-2; x++) {

                if (this.dungeonArea.tiles[y][x].content.equals(" ")) {

                    Tile underScrutiny = this.dungeonArea.tiles[y][x];

                    while (true) {
                        int deadCount = deadEndHelper(underScrutiny);
                        if (deadCount == 3) {
                            dungeonArea.tiles[y][x].content = "█";
                        } else {
                            break;
                        }
                        break;
                    }
                }
            }
        }
        return this.dungeonArea;
    }

    public int deadEndHelper(Tile underScrutiny) {
        int deadCount = 0;
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {

                if (k == 0 && l == 0 || k != 0 && l != 0) {
                    continue;
                }
                try {
                    Tile neighbor = dungeonArea.tiles[underScrutiny.y + k][underScrutiny.x + l];
                    if (neighbor.content.equals("█")) {
                        deadCount++;
                    }
                } catch (Exception e) {
                }
            }
        }
        return deadCount;
    }

}
