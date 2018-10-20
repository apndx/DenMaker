/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.ui;

import java.util.Scanner;

/**
 * Main class - creates a Scanner and TextUI, starts the application
 * 
 * @author apndx
 */
public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        DenMakerUi denMakerUi = new DenMakerUi(reader);
        denMakerUi.start();       
    }

}
