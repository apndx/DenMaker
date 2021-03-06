/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import denmaker.datastructures.OwnArrayList;

/**
 * MazeBuilder carves a maze through the remaining wall matter in the dungeon,
 * this is done after generating the rooms
 *
 * @author apndx
 */
public class MazeBuilder {

    public Area denArea;
    public int ynow;
    public int xnow;
    public int mazeRegion;

    public MazeBuilder(Area dungeonArea) {
        this.denArea = dungeonArea;
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
            denArea.tiles[start.y][start.x].content = 1;
            denArea.tiles[start.y][start.x].region = mazeRegion;
            //neighbour check  
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                    if (i == 0 && j == 0 || i != 0 && j != 0) {
                        continue;
                    }
                    Tile neighbor = denArea.tiles[start.y + i][start.x + j];

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
                Tile facing = next.checkOpposite(denArea);

                try {
                    // if both tiles are wall tiles and not on the edges
                    if (next.content == 0) {
                        if (facing.content == 0 && facing.y < denArea.areaHeight - 1 && facing.x < denArea.areaWidth - 1) {

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
                                    Tile neighbor2 = denArea.tiles[facing.y + k][facing.x + l];

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
        return this.denArea;
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
        if (y < 1 || x < 1 || y > denArea.areaHeight - 2 || x > denArea.areaWidth - 2) {
            y = 1;
            x = 1;
        }

        for (int i = y; i < denArea.tiles.length - 2; i++) {
            for (int j = x; j < denArea.tiles[i].length - 2; j++) {

                Tile tileNow = denArea.tiles[i][j];

                if (tileNow.content == 0) {

                    if (denArea.tiles[i + 1][j].content == 0
                            && denArea.tiles[i][j - 1].content == 0
                            && denArea.tiles[i][j + 1].content == 0
                            && denArea.tiles[i - 1][j].content == 0
                            && denArea.tiles[i - 1][j - 1].content == 0
                            && denArea.tiles[i - 1][j + 1].content == 0
                            && denArea.tiles[i + 1][j - 1].content == 0
                            && denArea.tiles[i + 1][j + 1].content == 0) {
                        ynow = y;
                        xnow = x;
                        return tileNow;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Trims from the maze the dead ends that do not lead anywhere. Leaves a
     * little tail in the up left corner.
     *
     * @return Area returns the Area that has now been trimmed of dead ends
     */
    public Area deadEndTrimmer() {

        for (int y = 1; y < this.denArea.areaHeight - 1; y++) {
            for (int x = 1; x < this.denArea.areaWidth - 1; x++) {

                if (this.denArea.tiles[y][x].content == 1) {

                    Tile underScrutiny = this.denArea.tiles[y][x];

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
        return this.denArea;
    }

    /**
     * Finds the dead ends to process
     *
     * @param underScrutiny The Tile that is checked
     * @return int amount of solid Tiles surrounding the Tile under scrutiny
     */
    public int deadEndHelper(Tile underScrutiny) {
        int deadCount = 0;
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {

                if (k == 0 && l == 0 || k != 0 && l != 0) {
                    continue;
                }
                try {
                    Tile neighbor = this.denArea.tiles[underScrutiny.y + k][underScrutiny.x + l];
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
