/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer.forms;

import java.time.LocalDate;
import viewer.InputHandler;

/**
 * Form for creating a new order.
 * Extends the Form class and provides specific fields for order creation.
 * Overrides the `takeUserInput` method to collect user input for creating an order.
 * Provides getter methods for accessing the input values.
 * 
 * The class prompts the user to enter the order ID, order date, and customer name.
 * The input values are stored in the corresponding fields.
 * @author ubro3
 */
public class OrderCreationForm extends Form {
    private String id;
    private LocalDate orderDate;
    private String customerName;
    
    /**
     * Constructs a new OrderCreationForm object with the specified InputHandler.
     * 
     * @param ih The InputHandler object to use for user input.
     */
    public OrderCreationForm(InputHandler ih) {
        super(ih);
    }
    
    /**
     * Returns the order ID entered by the user.
     * 
     * @return The order ID.
     */
    public String getId() {
        return id;
    }
    
    /**
     * Returns the order date entered by the user.
     * 
     * @return The order date.
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }
    
    /**
     * Returns the customer name entered by the user.
     * 
     * @return The customer name.
     */
    public String getCustomerName() {
        return customerName;
    }
    
    /**
     * Collects user input for creating an order.
     * Prompts the user to enter the order ID, order date, and customer name.
     * The input values are stored in the corresponding fields.
     * The method also includes a header indicating the start of the order creation process.
     */
    @Override
    public void takeUserInput() {
        System.out.println("\n\n---------- ADD ORDER ----------");
        id = ih.inputOrderID();
        orderDate = ih.inputOrderDate();
        customerName = ih.inputCustomerName();
    }
}