/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer.forms;

import viewer.InputHandler;

/**
 * Form for creating order details.
 * Extends the Form class and provides specific fields for order detail creation.
 * Overrides the `takeUserInput` method to collect user input for creating an order detail.
 * Provides getter methods for accessing the input values.
 * @author ubro3
 */
public class OrderDetailCreationForm extends Form {
    private String flowerID;
    private int quantity;
    
    /**
     * Constructs a new OrderDetailCreationForm object with the specified InputHandler.
     * 
     * @param ih The InputHandler object to use for user input.
     */
    public OrderDetailCreationForm(InputHandler ih) {
        super(ih);
    }
    
    /**
     * Returns the flower ID entered by the user.
     * 
     * @return The flower ID.
     */
    public String getFlowerID() {
        return flowerID;
    }

    /**
     * Returns the quantity entered by the user.
     * 
     * @return The quantity.
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Collects user input for creating an order detail.
     * Prompts the user to enter the flower ID and quantity for the order detail.
     * The input values are stored in the corresponding fields.
     * The method also includes a header indicating the start of the order detail creation process.
     */
    @Override
    public void takeUserInput() {
        System.out.println("\n\n---------- ADD ORDER DETAIL ----------");
        flowerID = ih.inputFlowerID();
        quantity = ih.inputQuantity();
    }
}
