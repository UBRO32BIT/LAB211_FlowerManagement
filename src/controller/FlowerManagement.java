/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.time.LocalDate;
import model.Flower;
import model.FlowerList;
import model.Order;
import model.OrderList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import tool.Validator;
import viewer.LogHandler;
import viewer.TableView;
import viewer.forms.FlowerCreationForm;
import viewer.forms.FlowerUpdateForm;
import viewer.forms.OrderCreationForm;
import viewer.forms.OrderDetailCreationForm;

/**
 * Handles the management of flowers and orders.
 * 
 * It provides methods for adding, updating, and deleting flowers, as well as
 * adding order details, displaying orders, sorting orders, and saving/loading data.
 * 
 * @author ubro3
 */
public class FlowerManagement {
    
    private FlowerList flowers;
    private OrderList orders;
    private final FileHandler file;
    private final LogHandler log;
    /**
     * Constructs a FlowerManagement object with the specified file paths.
     * 
     * @param flowersPath The path to the flowers data file.
     * @param ordersPath The path to the orders data file.
     * @param logPath The path to the log file.
    */
    public FlowerManagement(String flowersPath, String ordersPath, String logPath) {
        flowers = new FlowerList();
        orders = new OrderList();
        file = new FileHandler(flowersPath, ordersPath);
        log = new LogHandler(logPath);
    }
    /**
     * Adds a flower to the flower list based on the provided form.
     * 
     * @param form The FlowerCreationForm object containing the flower information.
     */
    public void addFlower(FlowerCreationForm form) {
        boolean result = true;
        if (!Validator.validateFlowerID(form.getFlowerID())) {
            result = false;
            log.printErr("Invalid flower ID (Must be four digit number)!");
        }
        Flower flower = new Flower(form.getFlowerID(), form.getDescription(), 
                form.getImportDate(), form.getUnitPrice(), form.getCategory()
            );
        flowers.add(flower);
        
        if (!result) {
            log.printInfo("Failed to add flower!");
        }
        else log.printInfo("Added successfully!");
    }
    /**
     * Finds flowers matching the provided description and displays them in a table.
     * 
     * @param description The description to search for.
     */
    public void findFlower(String description) {
        FlowerList result = flowers.filterByDescription(description);
        if (result.isEmpty()) {
            log.printErr("Flower not found");
        }
        else TableView.showFlowerTable(result);
    }
    /**
     * Updates the specified flower based on the provided form.
     * 
     * @param flowerID The ID of the flower to update.
     * @param form The FlowerUpdateForm object containing the updated flower information.
     */
    public void updateFlower(String flowerID, FlowerUpdateForm form) {
        boolean result = true;
        // Get flower to update
        Flower flower = flowers.getByID(flowerID);
        if (flower == null) {
            log.printErr("The flower does not exist!");
            result = false;
        }
        else {
            form.takeUserInput();
            // Update flower fields if requested fields are given
            if (form.getDescription() != null) {
                if (!flowers.filterByDescription(form.getDescription()).isEmpty()) {
                    result = false;
                    log.printErr("Found duplication with another flower's description");
                }
                else flower.setDescription(form.getDescription());
            }
            if (form.getImportDate() != null)
                flower.setImportDate(form.getImportDate());
            if (form.getUnitPrice() >= 0)
                flower.setUnitPrice(form.getUnitPrice());
            if (form.getCategory() != null)
                flower.setCategory(form.getCategory());
        }
        // Put the updated flower into FlowerList
        if (result) {
            flowers.remove(flower);
            flowers.add(flower);
            log.printInfo("Update flower with ID " + flowerID + " successfully!");
        }
        else log.printInfo("Failed to update flower!");
    }
    /**
     * Deletes the flower with the specified ID.
     * 
     * @param flowerID The ID of the flower to delete.
     */
    public void deleteFlower(String flowerID) {
        boolean isValid = true;
        if (!flowers.containsID(flowerID)) {
            isValid = false;
            log.printErr("Flower does not exist!");
        }
        if (orders.containsFlower(flowerID)) {
            isValid = false;
            log.printErr("The flower has already existed in an order detail!");
        }
        if (isValid) {
            flowers.removeByID(flowerID);
            log.printInfo("Delete flower successfully!");
        }
        else {
            log.printInfo("Delete flower failed!");
        }
    }
    /**
     * Adds an order detail to the specified order based on the provided form.
     * 
     * @param orderID The ID of the order to add the detail to.
     * @param form The OrderDetailCreationForm object containing the order detail information.
     */
    public void addOrderDetail(String orderID, OrderDetailCreationForm form) {
        boolean isValid = true;
        Flower flower = flowers.getByID(form.getFlowerID());
        if (flower == null) {
            isValid = false;
            log.printErr("Flower with ID " + form.getFlowerID() + " not found!");
        }
        if (orders.containsFlower(orderID, form.getFlowerID())) {
            isValid = false;
            log.printErr("The flower with ID " + form.getFlowerID() + " already exist in the order!");
        }
        if (isValid) {
            orders.addOrderDetail(orderID, flower, form.getQuantity());
            log.printInfo("Add order detail successfully!");
        }
        else {
            log.printInfo("Add order detail failed!");
        }
    }
    /**
     * Adds an order based on the provided form.
     * 
     * @param form The OrderCreationForm object containing the order information.
     * @throws Exception If an error occurs while adding the order.
     */
    public void addOrder(OrderCreationForm form) throws Exception {
        boolean isValid = true;
        if (orders.containsKey(form.getId())) {
            isValid = false;
            log.printErr("The order is already exist");
        }
        if (isValid) {
            Order order = new Order(form.getId(), form.getOrderDate(), form.getCustomerName());
            orders.put(order.getOrderID(), order);
            log.printInfo("Add order successfully!");
        }
        else {
            log.printInfo("Add order failed!");
        }
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
        OrderList filteredOrders = orders.filterByDate(startDate, endDate);
        LinkedHashMap<Integer, Order> result = new LinkedHashMap<Integer, Order>();
        int count = 1;
        for (Order order : filteredOrders.values()) {
            result.put(count++, order);
        }
        TableView.showOrderTable(result);
    }
    /**
     * Sorts the orders based on the specified field and order.
     * 
     * @param sortedField The field to sort the orders by.
     * @param sortedOrder The order (ascending or descending) to sort the orders in.
     */
    public void sortOrders(SortedField sortedField, SortedOrder sortedOrder) {
        Collection<Order> values = orders.values();
        ArrayList<Order> check = new ArrayList<>(values);
        ArrayList<Order> result = orders.sort(sortedField, sortedOrder);
        //Initialize the list which has key as the last order of the list
        LinkedHashMap<Integer, Order> resultView = new LinkedHashMap<>();
        //mapping the sorted list with the last orders, the number should be from 1-[number of patients]
        for (Order order: result) {
            resultView.put(check.indexOf(order)+1, order);
        }
        //Show the Patients table
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
        }
        catch (IOException e) {
            log.printErr(e.getMessage());
            log.printInfo("Save data failed!");
        }
    }
    /**
     * Loads the flower and order data from files.
     */
    public void loadData() {
        try {
            flowers = file.readFlower();
            orders = file.readOrder();
            log.printInfo("Load data successfully!");
        }
        catch (IOException | ClassNotFoundException e) {
            log.printErr(e.getMessage());
            log.printInfo("Load data failed!");
        }
    }
}
