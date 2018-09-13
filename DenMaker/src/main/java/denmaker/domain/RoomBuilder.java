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


    public Area dungeonArea;

    public RoomBuilder(Area dungeonArea) {

        this.dungeonArea = dungeonArea;

    }

    public Area addRooms(int attempts) {

        for (int i = 0; i < attempts; i++) {

            Room roomAttempt = new Room(dungeonArea.areaHeight, dungeonArea.areaWidth);

            if (!collisionCheck(roomAttempt)) {
                // does not collide, let's put it in!
                for (int y = roomAttempt.starty; y < roomAttempt.starty + roomAttempt.height-1; y++) {
                    for (int x = roomAttempt.startx; x < roomAttempt.startx + roomAttempt.width-1; x++) {
                        dungeonArea.tiles[y][x].content= " ";  
                    }
                }
            }
        }
        
        return dungeonArea;
    }

    /**
     * @param roomAttempt room that is tested
     * @return true if there is a collision, false if not
     */
    public boolean collisionCheck(Room roomAttempt) {

        Tile[][] tiles = dungeonArea.tiles;
        int startY = roomAttempt.starty;
        int startX = roomAttempt.startx;
        int height = roomAttempt.height;
        int width = roomAttempt.width;

        if (startY + height > dungeonArea.areaHeight-1 || startX + width > dungeonArea.areaWidth-1)  {
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
