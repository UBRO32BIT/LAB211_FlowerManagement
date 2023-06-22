/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.FlowerSet;
import java.util.LinkedHashMap;
import model.Flower;
import model.Order;
import tools.Converter;

/**
 * This class provides methods to display tables for flowers and orders.
 * @author ubro3
 */
public class TableView {
    /**
     * Displays a table of flowers using LinkedHashMap to represent indexes of objects.
     *
     * @param flowers The FlowerSet containing the flowers to display.
     */
    public static void showFlowerTable(FlowerSet flowers) {
        System.out.println("\n\n-----------------");
        System.out.println("LIST OF FLOWERS");
        System.out.println("-----------------");

        // Format for the table columns
        String format = "%4s %12s %20s %15s %14s %25s\n";

        // Print the column headers
        System.out.printf(format, "No. ", "Flower ID", "Description", "Import Date", "Unit price", "Category");

        int count = 1;
        for (Flower flower : flowers) {
            String importDateStr = Converter.dateToStr(flower.getImportDate());
            System.out.printf(format, count++, flower.getID(), flower.getDescription(), importDateStr,
                    flower.getUnitPrice(), flower.getCategory());
        }
    }

    /**
     * Displays a table of orders using LinkedHashMap to represent indexes of object before sorting.
     *
     * @param orders The LinkedHashMap containing the orders to display.
     */
    public static void showOrderTable(LinkedHashMap<Integer, Order> orders) {
        System.out.println("\n\n-----------------");
        System.out.println("LIST OF ORDERS");
        System.out.println("-----------------");

        // Format for the table columns
        String format = "%4s %8s %16s %25s %15s %15s\n";

        // Print the column headers
        System.out.printf(format, "No. ", "Order ID", "Order Date", "Customer", "Flower Count", "Order Total");

        double totalPrice = 0;
        int totalFlowerCount = 0;
        for (int no : orders.keySet()) {
            Order order = orders.get(no);
            totalPrice += order.getTotalPrice();
            totalFlowerCount += order.getOrderDetailsCount();
            String orderDateStr = Converter.dateToStr(order.getOrderDate());
            System.out.printf(format, no, order.getOrderID(), orderDateStr, order.getCustomerName(),
                    order.getOrderDetailsCount(), order.getTotalPrice()
            );
        }

        // Print the total flower species and total bill of all orders
        System.out.printf(format, "", "Total", "", "", totalFlowerCount, totalPrice);
    }
}
