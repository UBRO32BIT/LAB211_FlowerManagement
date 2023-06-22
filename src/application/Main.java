/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.FlowerManagement;
import view.InputHandler;
import view.Menu;
import view.forms.FlowerCreationForm;
import view.forms.FlowerUpdateForm;
import view.forms.OrderCreationForm;
import view.forms.OrderDetailCreationForm;

/**
 * Flower Management Program - SE173255
 *
 * @author ubro3
 */
public class Main {
    private enum MenuOption {
        ADD_FLOWER,
        FIND_FLOWER,
        UPDATE_FLOWER,
        DELETE_FLOWER,
        ADD_ORDER,
        DISPLAY_ORDERS,
        SORT_ORDERS,
        SAVE_DATA,
        LOAD_DATA,
        QUIT;
        
        @Override
        public String toString() {
        switch (this) {
        case ADD_FLOWER:
            return "Add a flower";
        case FIND_FLOWER:
            return "Find flower";
        case UPDATE_FLOWER:
            return "Update a flower";
        case DELETE_FLOWER:
            return "Delete a flower";
        case ADD_ORDER:
            return "Add an order";
        case DISPLAY_ORDERS:
            return "Display orders";
        case SORT_ORDERS:
            return "Sort orders";
        case SAVE_DATA:
            return "Save data";
        case LOAD_DATA:
            return "Load data";
        case QUIT:
            return "Quit";
        default:
            return null;
    }
}
    }
    /**
     * The main method that starts the Flower Management Program.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        InputHandler ih = new InputHandler();
        int choice = 0;
        final String fileFlower = "src\\saves\\flowers.dat";
        final String fileOrder = "src\\saves\\orders.dat";
        final String fileLog = "src\\logs\\log.txt";
        FlowerManagement fm = new FlowerManagement(fileFlower, fileOrder, fileLog);
        boolean isExited = false;
        do {
            System.out.println("\nFlower Management Program");
            choice = Menu.getChoice(Menu.getEnumStringValues(MenuOption.class)); // show Menu options
            // Switch through main menu choices
            switch (MenuOption.values()[choice - 1]) {
                case ADD_FLOWER:
                    //Create form object to create flower
                    FlowerCreationForm flowerCreationForm = new FlowerCreationForm(ih);
                    do {
                        //Display the form for inputting
                        flowerCreationForm.takeUserInput();
                        fm.addFlower(flowerCreationForm);
                    } while (ih.inputAddFlowerConfirmation());
                    break;
                case FIND_FLOWER:
                    fm.findFlower(ih.inputFlowerSearchingString());
                    break;
                case UPDATE_FLOWER:
                    String description = ih.inputDescription();
                    FlowerUpdateForm flowerUpdateForm = new FlowerUpdateForm(ih);
                    fm.updateFlower(description, flowerUpdateForm);
                    break;
                case DELETE_FLOWER:
                    String deleteFlowerID = ih.inputFlowerID();
                    if (ih.inputDeleteFlowerConfirmation(deleteFlowerID)) {
                        fm.deleteFlower(deleteFlowerID);
                    }
                    break;
                case ADD_ORDER:
                    OrderCreationForm orderForm = new OrderCreationForm(ih);
                    OrderDetailCreationForm orderDetailForm = new OrderDetailCreationForm(ih);
                    //Loop to show order input and add orders
                    do {
                        orderForm.takeUserInput();
                        String orderID = orderForm.getId();
                        boolean result = fm.addOrder(orderForm);
                        //If the order is created successfully, add order details
                        if (result) {
                            do {
                                orderDetailForm.takeUserInput();
                                fm.addOrderDetail(orderID, orderDetailForm);
                            } while (ih.inputAddOrderDetailConfirmation());
                            //Delete order if it has no OrderDetails to ensure data integrity
                            fm.deleteOrderIfEmpty(orderID);
                        }
                    } while (ih.inputAddOrderConfirmation());
                    break;
                case DISPLAY_ORDERS:
                    fm.displayOrders(ih.inputStartDate(), ih.inputEndDate());
                    break;
                case SORT_ORDERS:
                    fm.sortOrders(ih.inputSortedField(), ih.inputSortOrder());
                    break;
                case SAVE_DATA:
                    fm.saveData();
                    break;
                case LOAD_DATA:
                    fm.loadData();
                    break;
                case QUIT:
                    if (ih.inputExitConfirmation()) {
                        isExited = true;
                    }
            }
            //If the user choose quit, no need to wait for press Enter key
            if (!isExited) {
                Menu.waitForEnterKey();
            }
        } while (!isExited);
        //Save data before exiting the program
        fm.saveData();
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
