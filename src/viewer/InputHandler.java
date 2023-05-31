/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import model.OrderDetail;
import tool.Converter;
import tool.Validator;

/**
 *
 * @author ubro3
 */
public class InputHandler {
    private final Scanner sc;

    public InputHandler() {
        sc = new Scanner(System.in);
    }

    /**
     * Handles user input for Flower ID.
     * @return The entered Flower ID as a string.
     */
    public String inputFlowerID() {
        String id;
        do {
            System.out.print("Enter Flower ID: ");
            id = sc.nextLine();
            if (!id.isEmpty()) {
                return id;
            }
            System.out.println("Flower ID must not be empty!");
        } while (true);
    }

    /**
     * Handles user input for Flower description.
     * @return The entered Flower description as a string.
     */
    public String inputDescription() {
        String description;
        do {
            System.out.print("Enter Flower description: ");
            description = sc.nextLine();
            if (!description.isEmpty()) {
                return description;
            }
            System.out.println("Flower description must not be empty!");
        } while (true);
    }

    /**
     * Handles user input for Flower import date.
     * @return The entered import date as a string.
     */
    public LocalDate inputImportDate() {
        String importDate;
        do {
            System.out.print("Enter Flower import date (dd/MM/yyyy): ");
            importDate = sc.nextLine();
            if (Validator.dateValidate(importDate)) {
                return Converter.strToDate(importDate);
            }
            System.out.println("Invalid date! Please try again.");
        } while (true);
    }

    /**
     * Handles user input for the unit price.
     * @return The entered unit price as a double.
     */
    public double inputUnitPrice() {
        double unitPrice;
        do {
            System.out.print("Enter Unit Price: ");
            String unitPriceStr = sc.nextLine();
            if (Validator.validateDouble(unitPriceStr)) {
                unitPrice = Double.parseDouble(unitPriceStr);
                if (unitPrice > 0) {
                    return unitPrice;
                }
                System.out.println("Unit Price must be a positive number.");
            } else {
                System.out.println("Invalid input format! Please enter a valid number.");
            }
        } while (true);
    }

    /**
     * Handles user input for Flower category.
     * @return The entered Flower category as a string.
     */
    public String inputCategory() {
        String category;
        do {
            System.out.print("Enter Flower category: ");
            category = sc.nextLine();
            if (!category.isEmpty()) {
                return category;
            }
            System.out.println("Flower category must not be empty!");
        } while (true);
    }
    /**
     * Handles user input for the order ID.
     * @return The entered order ID as a string.
     */
    public String inputOrderID() {
        String orderID;
        do {
            System.out.print("Enter Order ID: ");
            orderID = sc.nextLine();
            if (!orderID.isEmpty()) {
                return orderID;
            }
            System.out.println("Order ID must not be empty!");
        } while (true);
    }

    /**
     * Handles user input for the order date.
     * @return The entered order date as a string.
     */
    public LocalDateTime inputOrderDate() {
        String orderDate;
        do {
            System.out.print("Enter Order Date (dd/MM/yyyy) (Leave blank to input current date): ");
            orderDate = sc.nextLine();
            if (orderDate.isEmpty()) {
                return LocalDateTime.now();
            }
            if (Validator.dateValidate(orderDate)) {
                return Converter.strToDate(orderDate).atStartOfDay();
            }
            System.out.println("Invalid date! Please try again.");
        } while (true);
    }

    /**
     * Handles user input for the customer name.
     * @return The entered customer name as a string.
     */
    public String inputCustomerName() {
        String customerName;
        do {
            System.out.print("Enter Customer Name: ");
            customerName = sc.nextLine();
            if (!customerName.isEmpty()) {
                return customerName;
            }
            System.out.println("Customer Name must not be empty!");
        } while (true);
    }

    /**
     * Handles user input for the order detail ID.
     * @return The entered order detail ID as a string.
     */
    public String inputOrderDetailID() {
        String orderDetailID;
        do {
            System.out.print("Enter Order Detail ID: ");
            orderDetailID = sc.nextLine();
            if (!orderDetailID.isEmpty()) {
                return orderDetailID;
            }
            System.out.println("Order Detail ID must not be empty!");
        } while (true);
    }
    /**
     * Handles user input for the quantity.
     * @return The entered quantity as an integer.
     */
    public int inputQuantity() {
        int quantity;
        do {
            System.out.print("Enter Quantity: ");
            String quantityStr = sc.nextLine();
            if (Validator.validateNumber(quantityStr)) {
                quantity = Integer.parseInt(quantityStr);
                if (quantity > 0) {
                    return quantity;
                }
                System.out.println("Quantity must be a positive number.");
            } else {
                System.out.println("Invalid input format! Please enter a valid number.");
            }
        } while (true);
    }
    public HashMap<String, OrderDetail> inputOrderDetails() {
        HashMap<String, OrderDetail> orderDetails = new HashMap<String, OrderDetail>();
        do {
            String[] YNOptions = {
                "Yes", "No"
            };
            OrderDetail od = inputOrderDetail();
            orderDetails.put(od.getOrderDetailID(), od);
            System.out.println("Continue adding order details?");
            int choice = Menu.getChoice(YNOptions);
            if (choice == 2) return orderDetails;
        }
        while(true);
    }
    public OrderDetail inputOrderDetail() {
        OrderDetail od = new OrderDetail(inputOrderDetailID(), inputFlowerID(), inputQuantity());
        return od;
    }
    /**
    * Handles user input for the start date.
    * 
    * @return The entered start date as a string.
    */
    public String inputStartDate() {
        String startDate;
        do {
            System.out.print("Enter start date: ");
            startDate = sc.nextLine();
            if (Validator.dateValidate(startDate)) {
                return startDate;
            }
            System.out.println("Invalid date! Please try again.");
        }
        while (true);
    }
    /**
    * Handles user input for the end date.
    * 
    * @return The entered end date as a string.
    */
    public String inputEndDate() {
        String endDate;
        do {
            System.out.print("Enter end date: ");
            endDate = sc.nextLine();
            if (Validator.dateValidate(endDate)) {
                return endDate;
            }
            System.out.println("Invalid date! Please try again.");
        }
        while (true);
    }
    /**
     * Handles user input on sorted fields for patient data.
     * @return True if the sorted field is "patient id", false if it is "patient's name".
     */
    public int inputSortedField() {
        String[] sortedFieldOptions = {
                "Order id", "Order date", "Customer name", "Order total"
            };
        System.out.print("Enter a sorted field: ");
        int choice = Menu.getChoice(sortedFieldOptions);
        return choice;
    }
    /**
    * Handles user input to determine the sort order for patient data.
    * 
    * @return True if the sort order is descending (DESC), false if it is ascending (ASC).
    */
    public String inputSortOrder() {
        String order;
        do {
            System.out.print("Enter sort order (ASC, DESC): ");
            order = sc.nextLine();
            if (order.equalsIgnoreCase("ASC") || order.equalsIgnoreCase("DESC")) {
                return order;
            }
            System.out.println("Invalid sort order!");
        }
        while (true);
    }
    /**
    * Handles user input for confirming the deletion of a nurse.
    * 
    * @param staffID The Staff ID of the nurse to be deleted.
    * @param YNOptions An array of options for user selection (Yes or No).
    * @return True if the user confirms the deletion, false otherwise.
    */
    public boolean inputDeleteFlowerConfirmation(String flowerID, String[] YNOptions) {
        System.out.println("Are you sure to delete flower with ID " + flowerID + "?");
        int choice = Menu.getChoice(YNOptions);
        return (choice == 1);
    }
}