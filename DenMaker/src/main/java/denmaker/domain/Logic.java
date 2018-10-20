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

    public Area denArea;
    public RoomBuilder roomBuilder;
    public MazeBuilder mazeBuilder;
    public Benchmark benchmark;

    public Logic() {
        this.denArea = new Area();
        this.roomBuilder = new RoomBuilder(this.denArea);
        this.mazeBuilder = new MazeBuilder(this.denArea);
        this.benchmark = new Benchmark(this);

    }

    /**
     * Changes the dungeon area to a new one with new measurements
     *
     * @param height New height for the dungeon area
     * @param width New width for the dungeon area
     */
    public void changeArea(int height, int width) {
        this.denArea = new Area(height, width);
        this.roomBuilder.dungeonArea = this.denArea;
        this.mazeBuilder.dungeonArea = this.denArea;
    }

    /**
     * Changes the Area content to string form before printing
     *
     * @return an Array of Strings that are the content of the Den
     */
    public String[][] contentToString() {

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
     * Draws the dungeon area according to what String content each tile of the
     * area array has (0 = solid, 1 = corridor/room, 2 = room wall)
     *
     */
    public void drawArea() {

        String[][] stringDen = contentToString();

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
     * collide with borders and each other will be added Also saves the amount
     * of rooms to performance listing
     */
    public void buildRooms(int attempts) {
        long start = System.currentTimeMillis();
        this.denArea = roomBuilder.addRooms(attempts);
        long stop = System.currentTimeMillis();
        this.denArea.performance.add(denArea.roomList.size() * 1.0);
        howLong(start, stop);
    }

    /**
     * Adds rooms to the dungeon area
     *
     * @param roomList A List of rooms to add, all the rooms that do not collide
     * with borders and each other will be added This is method is mainly for
     * testing purposes Also saves the amount of rooms to performance listing
     */
    public void buildRooms(OwnArrayList<Room> roomList) {
        long start = System.currentTimeMillis();
        this.denArea = roomBuilder.addRooms(roomList);
        long stop = System.currentTimeMillis();
        this.denArea.performance.add(denArea.roomList.size() * 1.0);
        howLong(start, stop);
    }

    /**
     * Builds a maze to the dungeon after the rooms have been added
     */
    public void buildMaze() {
        long start = System.currentTimeMillis();
        this.denArea = mazeBuilder.build();
        this.denArea.solidifyWalls();
        long stop = System.currentTimeMillis();
        howLong(start, stop);

    }

    /**
     * Opens entrances from rooms to the dungeon
     */
    public void getOutOfTheBox() {
        long start = System.currentTimeMillis();
        this.denArea.outOfTheBox();
        long stop = System.currentTimeMillis();
        howLong(start, stop);
    }

    /**
     * Trims dead ends
     */
    public void killDeadEnds() {
        long start = System.currentTimeMillis();
        this.denArea = mazeBuilder.deadEndExterminator();
        long stop = System.currentTimeMillis();
        howLong(start, stop);
    }

    /**
     * Time stamps performance testing
     *
     * @param start The time of the method starting
     * @param stop The time of the method ending
     *
     */
    public void howLong(long start, long stop) {
        this.denArea.performance.add((double) (stop - start));
    }

    /**
     * For performance testing, runs all the methods to create a den and
     * measures the performance
     *
     * @param attempts Room adding attempts
     * @param testRounds How many test rounds
     * @param height DungeonArea height
     * @param width DungeonArea width
     *
     */
    public void testRound(int attempts, int testRounds, int height, int width) {
        this.benchmark = new Benchmark(this);
        benchmark.testRound(attempts, testRounds, height, width);
    }

    @Override
    public String toString() {
        return "Area height: " + denArea.areaHeight + "\n"
                + "Area width: " + denArea.areaWidth + "\n"
                + "Room count: " + denArea.roomList.size();
    }

}
