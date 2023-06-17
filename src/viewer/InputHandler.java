/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import controller.SortedField;
import controller.SortedOrder;
import java.time.LocalDate;
import java.util.Scanner;
import tool.Validator;

/**
 *
 * @author ubro3
 */
public class InputHandler {
    private final Scanner sc;
    private final InputUtils inputUtils;
    private final String[] YNOptions = {
            "Yes", "No"
        };

    public InputHandler() {
        sc = new Scanner(System.in);
        inputUtils = new InputUtils(sc);
    }

    /**
     * Handles user input for Flower ID.
     *
     * @return The entered Flower ID as a string.
     */
    public String inputFlowerID() {
        String result;
        boolean isValid;
        do {
            result = inputUtils.inputStringField(PromptType.FLOWER_ID.getPrompt());
            isValid = Validator.validateFlowerID(result);
            if (!isValid) {
                System.out.println("Invalid flower ID (Must be four digit number)!");
            }
        }
        while (!isValid);
        
        return result;
    }

    /**
     * Handles user input for Flower name.
     *
     * @return The entered Flower name as a string.
     */
    public String inputName() {
        return inputUtils.inputStringField(PromptType.FLOWER_NAME.getPrompt());
    }

    /**
     * Handles user input for Flower description.
     *
     * @return The entered Flower description as a string.
     */
    public String inputDescription() {
        String result;
        boolean isValid;
        do {
            result = inputUtils.inputStringField(PromptType.DESCRIPTION.getPrompt());
            isValid = Validator.validateDescription(result);
            if (!isValid) {
                System.out.println("Invalid description (Must be from 3-50 characters)");
            }
        }
        while (!isValid);
        return result;
    }

    /**
     * Handles user input for Flower import date.
     *
     * @return The entered import date as a LocalDate.
     */
    public LocalDate inputImportDate() {
        return inputUtils.inputDateField(PromptType.IMPORT_DATE.getPrompt());
    }

    /**
     * Handles user input for the unit price.
     *
     * @return The entered unit price as a double.
     */
    public double inputUnitPrice() {
        return inputUtils.inputPositiveDoubleField(PromptType.UNIT_PRICE.getPrompt());
    }

    /**
     * Handles user input for Flower category.
     *
     * @return The entered Flower category as a string.
     */
    public String inputCategory() {
        return inputUtils.inputStringField(PromptType.CATEGORY.getPrompt());
    }

    /**
     * Handles user input for the order ID.
     *
     * @return The entered order ID as a string.
     */
    public String inputOrderID() {
        String result;
        boolean isValid;
        do {
            result = inputUtils.inputStringField(PromptType.ORDER_ID.getPrompt());
            isValid = Validator.validateOrderID(result);
            if (!isValid) {
                System.out.println("Invalid order ID (Must be a four digit number)!");
            }
        }
        while (!isValid);
        return result;
    }

    /**
     * Handles user input for the order date.
     *
     * @return The entered order date as a LocalDate.
     */
    public LocalDate inputOrderDate() {
        return inputUtils.inputDateField(PromptType.ORDER_DATE.getPrompt());
    }

    /**
     * Handles user input for the customer name.
     *
     * @return The entered customer name as a string.
     */
    public String inputCustomerName() {
        return inputUtils.inputStringField(PromptType.CUSTOMER_NAME.getPrompt());
    }

    /**
     * Handles user input for the quantity.
     *
     * @return The entered quantity as an integer.
     */
    public int inputQuantity() {
        return inputUtils.inputPositiveIntegerField(PromptType.QUANTITY.getPrompt());
    }

    /**
     * Handles user input for the start date.
     *
     * @return The entered start date as a LocalDate.
     */
    public LocalDate inputStartDate() {
        return inputUtils.inputDateField(PromptType.START_DATE.getPrompt());
    }

    /**
     * Handles user input for the end date.
     *
     * @return The entered end date as a LocalDate.
     */
    public LocalDate inputEndDate() {
        return inputUtils.inputDateField(PromptType.END_DATE.getPrompt());
    }
    public SortedOrder inputSortOrder() {
        SortedOrder[] sortedOrders = SortedOrder.values();
        System.out.println(PromptType.SORT_ORDER.getPrompt());
        int choice = Menu.getChoice(sortedOrders);
        return sortedOrders[choice - 1];
    }
    public SortedField inputSortedField() {
        SortedField[] sortedFields = SortedField.values();
        System.out.println(PromptType.SORT_FIELD.getPrompt());
        int choice = Menu.getChoice(sortedFields);
        return sortedFields[choice - 1];
    }
    public boolean inputAddFlowerConfirmation() {
        System.out.println(PromptType.CONTINUE_ADDING_FLOWER.getPrompt());
        int choice = Menu.getChoice(YNOptions);
        return (choice == 1);
    }
    /**
     * Handles user input for confirming the deletion of a flower.
     *
     * @param flowerID The ID of the flower to be deleted.
     * @return True if the user confirms the deletion, false otherwise.
     */
    public boolean inputDeleteFlowerConfirmation(String flowerID) {
        System.out.println("Are you sure you want to delete flower with ID " + flowerID + "?");
        int choice = Menu.getChoice(YNOptions);
        return (choice == 1);
    }
    public boolean inputAddOrderDetailConfirmation() {
        System.out.println(PromptType.CONTINUE_ADDING_ORDER_DETAIL.getPrompt());
        int choice = Menu.getChoice(YNOptions);
        return (choice == 1);
    }
    public boolean inputAddOrderConfirmation() {
        System.out.println(PromptType.CONTINUE_ADDING_ORDER.getPrompt());
        int choice = Menu.getChoice(YNOptions);
        return (choice == 1);
    }
    public boolean inputExitConfirmation() {
        System.out.println(PromptType.QUIT_PROGRAM.getPrompt());
        int choice = Menu.getChoice(YNOptions);
        return (choice == 1);
    }
}