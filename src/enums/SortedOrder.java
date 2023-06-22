/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 * Datatype to store sorted order, using overriden method toString() to get String content of the value
 * @author ubro3
 */
public enum SortedOrder {
    ASCENDING,
    DESCENDING;

    @Override
    public String toString() {
        switch (this) {
            case ASCENDING:
                return "Ascending";
            case DESCENDING:
                return "Descending";
            default:
                return null;
        }
    }
}





