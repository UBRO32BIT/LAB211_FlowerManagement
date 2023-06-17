/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;

/**
 *
 * @author ubro3
 */
public class Order implements Serializable {
    private String id;
    private LocalDate orderDate;
    private String customerName;
    private HashSet<OrderDetail> orderDetails;
    public Order(String orderID, LocalDate orderDate, String customerName, HashSet<OrderDetail> orderDetails) {
        this.id = orderID;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.orderDetails = orderDetails;
    }
    public Order(String orderID, LocalDate orderDate, String customerName) {
        this.id = orderID;
        this.orderDate = orderDate;
        this.customerName = customerName;
        orderDetails = new HashSet<OrderDetail>();
    }

    public String getOrderID() {
        return id;
    }

    public void setOrderID(String id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashSet<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(HashSet<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
    }

    public void removeOrderDetail(String orderDetailID) {
        orderDetails.remove(orderDetailID);
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (OrderDetail od : orderDetails) {
            totalPrice += od.getCost();
        }
        return totalPrice;
    }
    public int getOrderCount() {
        return orderDetails.size();
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Order other = (Order) obj;
        return id.equals(other.id);
    }
}
