/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.ui;

import denmaker.domain.Logic;
import java.util.Scanner;

/**
 * TextUserInterface for the DenMaker
 *
 * Menu options: 1. Create a new Den, 2. Test mode, 3. Quit
 *
 * @author apndx
 */
public class DenMakerUi {

    private Scanner reader;
    public Logic logic;

    public DenMakerUi(Scanner reader) {

        this.reader = reader;
        this.logic = new Logic();
    }

    public void start() {

        System.out.println("Welcome to DenMaker!");
        System.out.println(" ");
        printMenu();

        while (true) {
            String chosen = reader.nextLine();
            if (chosen.matches("1")) {
                menu1(reader, logic);
                printMenu();

            } else if (chosen.matches("2")) {
                menu2(reader, logic);
                printMenu();

            } else if (chosen.matches("3")) {
                menu3();
                break;
            } else {
                System.out.println("Please type a number mentioned in the menu.");
            }
        }
    }

    /**
     * Main menu
     */
    public static void printMenu() {
        System.out.println(" ");
        System.out.println("What to do next?");
        System.out.println("1. Create a new Den");
        System.out.println("2. Test mode");
        System.out.println("3. Quit");

    }

    /**
     * Menu option 1 - Create a new Den
     *
     * @param reader Scanner for reading the user input
     * @param logic Logic to work between UI and domain tools
     *
     */
    public static void menu1(Scanner reader, Logic logic) {

        int height = menuHelperHeight(reader); // default 49
        int width = menuHelperWidth(reader);  // default 149
        int attempts = menuHelperRoomAttempts(reader);  // default 20

        logic.changeArea(height, width);
        logic.buildRooms(attempts);
        System.out.println("A den with rooms");
        logic.drawArea();
        logic.buildMaze();
        System.out.println("A den with a maze");
        logic.drawArea();
        logic.getOutOfTheBox();
        System.out.println("Lets open the rooms");
        logic.drawArea();
        logic.killDeadEnds();
        System.out.println("Lets trim the dead ends");
        logic.drawArea();
        System.out.println(logic.toString());
        System.out.println(logic.benchmark.printResults(logic.denArea.performance));
    }

    /**
     * Menu option 2 - Testing
     *
     * @param reader Scanner for reading the user input
     * @param logic Logic to work between UI and domain tools
     */
    public static void menu2(Scanner reader, Logic logic) {

        System.out.println("1. Default test mode");
        System.out.println("2. Manual test mode");

        while (true) {
            String chosen = reader.nextLine();
            if (chosen.matches("1")) {

                System.out.println("Running default test round... Please wait for the results." + "\n");
                System.out.println("Next two rounds: 5 different dens are created with these parametres: 20 room adding attempts, height 49, width 149." + "\n");
                logic.testRound(20, 5, 49, 149);
                logic.testRound(20, 5, 49, 149);
                System.out.println("Next two rounds: 5 different dens are created with these parametres: 200 room adding attempts, height 149, width 490." + "\n");
                logic.testRound(200, 5, 149, 490);
                logic.testRound(200, 5, 149, 490);
                System.out.println("Next two rounds: 5 different dens are created with these parametres: 2000 room adding attempts, height 490, width 1490.");
                System.out.println("(This might take a while)." + "\n");
                logic.testRound(2000, 5, 490, 1490);
                logic.testRound(2000, 5, 490, 1490);
                break;

            } else if (chosen.matches("2")) {
                int height = menuHelperHeight(reader); // default 49
                int width = menuHelperWidth(reader);  // default 149
                int attempts = menuHelperRoomAttempts(reader);  // default 20
                int testRounds = menuHelperTestAmount(reader);  // default 5

                logic.testRound(attempts, testRounds, height, width);
                break;

            } else {
                System.out.println("Please type a number mentioned in the menu.");
            }
        }
    }

    /**
     * Menu option 3 - quits the application
     */
    public static void menu3() {

        System.out.println("See you soon!");
    }

    private static int menuHelperHeight(Scanner reader) {

        System.out.println("Let's make the den area, please give measurements.");
        System.out.println("Do you want to change the default height 49?");
        System.out.println("If yes, please type a number that is  between 10 and 999.");
        int height = 49;

        while (true) {
            String areaHeight = reader.nextLine();

            if (areaHeight.matches("(\\d){2,3}")) {
                height = Integer.parseInt(areaHeight);
                break;
            } else if (areaHeight.matches("")) {
                height = 49;
                break;
            } else {
                System.out.println("Please type a valid number or <enter>");
            }
        }
        if (height % 2 == 0) {
            height -= 1;
        }
        return height;
    }

    private static int menuHelperWidth(Scanner reader) {
        System.out.println("Do you want to change the default width 149?");
        System.out.println("If yes, please type a number that is  between 10 and 999.");
        int width = 149;

        while (true) {
            String areaWidth = reader.nextLine();

            if (areaWidth.matches("(\\d){2,3}")) {
                width = Integer.parseInt(areaWidth);
                break;
            } else if (areaWidth.matches("")) {
                width = 149;
                break;
            } else {
                System.out.println("Please type a valid number or <enter>");
            }
        }
        if (width % 2 == 0) {
            width -= 1;
        }
        return width;
    }

    private static int menuHelperRoomAttempts(Scanner reader) {

        System.out.println("Do you want to change the default 20 room adding attempts?");
        System.out.println("If yes, please type a number that is  between 10 and 999.");
        int attempts = 20;
        String roomAttempts = reader.nextLine();
        if (roomAttempts.matches("(\\d){2,3}")) {
            attempts = Integer.parseInt(roomAttempts);
        }
        return attempts;
    }

    private static int menuHelperTestAmount(Scanner reader) {

        System.out.println("Do you want to change the default 5 test rounds?");
        System.out.println("If yes, please type a number that is  between 1 and 20.");
        int rounds = 5;
        String testRounds = reader.nextLine();
        if (testRounds.matches("(\\d){1,2}")) {
            rounds = Integer.parseInt(testRounds);
        }
        return rounds;
    }

}
