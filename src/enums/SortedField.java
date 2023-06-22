/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 * Datatype to store sorted fields, using overriden method toString() to get String content of the value
 * @author ubro3
 */
public enum SortedField {
    ORDER_ID,
    ORDER_DATE,
    CUSTOMER_NAME,
    ORDER_TOTAL;
    
    @Override
    public String toString() {
        switch(this) {
            case ORDER_ID:
                return "Order ID";
            case ORDER_DATE:
                return "Order Date";
            case CUSTOMER_NAME:
                return "Customer's Name";
            case ORDER_TOTAL:
                return "Order Total";
            default:
                return null;
        }
    }
}
