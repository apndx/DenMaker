/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import java.util.ArrayList;

/**
 * This tool is used to add rooms to a dungeon area
 *
 * @author apndx
 */
public class RoomBuilder {

    public Area dungeonArea;

    public RoomBuilder(Area dungeonArea) {

        this.dungeonArea = dungeonArea;
    }
    
    /**
     * @param attempts Amount of room adding attempts, will generate a list of random rooms
     * that is processed in the other addRooms method
     * @return returns the area with rooms that have been added
     */
    public Area addRooms(int attempts) {    
        for (int i = 0; i < attempts; i++) {     
            dungeonArea.roomList.add(new Room(dungeonArea.areaHeight, dungeonArea.areaWidth));     
        }
        addRooms(dungeonArea.roomList);
        return dungeonArea;
    }

    /**
     * @param rooms A list of rooms that we try to add to the area, all the
     * rooms that do not collide with borders and each other will be added
     * @return Area returns the dungeon area with the rooms that now have been
     * added to it
     */
    public Area addRooms(ArrayList<Room> rooms) {
        dungeonArea.roomList = rooms;
        for (int i = 0; i < rooms.size(); i++) {
            if (!collisionCheck(rooms.get(i))) {
                addRoomHelper(rooms.get(i));
                dungeonArea = rooms.get(i).addRoomWalls(dungeonArea);
                
            }
        }
        return dungeonArea;
    }

    private void addRoomHelper(Room roomAttempt) {
        // room does not collide, let's put it in!
        for (int y = roomAttempt.starty; y < roomAttempt.starty + roomAttempt.height - 1; y++) {
            for (int x = roomAttempt.startx; x < roomAttempt.startx + roomAttempt.width - 1; x++) {
                dungeonArea.tiles[y][x].content = " ";
            }
        }
    }

    /**
     * @param roomAttempt the room that is tested
     * @return true if there is a collision, false if not
     */
    public boolean collisionCheck(Room roomAttempt) {

        Tile[][] tiles = dungeonArea.tiles;
        int startY = roomAttempt.starty;
        int startX = roomAttempt.startx;
        int height = roomAttempt.height;
        int width = roomAttempt.width;

        if (startY + height > dungeonArea.areaHeight - 1 || startX + width > dungeonArea.areaWidth - 1) {
            return true;
        }

        if (startY == 0 || startX == 0) {
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
    
//    roomWaller(Room room) {
//        
//        
//        
//        
//    }
    

}
