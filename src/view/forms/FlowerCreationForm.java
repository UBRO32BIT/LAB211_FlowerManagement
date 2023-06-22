/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms;

import java.time.LocalDate;
import view.InputHandler;

/**
 * Form for creating a new flower.
 * Extends the Form class and provides specific fields for flower object creation.
 * Overrides the `takeUserInput` method to collect user input for creating a flower.
 * Provides getter methods for accessing the input values.
 * @author ubro3
 */
public class FlowerCreationForm extends Form {
    private String flowerID;
    private String description;
    private LocalDate importDate;
    private double unitPrice;
    private String category;
    
    /**
     * Constructs a new FlowerCreationForm object with the specified InputHandler.
     * 
     * @param ih The InputHandler object to use for user input.
     */
    public FlowerCreationForm(InputHandler ih) {
        super(ih, "Create Flower");
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
     * Returns the flower description entered by the user.
     * 
     * @return The flower description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the import date entered by the user.
     * 
     * @return The import date.
     */
    public LocalDate getImportDate() {
        return importDate;
    }

    /**
     * Returns the unit price entered by the user.
     * 
     * @return The unit price.
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Returns the flower category entered by the user.
     * 
     * @return The flower category.
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * Collects user input for creating a flower.
     * Prompts the user to enter the flower ID, description, import date, unit price, and category.
     * The input values are stored in the corresponding fields.
     */
    @Override
    public void takeUserInput() {
        super.printHeader();
        flowerID = ih.inputFlowerID();
        description = ih.inputDescription();
        importDate = ih.inputImportDate();
        unitPrice = ih.inputUnitPrice();
        category = ih.inputCategory();
    }
}
