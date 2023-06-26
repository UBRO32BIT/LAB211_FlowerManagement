/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enums.SortedField;
import enums.SortedOrder;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import model.Flower;
import model.FlowerSet;
import model.Order;
import model.OrderSet;
import view.LogHandler;
import view.TableView;
import view.forms.FlowerCreationForm;
import view.forms.FlowerUpdateForm;
import view.forms.OrderCreationForm;
import view.forms.OrderDetailCreationForm;

/**
 * Handles the management of flowers and orders.
 *
 * It provides methods for adding, updating, and deleting flowers, as well as
 * adding order details, displaying orders, sorting orders, and saving/loading
 * data.
 *
 * @author ubro3
 */
public class FlowerManagement {

    private FlowerSet flowers;
    private OrderSet orders;
    private final FileHandler file;
    private final LogHandler log;

    /**
     * Constructor with the specified file paths.
     *
     * @param flowersPath The path to the flowers data file.
     * @param ordersPath The path to the orders data file.
     * @param logPath The path to the log file.
     */
    public FlowerManagement(String flowersPath, String ordersPath, String logPath) {
        flowers = new FlowerSet();
        orders = new OrderSet();
        file = new FileHandler(flowersPath, ordersPath);
        log = new LogHandler(logPath);
    }

    /**
     * Adds a flower to the flower list based on the provided form.
     *
     * @param form The FlowerCreationForm object containing the flower
     * information.
     */
    public void addFlower(FlowerCreationForm form) {
        boolean result;
        //Create flower object based on the form's data
        Flower flower = new Flower(form.getFlowerID(), form.getDescription(),
                form.getImportDate(), form.getUnitPrice(), form.getCategory()
        );
        result = flowers.add(flower);
        //Print operation result
        if (!result) {
            log.printInfo("Failed to add flower, duplication of flower's ID or description found!");
        } else {
            log.printInfo("Added successfully!");
        }
    }

    /**
     * Finds flowers matching the provided ID or description and displays them
     * in a table.
     *
     * @param queryStr The string to search for.
     */
    public void findFlower(String queryStr) {
        FlowerSet result;
        if (queryStr.equals("@all")) {
            result = flowers;
        } else {
            //Get flowers based on description
            result = flowers.filterByDescription(queryStr);
            //Get a flower based on its ID
            Flower flowerIDQueryResult = flowers.getByID(queryStr);
            //If there is a flower matches with the ID
            if (flowerIDQueryResult != null) {
                result.add(flowerIDQueryResult);
            }
        }

        //If the result is empty, an error is display. Otherwise, show the Flowers table
        if (result.isEmpty()) {
            log.printInfo("Flower not found");
        } else {
            TableView.showFlowerTable(result);
        }
    }

    /**
     * Updates the specified flower based on the provided form. If the flower
     * does not exist, the message “The flower does not exist” is displayed.
     * Otherwise, FlowerUpdateForm is display to let the user edit the flower
     * and updated after submit the form and validate the data
     *
     * @param description The description of the flower to update.
     * @param form The FlowerUpdateForm object containing the necessary
     * attribute to update information.
     */
    public void updateFlower(String description, FlowerUpdateForm form) {
        boolean isValid = true;
        Flower queryFlower = flowers.getByDescription(description);
        // Check if the flower exists
        if (queryFlower == null) {
            log.printErr("The flower does not exist!");
            isValid = false;
        } else {
            // If the flower exists, display the form to let the user edit flower fields
            form.takeUserInput();

            // Check if the description is provided and has duplication with another flower
            if (form.getDescription() != null) {
                if (flowers.containsDescription(form.getDescription())) {
                    isValid = false;
                    log.printErr("Found duplication with another flower's description");
                }
            }
        }

        // If the query is valid, update the flower
        if (isValid) {
            Flower flower = new Flower(queryFlower.getID(), form.getDescription(), form.getImportDate(), form.getUnitPrice(), form.getCategory());
            flowers.update(flower);
            log.printInfo("Update flower successfully!");
        } else {
            log.printInfo("Update flower failed");
        }
    }

    /**
     * Deletes the flower with the specified ID. If the flower is not found or
     * has already existed in an order detail, the operation will be aborted
     * Otherwise, the flower will be removed using OrderSet removeByID() method
     *
     * @param flowerID The ID of the flower to delete.
     */
    public void deleteFlower(String flowerID) {
        boolean isValid = true;
        //If the flower not found in the FlowerSet collection
        if (!flowers.containsID(flowerID)) {
            isValid = false;
            log.printErr("Flower does not exist!");
        }
        //If the flower has found in an order detail in OrderSet collection
        if (orders.containsFlower(flowerID)) {
            isValid = false;
            log.printErr("The flower has already existed in an order detail!");
        }
        //If the query is valid, delete the flower
        if (isValid) {
            flowers.removeByID(flowerID);
            log.printInfo("Delete flower successfully!");
        } else {
            log.printInfo("Delete flower failed!");
        }
    }

    /**
     * Adds an order detail to the specified order based on the provided form.
     *
     * @param orderID The ID of the order to add the detail to.
     * @param form The OrderDetailCreationForm object containing the order
     * detail information.
     */
    public void addOrderDetail(String orderID, OrderDetailCreationForm form) {
        boolean isValid = true;
        // Retrieve the flower based on the flower ID from the form
        Flower flower = flowers.getByID(form.getFlowerID());
        // Check if the flower exists in the collection
        if (flower == null) {
            isValid = false;
            log.printErr("Flower with ID " + form.getFlowerID() + " not found!");
        }
        // Check if the order already contains the flower in the OrderDetail collection
        if (orders.containsFlower(orderID, form.getFlowerID())) {
            isValid = false;
            log.printErr("The flower with ID " + form.getFlowerID() + " already exist in the order!");
        }
        // If the query is valid, add the order detail
        if (isValid) {
            orders.addOrderDetail(orderID, flower, form.getQuantity());
            log.printInfo("Add order detail successfully!");
        } else {
            log.printInfo("Add order detail failed!");
        }
    }

    /**
     * Adds an order based on the provided form.
     *
     * @param form The OrderCreationForm object containing the order
     * information.
     * @return The result of the operation as boolean
     */
    public boolean addOrder(OrderCreationForm form) {
        boolean isValid = true;
        // Check if the order ID already exists
        if (orders.containsID(form.getId())) {
            isValid = false;
            log.printErr("The order is already exist");
        }
        // If the query is valid, add the order to the collection
        if (isValid) {
            Order order = new Order(form.getId(), form.getOrderDate(), form.getCustomerName());
            orders.add(order);
            log.printInfo("Add order successfully!");
        } else {
            log.printInfo("Add order failed!");
        }
        return isValid;
    }

    /**
     * Deletes the specified order if it has no order details.
     *
     * @param orderID The ID of the order to delete.
     */
    public void deleteOrderIfEmpty(String orderID) {
        boolean result = orders.removeIfEmpty(orderID);
        if (result) {
            log.printInfo("The order with ID " + orderID + " is deleted because there is no order detail in it!");
        }
    }

    /**
     * Displays the orders within the specified date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     */
    public void displayOrders(LocalDate startDate, LocalDate endDate) {
        // Filter 
        OrderSet filteredOrders = orders.filterByDate(startDate, endDate);
        LinkedHashMap<Integer, Order> result = new LinkedHashMap<>();
        int count = 1;
        for (Order order : filteredOrders) {
            result.put(count++, order);
        }
        TableView.showOrderTable(result);
    }

    /**
     * Sorts the orders based on the specified field and order.
     *
     * @param sortedField The field to sort the orders by.
     * @param sortedOrder The order (ascending or descending) to sort the orders
     * in.
     */
    public void sortOrders(SortedField sortedField, SortedOrder sortedOrder) {
        Collection<Order> values = orders;
        ArrayList<Order> check = new ArrayList<>(values);
        ArrayList<Order> result = orders.sort(sortedField, sortedOrder);
        //Initialize the list which has key as the last order of the list
        LinkedHashMap<Integer, Order> resultView = new LinkedHashMap<>();
        //Mapping the sorted list with the last orders, the number should be from 1-[number of patients]
        for (Order order : result) {
            resultView.put(check.indexOf(order) + 1, order);
        }
        //Show the Order table
        TableView.showOrderTable(resultView);
    }

    /**
     * Saves the flower and order data to files.
     */
    public void saveData() {
        try {
            file.writeOrderFile(orders);
            file.writeFlowerFile(flowers);
            log.printInfo("Save data successfully");
        } catch (IOException e) {
            log.printErr(e.getMessage());
            log.printInfo("Save data failed!");
        }
    }

    /**
     * Loads the flower and order data from files.
     */
    public void loadData() {
        try {
            FlowerSet loadedFlowers = file.readFlower();
            if (loadedFlowers != null) {
                flowers = loadedFlowers;
            }

            OrderSet loadedOrders = file.readOrder();
            if (loadedOrders != null) {
                orders = loadedOrders;
            }
            log.printInfo("Load data successfully!");
        } catch (IOException | ClassNotFoundException e) {
            log.printErr(e.getMessage());
            log.printInfo("Load data failed!");
        }
    }
}
