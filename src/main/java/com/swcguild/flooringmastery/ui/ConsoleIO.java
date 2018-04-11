/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.ui;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ConsoleIO {

    private Scanner sc = new Scanner(System.in);

    public ConsoleIO() {
    }

    public void displayMessage(String userString) {
        System.out.println(userString);
    }

    public int intPrompt(String userString) {
        int value = 0;
        boolean validInput = false;
        do {
            try {
                System.out.println(userString);
                value = Integer.parseInt(sc.nextLine());
                validInput = true;

            } catch (NumberFormatException nfe) {
                System.out.println("Your input must be an integer. Please try again");
            }
        } while (!validInput);
        return value;
    }

    public int intPrompt(String userString, int min, int max) {
        int value = 0;
        do {
            System.out.println(userString);
            value = Integer.parseInt(sc.nextLine());
            if (value < min || value > max) {
                System.out.println("That is not a valid integer");
            }
            return value;

        } while ((value < min || value > max));
    }

    public String stringPrompt(String userString) {
        System.out.println(userString);
        return sc.nextLine();
    }

    public float floatPrompt(String userString) {
        System.out.println(userString);
        float value = Float.parseFloat(sc.nextLine());
        return value;
    }

    public float floatPrompt(String userString, float min, float max) {
        float value = 0;
        do {
            System.out.println(userString);
            value = Float.parseFloat(sc.nextLine());
            if (value < min || value > max) {
                System.out.println("That is not a valid number");
            }
        } while (value < min || value > max);
        return value;
    }

    public double doublePrompt(String userString) {
        System.out.println(userString);
        double value = Double.parseDouble(sc.nextLine());
        return value;
    }

    public double doublePrompt(String userString, int min, int max) {
        double value = 0;
        do {
            System.out.println(userString);
            value = Double.parseDouble(sc.nextLine());
            if (value < min || value > max) {
                System.out.println("That is not a valid number");
            }
        } while (value < min || value > max);
        return value;

    }

}
