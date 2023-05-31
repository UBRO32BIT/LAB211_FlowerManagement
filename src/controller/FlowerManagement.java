/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import model.Flower;
import business.FlowerList;
import model.Order;
import model.OrderDetail;
import business.OrderList;
import java.util.HashMap;
import viewer.LogHandler;

/**
 *
 * @author ubro3
 */
public class FlowerManagement {
    private FlowerList flowers;
    private OrderList orders;
    private final LogHandler log;
    public FlowerManagement(String fileLog) {
        flowers = new FlowerList();
        orders = new OrderList();
        log = new LogHandler(fileLog);
    }
    public void addFlower(String id, String description, LocalDate importDate, double unitPrice, String category) {
        
    }
    public void findFlower(String name) {
        
    }
    
    public boolean containsFlower(String flowerID) {
        for (Flower flower : flowers) {
            if (flower.getID().equals(flowerID)) return true;
        }
        return false;
    }
    public void updateFlower(String flowerID, String[] updatedFields) {
        
    }
    public void deleteFlower(String flowerID) {
        
    }
    public void addOrder(String orderID, LocalDateTime orderDate, String customerName, HashMap<String, OrderDetail> orderDetails) {
        Order order = new Order(orderID, orderDate, customerName, orderDetails);
        orders.put(order.getOrderID(), order);
    }
    public void addOrderDetail(String orderID, String orderDetailID, String flowerID, int quantity) throws Exception{
        Flower flower = flowers.get(flowerID);
        if (flower == null) {
            log.printErr("Flower not found!");
        }
        OrderDetail od = new OrderDetail(orderDetailID, flower, quantity);
    }
    public void displayOrders(String startDate, String endDate) {
        
    }
    public void sortOrders(int sortedField, String sortOrder) {
        
    }
    public void saveData(String fileFlower, String fileOrder) {
        
    }
    public void loadData(String fileFlower, String fileOrder) {
        
    }
}
