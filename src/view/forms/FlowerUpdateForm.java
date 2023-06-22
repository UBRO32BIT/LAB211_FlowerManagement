/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms;

import java.time.LocalDate;
import view.InputHandler;
import view.Menu;

/**
 * Form for updating an existing flower.
 * Extends the Form class and provides specific fields for flower update.
 * Overrides the `takeUserInput` method to collect user input for updating a flower.
 * Provides getter methods for accessing the input values.
 * 
 * The class also includes a menu for selecting which field to update.
 * The user can choose to update the description, import date, unit price, or category.
 * 
 * The editing process continues until the user chooses to finish editing.
 * 
 * Note: The `unitPrice` field is initialized with a default value of -1 and is not set by default.
 * If the user chooses to update the unit price, it will be set accordingly.
 * @author ubro3
 */
public class FlowerUpdateForm extends Form {
    private String description;
    private LocalDate importDate;
    private double unitPrice;
    private String category;

    /**
     * Constructs a new FlowerUpdateForm object with the specified InputHandler.
     * 
     * @param ih The InputHandler object to use for user input.
     */
    public FlowerUpdateForm(InputHandler ih) {
        super(ih, "Update Flower");
        unitPrice = -1;
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
     * Collects user input for updating a flower.
     * Displays a menu for selecting which field to update (description, import date, unit price, or category).
     * The input values are stored in the corresponding fields.
     * The editing process continues until the user chooses to finish editing.
     */
    @Override
    public void takeUserInput() {
        String[] editOptions = {
            "Edit description",
            "Edit import date",
            "Edit unit price",
            "Edit category",
            "Finish editing"
        };
        int choice;
        do {
            super.printHeader();
            choice = Menu.getChoice(editOptions);
            switch (choice) {
                case 1:
                    description = ih.inputDescription();
                    break;
                case 2:
                    importDate = ih.inputImportDate();
                    break;
                case 3:
                    unitPrice = ih.inputUnitPrice();
                    break;
                case 4:
                    category = ih.inputCategory();
                    break;
            }
        }
        while (choice != 5);
    }
}
