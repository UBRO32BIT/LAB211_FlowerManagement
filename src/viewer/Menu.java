/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import java.util.Scanner;
import tool.Validator;

/**
 *
 * @author ubro3
 */
public class Menu {
    /**
    * Prompts the user to choose an option from a list and returns the selected choice.
    *
    * @param options An array of options to display.
    * @return The chosen option as an integer.
    */
    public static int getChoice(Object[] options) {
        String strChoice;
        boolean isValidChoice = false;
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        do {
            System.out.print("Your options from 1 - " + options.length + ": ");
            strChoice = sc.nextLine();

            if (Validator.validateNumber(strChoice)) {
                choice = Integer.parseInt(strChoice);
                if (choice >= 1 && choice <= options.length) {
                    isValidChoice = true;
                }
            }

            if (!isValidChoice) {
                System.out.println("Invalid choice!");
            }
        } while (!isValidChoice);

        return choice;
    }
    /**
     * Waits for the user to press the "ENTER" key before continuing.
     */
    public static void waitForEnterKey() {
        System.out.print("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
