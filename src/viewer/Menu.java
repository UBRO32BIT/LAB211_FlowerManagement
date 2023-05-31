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
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        do {
            System.out.print("Your options from 1 - " + options.length + ": ");
            Scanner sc = new Scanner(System.in);
            strChoice = sc.nextLine();
            if (Validator.validateNumber(strChoice)) {
                int choice = Integer.parseInt(strChoice);
                if (choice >= 1 && choice <= options.length) {
                    return choice;
                }
            }
            System.out.println("Invalid choice!");
        } while (true);
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
