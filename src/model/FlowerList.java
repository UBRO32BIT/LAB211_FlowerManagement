/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ubro3
 */
public class FlowerList extends HashSet<Flower> {
    public Flower getByID(String flowerID) {
        Flower result = null;
        for (Flower flower : this) {
            if (flower.getID().equals(flowerID)) {
                result = flower;
            }
        }
        return result;
    }
    public FlowerList filterByDescription(String description) {
        FlowerList result = new FlowerList();
        String regex = ".*" + description + ".*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        
        for (Flower flower : this) {
            Matcher matcher = pattern.matcher(flower.getDescription());
            if (matcher.matches()) {
                result.add(flower);
            }
        }
        return result;
    }
    public boolean containsID(String flowerID) {
        boolean isContain = false;
        for (Flower flower : this) {
            if (flower.getID().equals(flowerID)) {
                isContain = true;
            }
        }
        return isContain;
    }
    @Override
    public boolean add(Flower flower) {
        boolean result;
        for (Flower f : this) {
            if (flower.equals(f)) {
                return false;
            }
        }
        result = super.add(flower);
        return result;
    }
    public boolean removeByID(String id) {
        String description = getByID(id).getDescription();
        Flower flower = new Flower(id, description, null, 0, null);
        boolean result = remove(flower);
        return result;
    }
}
