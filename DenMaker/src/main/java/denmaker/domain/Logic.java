/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

/**
 * Logic has methods working between the domain tools and UI
 *
 * @author apndx
 */
public class Logic {

    public Area dungeonArea;
    public RoomBuilder roomBuilder;
    public MazeBuilder mazeBuilder;

    public Logic() {
        this.dungeonArea = new Area();
        this.roomBuilder = new RoomBuilder(this.dungeonArea);
        this.mazeBuilder = new MazeBuilder(this.dungeonArea);
    }

    /**
     * Changes the dungeon area to a new one with new measurements
     *
     * @param height New height for the dungeon area
     * @param width New width for the dungeon area
     */
    public void changeArea(int height, int width) {
        this.dungeonArea = new Area(height, width);
        this.roomBuilder.dungeonArea = this.dungeonArea;
        this.mazeBuilder.dungeonArea = this.dungeonArea;
    }

    /**
     * Draws the dungeon area according to what content each tile of the area
     * array has
     */
    public void drawArea() {

        for (int y = 0; y < dungeonArea.tiles.length; y++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int x = 0; x < dungeonArea.tiles[y].length; x++) {
                stringBuilder.append(dungeonArea.tiles[y][x].content);
                //stringBuilder.append(" "+dungeonArea.tiles[y][x].weight+" ");
            }

//            if (stringBuilder.toString().trim().isEmpty()) {
//                continue;
//            }
            System.out.println(stringBuilder);
        }
    }

      /**
     * Builds a maze to the dungeon after the rooms have been added
     */
    public void buildMaze() {
        this.dungeonArea = mazeBuilder.build();
    }

    /**
     * Adds rooms to the dungeon area
     *
     * @param attempts Amount of room adding attempts, all the rooms that do not
     * collide with borders and each other will be added
     */
    public void buildRooms(int attempts) {
        this.dungeonArea = roomBuilder.addRooms(attempts);
    }

}
