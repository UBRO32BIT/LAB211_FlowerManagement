/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import java.util.Scanner;
import controller.FlowerManagement;
import java.time.LocalDate;
import tool.Converter;

/**
 * Flower Management Program
 * @author ubro3
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // Menu options
        String[] mainOptions = {"Add a flower",
            "Find a flower", "Update a flower",
            "Delete a flower", "Add an order",
            "Display orders", "Sort orders", 
            "Save data", "Load data", "Quit",
        };
        String[] YNOptions = {
            "Yes", "No",
        };
        String [] editOptions = {
            "Edit Flower ID", "Edit description", 
            "Edit import date", "Edit unit price", 
            "Edit category", "Finish editing"
        };
        InputHandler ih = new InputHandler();
        int choice = 0;
        final String fileFlower = "src\\saves\\flowers.dat";
        final String fileOrder = "src\\saves\\orders.dat";
        final String fileLog = "src\\logs\\log.txt";
        FlowerManagement fm = new FlowerManagement(fileLog);
        do {
                System.out.println("\nFlower Management Program");
                choice = Menu.getChoice(mainOptions); // show Menu options
                Scanner sc = new Scanner(System.in);
                // Switch through main menu choices
                switch (choice) {
                    case 1:
                        int continueFlowerChoice = 0;
                        do {
                            fm.addFlower(ih.inputFlowerID(), ih.inputDescription(), 
                                ih.inputImportDate(), ih.inputUnitPrice(), 
                                ih.inputCategory()
                            );
                            System.out.println("Continue adding a flower?");
                            continueFlowerChoice = Menu.getChoice(YNOptions);
                        } while (continueFlowerChoice != 2);
                        break;
                    case 2:
                        System.out.print("Enter the flower ID you want to find: ");
                        String flowerID = sc.nextLine();
                        fm.findFlower(flowerID);
                        break;
                    case 3:
                        String editFlowerID = ih.inputFlowerID();
                        if (!fm.containsFlower(editFlowerID)) {
                            System.out.println("The flower does not exist");
                            break;
                        }
                        // Array of type String with a length of 5, all elements will be set to null as default
                        String[] updatedFields = new String[5];
                        int editChoice;
                        do {
                            editChoice = Menu.getChoice(editOptions);
                            // Switch through edit choices
                            switch (editChoice) {
                                case 1:
                                    updatedFields[0] = ih.inputFlowerID();
                                    break;
                                case 2:
                                    updatedFields[1] = ih.inputDescription();
                                    break;
                                case 3:
                                    updatedFields[2] = Converter.dateToStr(ih.inputImportDate());
                                    break;
                                case 4:
                                    updatedFields[3] = Double.toString(ih.inputUnitPrice());
                                    break;
                                case 5:
                                    updatedFields[4] = ih.inputCategory();
                                    break;
                                case 6:
                                    break;
                            }
                        } while (editChoice != 6);
                        fm.updateFlower(editFlowerID, updatedFields);
                        break;
                    case 4:
                        String deleteFlowerID = ih.inputFlowerID();
                        if (!fm.containsFlower(deleteFlowerID)) {
                            System.out.println("The flower does not exist");
                            break;
                        }
                        if (!ih.inputDeleteFlowerConfirmation(deleteFlowerID, YNOptions)) {
                            break;
                        }
                        fm.deleteFlower(deleteFlowerID);
                        break;
                    case 5:
                        int continueOrderChoice = 0;
                        do {
                            fm.addOrder(ih.inputOrderID(), ih.inputOrderDate(), ih.inputCustomerName(),
                                ih.inputOrderDetails()
                            );
                            System.out.println("Continue adding an order?");
                            continueOrderChoice = Menu.getChoice(YNOptions);
                        } while (continueOrderChoice != 2);
                        break;
                    case 6:
                        fm.displayOrders(ih.inputStartDate(), ih.inputEndDate());
                        break;
                    case 7:
                        fm.sortOrders(ih.inputSortedField(), ih.inputSortOrder());
                        break;
                    case 8:
                        fm.saveData(fileFlower, fileOrder);
                        break;
                    case 9:
                        fm.loadData(fileFlower, fileOrder);
                        break;
                    case 10:
                        System.out.println("Are you sure you want to quit the program?");
                        int exitChoice = Menu.getChoice(YNOptions);
                        if (exitChoice == 1) {
                            fm.saveData(fileFlower, fileOrder);
                            sc.close();
                            System.out.println("Goodbye!");
                            System.exit(0);
                        } else {
                            break;
                        }
                }
                Menu.waitForEnterKey();
        } while (true);
    }
}