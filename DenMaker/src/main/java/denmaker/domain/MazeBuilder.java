/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

//import java.util.ArrayList;
import denmaker.datastructures.OwnArrayList;

/**
 * MazeBuilder carves a maze through the remaining wall matter in the dungeon,
 * this is done after generating the rooms
 *
 * @author apndx
 */
public class MazeBuilder {

    public Area dungeonArea;
    public int ynow;
    public int xnow;
    public int mazeRegion;

    public MazeBuilder(Area dungeonArea) {
        this.dungeonArea = dungeonArea;
        this.ynow = 1;
        this.xnow = 1;
        this.mazeRegion = 0;
    }

    /**
     * Carves a maze through the remaining wall matter in the dungeon, based on
     * Prim
     *
     * @return Area returns the area that was worked on, now with a maze
     */
    public Area build() {

        while (true) {

            OwnArrayList<Tile> neighbors = new OwnArrayList<>();

            Tile start = emptyFinder(ynow, xnow);

            mazeRegion--;
            dungeonArea.tiles[start.y][start.x].content = 1;
            dungeonArea.tiles[start.y][start.x].region = mazeRegion;
            //neighbour check  
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                    if (i == 0 && j == 0 || i != 0 && j != 0) {
                        continue;
                    }
                    Tile neighbor = dungeonArea.tiles[start.y + i][start.x + j];

                    try {
                        if (neighbor.content == 1) {
                            continue;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    neighbors.add(neighbor);
                    neighbor.setParent(start);
                }
            }
            Tile last = null;
            while (neighbors.size() != 0) {

                Tile next = neighbors.get((int) (Math.random() * neighbors.size()));
                neighbors.remove(next);
                Tile facing = next.checkOpposite(dungeonArea);

                try {
                    // if both tiles are wall tiles and not on the edges
                    if (next.content == 0) {
                        if (facing.content == 0 && facing.y < dungeonArea.areaHeight - 1 && facing.x < dungeonArea.areaWidth - 1) {

                            next.content = 1;
                            next.region = mazeRegion;
                            facing.content = 1;
                            facing.region = mazeRegion;

                            facing.setParent(next);
                            last = facing;
                            for (int k = -1; k <= 1; k++) {
                                for (int l = -1; l <= 1; l++) {

                                    if (k == 0 && l == 0 || k != 0 && l != 0) {
                                        continue;
                                    }
                                    Tile neighbor2 = dungeonArea.tiles[facing.y + k][facing.x + l];

                                    try {
                                        if (neighbor2.content == 1) {
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
            if (emptyFinder(ynow, xnow) == null) {
                break;
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

                if (tileNow.content == 0) {

                    if (dungeonArea.tiles[i + 1][j].content == 0
                            && dungeonArea.tiles[i][j - 1].content == 0
                            && dungeonArea.tiles[i][j + 1].content == 0
                            && dungeonArea.tiles[i - 1][j].content == 0
                            && dungeonArea.tiles[i - 1][j - 1].content == 0
                            && dungeonArea.tiles[i - 1][j + 1].content == 0
                            && dungeonArea.tiles[i + 1][j - 1].content == 0
                            && dungeonArea.tiles[i + 1][j + 1].content == 0) {
                        ynow = y;
                        xnow = x;
                        return tileNow;
                    }
                }
            }
        }
        return null;
    }

    public Area deadEndExterminator() {

        for (int y = 1; y < this.dungeonArea.areaHeight - 1; y++) {
            for (int x = 1; x < this.dungeonArea.areaWidth - 1; x++) {

                if (this.dungeonArea.tiles[y][x].content == 1) {

                    Tile underScrutiny = this.dungeonArea.tiles[y][x];

                    while (true) {
                        int deadCount = deadEndHelper(underScrutiny);
                        if (deadCount == 3 || deadCount == 4) {
                            underScrutiny.content = 0;
                            underScrutiny = underScrutiny.parent;
                        } else {
                            break;
                        }

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
                    Tile neighbor = this.dungeonArea.tiles[underScrutiny.y + k][underScrutiny.x + l];
                    if (neighbor.content == 0) {
                        deadCount++;
                    }
                } catch (Exception e) {
                }
            }
        }
        return deadCount;
    }

}
