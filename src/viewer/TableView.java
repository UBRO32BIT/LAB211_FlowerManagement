/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import model.FlowerList;
import java.util.LinkedHashMap;
import model.Flower;
import model.Order;
import tool.Converter;

/**
 *
 * @author ubro3
 */
public class TableView {
    public static void showFlowerTable(FlowerList flowers) {
        System.out.println("\n\n-----------------");
        System.out.println("LIST OF FLOWERS");
        System.out.println("-----------------");

        String format = "%4s %12s %20s %15s %14s %16s\n";
        System.out.printf(format, "No. ", "Flower ID", "Description", "Import Date", "Unit price", "Category");

        int count = 1;
        for (Flower flower : flowers) {
            String importDateStr = Converter.dateToStr(flower.getImportDate());
            System.out.printf(format, count++, flower.getID(), flower.getDescription(), importDateStr,
                    flower.getUnitPrice(), flower.getCategory());
        }
    }

    public static void showOrderTable(LinkedHashMap<Integer, Order> orders) {
        System.out.println("\n\n-----------------");
        System.out.println("LIST OF ORDERS");
        System.out.println("-----------------");

        String format = "%4s %8s %16s %20s %14s %14s\n";
        System.out.printf(format, "No. ", "Order ID", "Order Date", "Customer", "Flower Count", "Order Total");

        double totalPrice = 0;
        for (int no : orders.keySet()) {
            Order order = orders.get(no);
            totalPrice += order.getTotalPrice();

            String orderDateStr = Converter.dateToStr(order.getOrderDate());
            System.out.printf(format, no, order.getOrderID(), orderDateStr, order.getCustomerName(),
                    order.getOrderCount(), order.getTotalPrice());
        }

        System.out.println("TOTAL: " + totalPrice);
    }
}
