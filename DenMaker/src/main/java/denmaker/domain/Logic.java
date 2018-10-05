/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import java.util.ArrayList;

/**
 * Logic has methods working between the domain tools and UI
 *
 * @author apndx
 */
public class Logic {

    public Area dungeonArea;
    public RoomBuilder roomBuilder;
    public MazeBuilder mazeBuilder;
    public ArrayList<Double> performance; // 0 rooms, 1 maze, 2 entrances, 3 trimming

    public Logic() {
        this.dungeonArea = new Area();
        this.roomBuilder = new RoomBuilder(this.dungeonArea);
        this.mazeBuilder = new MazeBuilder(this.dungeonArea);
        this.performance = new ArrayList<>();
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
        howLong("Roombuilding, ", start, stop);
    }

    /**
     * Adds rooms to the dungeon area
     *
     * @param roomList A List of rooms to add, all the rooms that do not collide
     * with borders and each other will be added This is method is mainly for
     * testing purposes
     */
    public void buildRooms(ArrayList<Room> roomList) {
        long start = System.currentTimeMillis();
        this.dungeonArea = roomBuilder.addRooms(roomList);
        long stop = System.currentTimeMillis();
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
        this.performance.add((double) (stop - start));
        //System.out.println(what + (stop - start) + "ms");
    }

    public void testRound(int attempts, int howMany, int height, int width) {

        ArrayList<ArrayList> testResults = new ArrayList<>();
        ArrayList<Double> averagePerformance = new ArrayList<>();
        double averageRoom = 0;
        double averageMaze = 0;
        double averageEntrance = 0;
        double averageTrim = 0;

        for (int i = 0; i < howMany; i++) {
            changeArea(height, width);
            buildRooms(attempts);
            buildMaze();
            getOutOfTheBox();
            killDeadEnds();
            testResults.add(this.performance);
        }

        for (int i = 0; i < testResults.size(); i++) {
            ArrayList<Double> listToCheck = testResults.get(i);

            averageRoom += listToCheck.get(0);
            averageMaze += listToCheck.get(1);
            averageEntrance += listToCheck.get(2);
            averageTrim += listToCheck.get(3);
        }

        averagePerformance.add(averageRoom / howMany);
        averagePerformance.add(averageMaze / howMany);
        averagePerformance.add(averageEntrance / howMany);
        averagePerformance.add(averageTrim / howMany);

        System.out.println(toString());
        System.out.println("This result is the average result of " + howMany + " testrounds.");
        System.out.println(testResults(averagePerformance));
    }

    public String testResults(ArrayList<Double> results) {

        return "Adding rooms: " + results.get(0) + " ms" + "\n"
                + "Making maze: " + results.get(1) + " ms" + "\n"
                + "Opening entrances: " + results.get(2) + " ms" + "\n"
                + "Trimming dead ends: " + results.get(3) + " ms";
    }

    @Override
    public String toString() {

        return "Area height: " + dungeonArea.areaHeight + "\n"
                + "Area width: " + dungeonArea.areaWidth + "\n"
                + "Room count: " + dungeonArea.roomList.size() + "\n";

    }

}
