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
public class Logic {

    public Area dungeonArea;
    public RoomBuilder roomBuilder;

    public Logic() {
        this.dungeonArea = new Area();
        this.roomBuilder = new RoomBuilder(dungeonArea);
    }

    public void changeArea(int height, int width) {
        dungeonArea = new Area(height, width);
    }

    public void drawArea() {

        for (int y = 0; y < dungeonArea.tiles.length; y++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int x = 0; x < dungeonArea.tiles[y].length; x++) {
                stringBuilder.append(dungeonArea.tiles[y][x].getContent());
            }

//            if (stringBuilder.toString().trim().isEmpty()) {
//                continue;
//            }
            System.out.println(stringBuilder);
        }
    }
    
    public void buildRooms(int attempts) {
        dungeonArea = roomBuilder.addRooms(attempts);        
    }

    
    
}
