/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enums.SortedField;
import enums.SortedOrder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import tools.Converter;

/**
 * A collection of Order objects.
 * Extends the HashSet class to provide additional functionality for managing Order objects.
 * Provides methods for searching order by ID, as well as adding, and updating orders.
 * 
 * @author ubro3
 */
public class OrderSet extends HashSet<Order> {
     /**
     * Retrieves an order from the set based on the order ID.
     *
     * @param orderID The ID of the order to retrieve.
     * @return The Order object with the specified order ID, or null if not found.
     */
    public Order getByID(String orderID) {
        Order result = null;
        for (Order order : this) {
            if (order.getOrderID().equals(orderID)) {
                result = order;
            }
        }
        return result;
    }
    /**
     * Checks if the set contains an order with the specified order ID.
     *
     * @param orderID The ID of the order to check.
     * @return true if the set contains the order, false otherwise.
     */
    public boolean containsID(String orderID) {
        Order order = new Order(orderID, null, null, null);
        return super.contains(order);
    }
    /**
     * Updates an existing order object in the set.
     *
     * @param order The updated Order object.
     */
    public void update(Order order) {
        super.remove(order);
        super.add(order);
    }
    /**
     * Removes an order from the set based on the order ID.
     *
     * @param orderID The ID of the order to remove.
     * @return true if the order was successfully removed, false otherwise.
     */
    public boolean removeByID(String orderID) {
        Order order = new Order(orderID, null, null, null);
        boolean result = super.remove(order);
        return result;
    }
    /**
     * Adds an order detail to an existing order in the set.
     *
     * @param orderID   The ID of the order to add the detail to.
     * @param flower    The Flower object representing the flower in the order detail.
     * @param quantity  The quantity of the flower in the order detail.
     */
    public void addOrderDetail(String orderID, Flower flower, int quantity) {
        Order order = this.getByID(orderID);
        String orderDetailID = Converter.convertToFixedLengthString(order.getOrderDetailsCount() + 1, 4);
        OrderDetail od = new OrderDetail(orderDetailID, flower, quantity);
        order.addOrderDetail(od);
        this.update(order);
    }
    /**
     * Checks if the set contains any order with the specified flower ID.
     *
     * @param flowerID The ID of the flower to check.
     * @return true if any order contains the flower, false otherwise.
     */
    public boolean containsFlower(String flowerID) {
        boolean result = false;
        for (Order order : this) {
            if (containsFlower(order.getOrderID(), flowerID)) {
                result = true;
                break;
            }
        }
        return result;
    }
    /**
     * Checks if an order contains a specific flower.
     *
     * @param orderID   The ID of the order to check.
     * @param flowerID  The ID of the flower to check.
     * @return true if the order contains the flower, false otherwise.
     */
    public boolean containsFlower(String orderID, String flowerID) {
        boolean result = false;
        Order order = getByID(orderID);
        for (OrderDetail od : order.getOrderDetails()) {
            if (od.getFlowerID().equals(flowerID)) {
                result = true;
                break;
            }
        }
        return result;
    }
    /**
     * Removes an order from the set if it has no order details.
     *
     * @param orderID The ID of the order to check and potentially remove.
     * @return true if the order was removed, false otherwise.
     */
    public boolean removeIfEmpty(String orderID) {
        boolean result = false;
        Order order = getByID(orderID);
        if (order.getOrderDetails().isEmpty()) {
            result = removeByID(orderID);
        }
        return result;
    }
    /**
     * Filters the set of orders based on a date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return An OrderSet containing orders within the specified date range.
     */
    public OrderSet filterByDate(LocalDate startDate, LocalDate endDate) {
        OrderSet filteredOrders = new OrderSet();
        for (Order order : this) {
            if (order.getOrderDate().compareTo(startDate) >= 0 && order.getOrderDate().compareTo(endDate) <= 0) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
    /**
     * Sorts the set of orders based on a specified field and order.
     *
     * @param sortedField  The field to sort the orders by.
     * @param sortedOrder  The order (ascending or descending) to sort the orders in.
     * @return An ArrayList containing the sorted orders.
     */
    public ArrayList<Order> sort(SortedField sortedField, SortedOrder sortedOrder) {
            ArrayList<Order> sorted = new ArrayList<Order>(this);
            Collections.sort(sorted, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                switch (sortedField) {
                    case ORDER_ID:
                       return o1.getOrderID().compareTo(o2.getOrderID());
                    case ORDER_DATE:
                        return o1.getOrderDate().compareTo(o2.getOrderDate());
                    case CUSTOMER_NAME:
                        return o1.getCustomerName().compareTo(o2.getCustomerName());
                    case ORDER_TOTAL:
                        return Double.compare(o1.getTotalPrice(), o2.getTotalPrice());
                    default:
                        return 0;
                }
            }
        });
        //If sort at descending order
        if (sortedOrder.equals(SortedOrder.DESCENDING)) {
            Collections.reverse(sorted);
        }
        return sorted;
    }
}
