/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.ui;

import java.util.Scanner;

/**
 *
 * @author apndx
 */
public class DenMakerUi {

    private Scanner reader;

    public DenMakerUi(Scanner reader) {

        this.reader = reader;
    }

    public void start() {

        System.out.println("Welcome to DenMaker!");
        System.out.println(" ");
        printMenu();
        
        while (true) {
            String chosen = reader.nextLine();
            if (chosen.matches("1")) {
                
            } else if (chosen.matches("2")) {
                menu2();
                break;
            } else {
                System.out.println("Please type a number mentioned in the menu.");
            }
            
        }
        
    }

    public static void printMenu() {
        System.out.println(" ");
        System.out.println("What to do next?");
        System.out.println("1. Create a new Den");
        System.out.println("2. Quit");
    }
    
     public static void menu1() {
         //todo 1. Create a new Den
     } 

    public static void menu2() {

        //quits the application
        System.out.println("See you soon!");
    }

}
