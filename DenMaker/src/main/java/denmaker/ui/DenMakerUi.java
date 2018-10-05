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

        System.out.println("Dungeon with rooms");
        logic.drawArea();
        logic.buildMaze();
        System.out.println("Dungeon with a maze");
        logic.drawArea();
        System.out.println("Lets open the rooms");
        logic.getOutOfTheBox();
        logic.drawArea();
        logic.killDeadEnds();
        System.out.println("Lets trim the dead ends");
        logic.drawArea();
        System.out.println(logic.toString());
        System.out.println(logic.testResults(logic.performance));
    }

    /**
     * Menu option 2 - Testing
     *
     * @param reader Scanner for reading the user input
     * @param logic Logic to work between UI and domain tools
     */
    public static void menu2(Scanner reader, Logic logic) {
        int height = menuHelperHeight(reader); // default 49
        int width = menuHelperWidth(reader);  // default 149
        int attempts = menuHelperRoomAttempts(reader);  // default 20
        int testRounds = menuHelperTestAmount(reader);  // default 5
        logic.testRound(attempts, testRounds, height, width);
    }

    /**
     * Menu option 3 - quits the application
     */
    public static void menu3() {

        System.out.println("See you soon!");
    }

    private static int menuHelperHeight(Scanner reader) {

        System.out.println("Let's make the dungeon map, please give measurements.");
        System.out.println("Do you want to change the default height 49?");
        System.out.println("If yes, please type a number that is  between 10 and 999.");
        String areaHeight = reader.nextLine();
        int height = 49;

        if (areaHeight.matches("(\\d){2,3}")) {
            height = Integer.parseInt(areaHeight);
        }

        if (height % 2 == 0) {
            height -= 1;
        }
        return height;
    }

    private static int menuHelperWidth(Scanner reader) {
        System.out.println("Do you want to change the default width 149?");
        System.out.println("If yes, please type a number that is  between 10 and 999.");
        String areaWidth = reader.nextLine();
        int width = 149;

        if (areaWidth.matches("(\\d){2,3}")) {
            width = Integer.parseInt(areaWidth);
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
