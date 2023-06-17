/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.SortedField;
import controller.SortedOrder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import tool.Converter;

/**
 *
 * @author ubro3
 */
public class OrderList extends HashMap<String, Order> {
    public void addOrderDetail(String orderID, Flower flower, int quantity) {
        Order order = super.get(orderID);
        String orderDetailID = Converter.convertToFixedLengthString(order.getOrderDetails().size() + 1, 3);
        OrderDetail od = new OrderDetail(orderDetailID, flower, quantity);
        order.addOrderDetail(od);
        super.put(orderID, order);
    }
    public boolean containsFlower(String flowerID) {
        boolean result = false;
        for (Order order : values()) {
            if (containsFlower(order.getOrderID(), flowerID)) {
                result = true;
                break;
            }
        }
        return result;
    }
    public boolean containsFlower(String orderID, String flowerID) {
        boolean result = false;
        Order order = get(orderID);
        for (OrderDetail od : order.getOrderDetails()) {
            if (od.getFlowerID().equals(flowerID)) {
                result = true;
                break;
            }
        }
        return result;
    }
    public boolean removeIfEmpty(String orderID) {
        boolean result = false;
        Order order = get(orderID);
        if (order.getOrderDetails().isEmpty()) {
            remove(orderID);
            result = true;
        }
        return result;
    }
    public OrderList filterByDate(LocalDate startDate, LocalDate endDate) {
        OrderList filteredOrders = new OrderList();
        for (Order order : values()) {
            if (order.getOrderDate().compareTo(startDate) >= 0 && order.getOrderDate().compareTo(endDate) <= 0) {
                filteredOrders.put(order.getOrderID(), order);
            }
        }
        return filteredOrders;
    }
    
    public ArrayList<Order> sort(SortedField sortedField, SortedOrder sortedOrder) {
                ArrayList<Order> sorted = new ArrayList<Order>(values());
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
        if (sortedOrder.equals(SortedOrder.DESCENDING)) {
            Collections.reverse(sorted);
        }
        return sorted;
    }
}
