/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author ubro3
 */
public class Order implements Serializable {
    private String orderID;
    private LocalDateTime orderDate;
    private String customerName;
    private HashMap<String, OrderDetail> orderDetails;
    public Order(String orderID, LocalDateTime orderDate, String customerName, HashMap<String, OrderDetail> orderDetails) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.orderDetails = orderDetails;
    }
    public Order(String orderID, LocalDateTime orderDate, String customerName) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerName = customerName;
        orderDetails = new HashMap<String, OrderDetail>();
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(HashMap<String, OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.put(orderDetail.getOrderDetailID(), orderDetail);
    }

    public void removeOrderDetail(String orderDetailID) {
        orderDetails.remove(orderDetailID);
    }
}
