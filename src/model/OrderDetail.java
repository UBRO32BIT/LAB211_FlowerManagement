/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ubro3
 */
public class OrderDetail implements Serializable {
    private String orderDetailID;
    private String flowerID;
    private int quantity;
    private double cost;
    /**
     * Constructor of the class
     * @param orderDetailID
     * @param flower
     * @param quantity 
     */
    public OrderDetail(String orderDetailID, Flower flower, int quantity) {
        this.orderDetailID = orderDetailID;
        this.flowerID = flower.getID();
        this.quantity = quantity;
        this.cost = flower.getUnitPrice()*quantity;
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public String getFlowerID() {
        return flowerID;
    }

    public void setFlowerID(String flowerID) {
        this.flowerID = flowerID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    
}
