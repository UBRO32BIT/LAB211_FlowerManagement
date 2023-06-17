/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

/**
 *
 * @author ubro3
 */
public enum PromptType {
    FLOWER_ID("Enter Flower ID: "),
    FLOWER_NAME("Enter flower name: "),
    DESCRIPTION("Enter Flower description: "),
    IMPORT_DATE("Enter Flower import date (dd/MM/yyyy): "),
    UNIT_PRICE("Enter unit price: "),
    CATEGORY("Enter Flower category: "),
    ORDER_ID("Enter Order ID: "),
    ORDER_DATE("Enter Order Date (dd/MM/yyyy): "),
    CUSTOMER_NAME("Enter Customer Name: "),
    QUANTITY("Enter Quantity: "),
    START_DATE("Enter start date (yyyy-MM-dd): "),
    END_DATE("Enter end date (yyyy-MM-dd): "),
    SORT_ORDER("Enter sort order"),
    SORT_FIELD("Input a field to sort"),
    CONTINUE_ADDING_FLOWER("Continue adding a flower?"),
    CONTINUE_ADDING_ORDER("Continue adding an order?"),
    CONTINUE_ADDING_ORDER_DETAIL("Continue adding an order detail?"),
    QUIT_PROGRAM("Are you sure you want to quit the program?");

    private final String prompt;

    PromptType(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
