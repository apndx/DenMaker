/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import denmaker.datastructures.OwnArrayList;

/**
 * This class is for performance testing
 *
 * @author apndx
 */
public class Benchmark {

    public Logic logic;
    public OwnArrayList<OwnArrayList> testResults;  // Each area has a list of performance values, this is a list of those lists
    public OwnArrayList<Double> averagePerformance;

    public Benchmark(Logic logic) {

        this.logic = logic;
        this.testResults = new OwnArrayList<>();
        this.averagePerformance = new OwnArrayList<>(); // 0 room amount, 1 room time, 2 maze time, 3 entrances time, 4 trimming time
    }

    /**
     * Main method for running a test run
     *
     * @param attempts Room adding attempts
     * @param howMany How many test rounds
     * @param height DungeonArea height
     * @param width DungeonArea width
     *
     */
    public void testRound(int attempts, int howMany, int height, int width) {

        testLooper(attempts, howMany, height, width);
        averageCounter(howMany);

        System.out.println("This result is the average result of " + howMany + " testrounds.");
        System.out.println(toString());
        System.out.println(printResults(this.averagePerformance));
    }

    /**
     * Runs the required amount of den creations, collects the performance
     * results of each to testResults list
     *
     * @param attempts Room adding attempts
     * @param howMany How many test rounds
     * @param height DungeonArea height
     * @param width DungeonArea width
     *
     */
    public void testLooper(int attempts, int howMany, int height, int width) {
        for (int i = 0; i < howMany; i++) {
            logic.changeArea(height, width);
            logic.buildRooms(attempts);
            logic.buildMaze();
            logic.getOutOfTheBox();
            logic.trimDeadEnds();
            testResults.add(logic.denArea.performance);
        }
    }

    /**
     * Counts the average performance time for each Den making process
     *
     * @param howMany How many test rounds
     *
     */
    public void averageCounter(int howMany) {

        double roomAmountSum = 0;
        double roomSum = 0;
        double mazeSum = 0;
        double entranceSum = 0;
        double trimSum = 0;

        for (int i = 0; i < testResults.size(); i++) {
            OwnArrayList<Double> listToCheck = testResults.get(i);

            roomAmountSum += listToCheck.get(0);
            roomSum += listToCheck.get(1);
            mazeSum += listToCheck.get(2);
            entranceSum += listToCheck.get(3);
            trimSum += listToCheck.get(4);
        }
        averagePerformance.add(roomAmountSum / howMany);
        averagePerformance.add(roomSum / howMany);
        averagePerformance.add(mazeSum / howMany);
        averagePerformance.add(entranceSum / howMany);
        averagePerformance.add(trimSum / howMany);
    }

    @Override
    public String toString() {

        return "Area height: " + logic.denArea.areaHeight + "\n"
                + "Area width: " + logic.denArea.areaWidth + "\n"
                + "Room count: " + this.averagePerformance.get(0);
    }

    /**
     * String of the performance results for printing
     *
     * @param resultList List of the results we want in a printed form
     * @return String of the performance results
     *
     */
    public String printResults(OwnArrayList<Double> resultList) {

        return "Adding rooms: " + resultList.get(1) + " ms" + "\n"
                + "Making maze: " + resultList.get(2) + " ms" + "\n"
                + "Opening entrances: " + resultList.get(3) + " ms" + "\n"
                + "Trimming dead ends: " + resultList.get(4) + " ms" + "\n";
    }

}
