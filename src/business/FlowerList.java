/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.HashSet;
import java.util.Iterator;
import model.Flower;

/**
 *
 * @author ubro3
 */
public class FlowerList extends HashSet<Flower> {
    public Flower get(String flowerID) {
        Iterator<Flower> i = iterator();
        while(i.hasNext()) {
            Flower flower = i.next();
            if (flower.getID().equals(flowerID)) {
                return flower;
            }
        }
        return null;
    }
}
