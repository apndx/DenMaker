/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

//import java.util.ArrayList;
import denmaker.datastructures.OwnArrayList;

/**
 * Logic has methods working between the domain tools and UI
 *
 * @author apndx
 */
public class Logic {
    
    public Area dungeonArea;
    public RoomBuilder roomBuilder;
    public MazeBuilder mazeBuilder;
    public Benchmark benchmark;
    
    public Logic() {
        this.dungeonArea = new Area();
        this.roomBuilder = new RoomBuilder(this.dungeonArea);
        this.mazeBuilder = new MazeBuilder(this.dungeonArea);
        this.benchmark = new Benchmark(this);
        
    }
    
    public String[][] contentToString(Area denArea) {
        
        String[][] denWithStrings = new String[denArea.areaHeight][denArea.areaWidth];
        char uniChar = '\u2588';
        String block = String.valueOf(uniChar); // ascii block

        for (int y = 0; y < denArea.tiles.length; y++) {;
            
            for (int x = 0; x < denArea.tiles[y].length; x++) {
                
                switch (denArea.tiles[y][x].content) {
                    case 0:
                        denWithStrings[y][x] = block;
                        break;
                    case 1:
                        denWithStrings[y][x] = " ";
                        break;
                    default:
                        denWithStrings[y][x] = "+";
                        break;
                }
            }
        }
        return denWithStrings;
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
     * Draws the dungeon area according to what String content each tile of the
     * area array has (0 = solid, 1 = corridor/room, 2 = room wall)
     *
     */
    public void drawArea() {
        
        String[][] stringDen = contentToString(this.dungeonArea);
        
        for (int y = 0; y < stringDen.length; y++) {
            StringBuilder stringBuilder = new StringBuilder();
            
            for (int x = 0; x < stringDen[y].length; x++) {
                stringBuilder.append(stringDen[y][x]);
            }
            System.out.println(stringBuilder);
        }
    }

    /**
     * Adds rooms to the dungeon area
     *
     * @param attempts Amount of room adding attempts, all the rooms that do not
     * collide with borders and each other will be added
     */
    public void buildRooms(int attempts) {
        long start = System.currentTimeMillis();
        this.dungeonArea = roomBuilder.addRooms(attempts);
        long stop = System.currentTimeMillis();
        this.dungeonArea.performance.add(dungeonArea.roomList.size() * 1.0);
        howLong("Roombuilding, ", start, stop);
    }

    /**
     * Adds rooms to the dungeon area
     *
     * @param roomList A List of rooms to add, all the rooms that do not collide
     * with borders and each other will be added This is method is mainly for
     * testing purposes
     */
    public void buildRooms(OwnArrayList<Room> roomList) {
        long start = System.currentTimeMillis();
        this.dungeonArea = roomBuilder.addRooms(roomList);
        long stop = System.currentTimeMillis();
        this.dungeonArea.performance.add(dungeonArea.roomList.size() * 1.0);
        howLong("Roombuilding, ", start, stop);
    }

    /**
     * Builds a maze to the dungeon after the rooms have been added
     */
    public void buildMaze() {
        long start = System.currentTimeMillis();
        this.dungeonArea = mazeBuilder.build();
        this.dungeonArea.solidifyWalls();
        long stop = System.currentTimeMillis();
        howLong("Mazebuilding, ", start, stop);
        
    }

    /**
     * Opens entrances from rooms to the dungeon
     */
    public void getOutOfTheBox() {
        long start = System.currentTimeMillis();
        this.dungeonArea.outOfTheBox();
        long stop = System.currentTimeMillis();
        howLong("Entrances, ", start, stop);
    }

    /**
     * Trims dead ends
     */
    public void killDeadEnds() {
        long start = System.currentTimeMillis();
        this.dungeonArea = mazeBuilder.deadEndKiller();
        long stop = System.currentTimeMillis();
        howLong("Trimming, ", start, stop);
    }
    
    public void howLong(String what, long start, long stop) {
        this.dungeonArea.performance.add((double) (stop - start));
    }
    
    public void testRound(int attempts, int testRounds, int height, int width) {
        this.benchmark = new Benchmark(this);
        benchmark.testRound(attempts, testRounds, height, width);
    }
    
    @Override
    public String toString() {
        
        return "Area height: " + dungeonArea.areaHeight + "\n"
                + "Area width: " + dungeonArea.areaWidth + "\n"
                + "Room count: " + dungeonArea.roomList.size();
        
    }
    
}
