/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

/**
 *
 * @author apndx
 */
public class RoomBuilder {

    public int attempts;

    public RoomBuilder(int attempts) {

        this.attempts = attempts;

    }

    public void build() {

    }

    /**
     * @param tiles is the dungeon area, height and width room measurements
     * @param startY room starting coordinates
     * @param startX room starting coordinates
     * @param height room height
     * @param width room width
     * @return true if there is a collision, false if not
     */
    public boolean collisionCheck(Tile[][] tiles, int startY, int startX, int height, int width) {

        if (startY + height > 50 || startX + width > 150) {
            return true;
        }

        for (int i = startY; i < startY + height; i++) {
            for (int j = startX; j < startX + width; j++) {
                if (" ".equals(tiles[i][j].content)) {
                    return true;
                }
            }
        }

        return false;
    }

}
