/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import denmaker.datastructures.OwnArrayList;

/**
 *
 * @author apndx
 */
public class Benchmark {

    public Logic logic;
    public OwnArrayList<OwnArrayList> testResults;
    public OwnArrayList<Double> averagePerformance;
    public double averageRoomAmount;
    public double averageRoom;
    public double averageMaze;
    public double averageEntrance;
    public double averageTrim;

    public Benchmark(Logic logic) {

        this.logic = logic;
        this.testResults = new OwnArrayList<>();
        this.averagePerformance = new OwnArrayList<>(); // 0 room amount, 1 room time, 2 maze time, 3 entrances time, 4 trimming time
        this.averageRoomAmount = 0;
        this.averageRoom = 0;
        this.averageMaze = 0;
        this.averageEntrance = 0;
        this.averageTrim = 0;

    }

    public void testRound(int attempts, int howMany, int height, int width) {

        for (int i = 0; i < howMany; i++) {
            logic.changeArea(height, width);
            logic.buildRooms(attempts);
            logic.buildMaze();
            logic.getOutOfTheBox();
            logic.killDeadEnds();
            testResults.add(logic.dungeonArea.performance);
        }

        for (int i = 0; i < testResults.size(); i++) {
            OwnArrayList<Double> listToCheck = testResults.get(i);

            averageRoomAmount += listToCheck.get(0);
            averageRoom += listToCheck.get(1);
            averageMaze += listToCheck.get(2);
            averageEntrance += listToCheck.get(3);
            averageTrim += listToCheck.get(4);
        }
        averagePerformance.add(averageRoomAmount / howMany);
        averagePerformance.add(averageRoom / howMany);
        averagePerformance.add(averageMaze / howMany);
        averagePerformance.add(averageEntrance / howMany);
        averagePerformance.add(averageTrim / howMany);

        System.out.println("This result is the average result of " + howMany + " testrounds.");
        System.out.println(toString());
        System.out.println(printResults(this.averagePerformance));
    }

    public String printResults(OwnArrayList<Double> resultList) {

        return "Adding rooms: " + resultList.get(1) + " ms" + "\n"
                + "Making maze: " + resultList.get(2) + " ms" + "\n"
                + "Opening entrances: " + resultList.get(3) + " ms" + "\n"
                + "Trimming dead ends: " + resultList.get(4) + " ms" + "\n";
    }

    @Override
    public String toString() {

        return "Area height: " + logic.dungeonArea.areaHeight + "\n"
                + "Area width: " + logic.dungeonArea.areaWidth + "\n"
                + "Room count: " + this.averagePerformance.get(0);
    }

}
