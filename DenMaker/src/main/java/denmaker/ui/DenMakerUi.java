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
                menu2();
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
        System.out.println("2. Quit");
    }

    /**
     * Menu option 1 - Create a new Den
     *
     * @param reader Scanner for reading the user input
     * @param logic Logic to work between UI and domain tools
     *
     */
    public static void menu1(Scanner reader, Logic logic) {

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

        System.out.println("Do you want to change the default 20 room adding attempts?");
        System.out.println("If yes, please type a number that is  between 10 and 999.");
        int attempts = 20;
        String roomAttempts = reader.nextLine();
        if (roomAttempts.matches("(\\d){2,3}")) {
            attempts = Integer.parseInt(roomAttempts);
        }
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

    }

    /**
     * Menu option 2 - quits the application
     */
    public static void menu2() {

        System.out.println("See you soon!");
    }

}
