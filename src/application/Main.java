/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.Scanner;
import controller.FlowerManagement;
import viewer.InputHandler;
import viewer.Menu;
import viewer.forms.FlowerCreationForm;
import viewer.forms.FlowerUpdateForm;
import viewer.forms.OrderCreationForm;
import viewer.forms.OrderDetailCreationForm;

/**
 * Flower Management Program
 *
 * @author ubro3
 */
public class Main {
    /**
     * The main method that starts the Flower Management Program.
     * 
     * @param args The command line arguments.
     * @throws Exception If an error occurs while running the program.
     */
    public static void main(String[] args) throws Exception {
        // Menu options
        String[] mainOptions = {"Add a flower",
            "Find a flower", "Update a flower",
            "Delete a flower", "Add an order",
            "Display orders", "Sort orders",
            "Save data", "Load data", "Quit",
        };
        InputHandler ih = new InputHandler();
        int choice = 0;
        final String fileFlower = "src\\saves\\flowers.dat";
        final String fileOrder = "src\\saves\\orders.dat";
        final String fileLog = "src\\logs\\log.txt";
        FlowerManagement fm = new FlowerManagement(fileFlower, fileOrder, fileLog);
        boolean isExited = false;
        do {
            System.out.println("\nFlower Management Program");
            choice = Menu.getChoice(mainOptions); // show Menu options
            Scanner sc = new Scanner(System.in);
            // Switch through main menu choices
            switch (choice) {
                case 1:
                    FlowerCreationForm flowerCreationForm = new FlowerCreationForm(ih);
                    do {
                        flowerCreationForm.takeUserInput();
                        fm.addFlower(flowerCreationForm);
                    } while (ih.inputAddFlowerConfirmation());
                    break;
                case 2:
                    fm.findFlower(ih.inputDescription());
                    break;
                case 3:
                    String editFlowerID = ih.inputFlowerID();
                    FlowerUpdateForm flowerUpdateForm = new FlowerUpdateForm(ih);
                    fm.updateFlower(editFlowerID, flowerUpdateForm);
                    break;
                case 4:
                    String deleteFlowerID = ih.inputFlowerID();
                    if (ih.inputDeleteFlowerConfirmation(deleteFlowerID)) {
                        fm.deleteFlower(deleteFlowerID);
                    }
                    break;
                case 5:
                    OrderCreationForm orderForm = new OrderCreationForm(ih);
                    OrderDetailCreationForm orderDetailForm = new OrderDetailCreationForm(ih);
                    do {
                        orderForm.takeUserInput();
                        String orderID = orderForm.getId();
                        fm.addOrder(orderForm);
                        do {
                            orderDetailForm.takeUserInput();
                            fm.addOrderDetail(orderID, orderDetailForm);
                        } while (ih.inputAddOrderDetailConfirmation());
                        fm.deleteOrderIfEmpty(orderID);
                    } while (ih.inputAddOrderConfirmation());
                    break;
                case 6:
                    fm.displayOrders(ih.inputStartDate(), ih.inputEndDate());
                    break;
                case 7:
                    fm.sortOrders(ih.inputSortedField(), ih.inputSortOrder());
                    break;
                case 8:
                    fm.saveData();
                    break;
                case 9:
                    fm.loadData();
                    break;
                case 10:
                    if (ih.inputExitConfirmation()) {
                        isExited = true;
                    }
            }
            Menu.waitForEnterKey();
        } while (!isExited);
        fm.saveData();
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
