/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.time.LocalDate;
import java.util.Scanner;
import tools.Converter;
import tools.Validator;

/**
 * Utility class for handling user input.
 * Provides methods for inputting different types of fields.
 * 
 * @author ubro3
 */
public class InputUtils {
    private final Scanner sc;
    public InputUtils (Scanner sc) {
        this.sc = sc;
    }
    /**
     * Handles user input for a string field.
     *
     * @param prompt The prompt message for the field.
     * @return The entered string value.
     */
    public String inputStringField(String prompt) {
        String value;
        boolean isValid = false;
        do {
            System.out.print(prompt);
            value = sc.nextLine();
            if (!value.isEmpty()) {
                isValid = true;
            } else {
                System.out.println("Field must not be empty!");
            }
        } while (!isValid);
        return value;
    }
    
    /**
     * Handles user input for a date field.
     *
     * @param prompt The prompt message for the field.
     * @return The entered date value.
     */
    public LocalDate inputDateField(String prompt) {
        String dateStr;
        boolean isValidInput = false;

        do {
            System.out.print(prompt);
            dateStr = sc.nextLine();

            if (Validator.dateValidate(dateStr)) {
                isValidInput = true;
            } else {
                System.out.println("Invalid date! Please try again.");
            }
        } while (!isValidInput);

        return Converter.strToDate(dateStr);
    }

    /**
     * Handles user input for a positive integer field.
     *
     * @param prompt The prompt message for the field.
     * @return The entered integer value.
     */
    public int inputPositiveIntegerField(String prompt) {
        int value = -1;
        boolean isValidInput = false;
        do {
            System.out.print(prompt);
            String valueStr = sc.nextLine();

            if (Validator.validateNumber(valueStr)) {
                value = Integer.parseInt(valueStr);

                if (value > 0) {
                    isValidInput = true;
                } else {
                    System.out.println("Value must be a positive number.");
                }
            } else {
                System.out.println("Invalid input format! Please enter a valid number.");
            }
        } while (!isValidInput);

        return value;
    }
    /**
     * Handles user input for a positive double field.
     *
     * @param prompt The prompt message for the field.
     * @return The entered double value.
     */
    public double inputPositiveDoubleField(String prompt) {
        double value = -1;
        boolean isValidInput = false;
        do {
            System.out.print(prompt);
            String valueStr = sc.nextLine();

            if (Validator.validatePositiveDouble(valueStr)) {
                value = Double.parseDouble(valueStr);
                if (value > 0) {
                    isValidInput = true;
                } else {
                    System.out.println("Field must be a positive number.");
                }
            } else {
                System.out.println("Invalid input format! Please enter a valid number.");
            }
        } while (!isValidInput);
        return value;
    }
}
