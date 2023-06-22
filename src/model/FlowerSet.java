/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A collection of Flower objects.
 * Extends the HashSet class to provide additional functionality for managing Flower objects.
 * Provides methods for filtering and searching flowers by ID and description, as well as adding, updating, and removing flowers.
 * Implements specific business logic to ensure uniqueness of flowers based on their ID and description.
 * 
 * @author ubro3
 */
public class FlowerSet extends HashSet<Flower> {

    /**
     * Retrieves a flower by its ID.
     *
     * @param flowerID The ID of the flower to retrieve.
     * @return The Flower object with the specified ID, or null if not found.
     */
    public Flower getByID(String flowerID) {
        Flower result = null;
        for (Flower flower : this) {
            if (flower.getID().equals(flowerID)) {
                result = flower;
                break;
            }
        }
        return result;
    }
    /**
     * Get a flower object by its description.
     *
     * @param description The description to filter by.
     * @return The Flower object with the specified description, or null if not found.
     */
    public Flower getByDescription(String description) {
        Flower result = null;
        for (Flower flower : this) {
            if (flower.getDescription().equals(description)) {
                result = flower;
                break;
            }
        }
        return result;
    }
    /**
     * Filters flowers by their description or part of the description using Regular Expression.
     *
     * @param description The description to filter by.
     * @return A new FlowerSet containing the flowers whose description matches the specified pattern.
     */
    public FlowerSet filterByDescription(String description) {
        FlowerSet result = new FlowerSet();
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

    /**
     * Checks if a flower with the given ID exists in the collection.
     *
     * @param flowerID The ID to check for.
     * @return true if a flower with the specified ID exists, false otherwise.
     */
    public boolean containsID(String flowerID) {
        boolean result;
        if (this.getByID(flowerID) != null) {
            result = true;
        }
        else result = false;
        return result;
    }

    /**
     * Checks if a flower with the given description exists in the collection.
     *
     * @param description The description to check for.
     * @return true if a flower with the specified description exists, false otherwise.
     */
    public boolean containsDescription(String description) {
        boolean result;
        if (this.getByDescription(description) != null) {
            result = true;
        }
        else result = false;
        return result;
    }

    /**
     * Adds a flower to the collection.
     * Overrides the add() method to enforce uniqueness of flowers based on their ID and description.
     *
     * @param flower The flower to add.
     * @return true if the flower is added successfully, false if a flower with the same ID or description already exists.
     */
    @Override
    public boolean add(Flower flower) {
        boolean result;
        //If there is a duplication with other flower's ID or description, the operation aborted
        if (containsID(flower.getID()) || containsDescription(flower.getDescription())) {
            result = false;
        } else {
            result = true;
            super.add(flower);
        }
        return result;
    }

    /**
     * Updates an existing flower with new information.
     *
     * @param updatedFlower The updated Flower object.
     */
    public void update(Flower updatedFlower) {
        // Fetches the flower object from the collection based on the query flower's ID
        Flower flower = this.getByID(updatedFlower.getID());

        String description = updatedFlower.getDescription();
        LocalDate importDate = updatedFlower.getImportDate();
        double unitPrice = updatedFlower.getUnitPrice();
        String category = updatedFlower.getCategory();
        //Update the flower's attributes based on the query object's attributes
        if (description != null) {
            flower.setDescription(description);
        }
        if (importDate != null) {
            flower.setImportDate(importDate);
        }
        if (unitPrice > 0) {
            flower.setUnitPrice(unitPrice);
        }
        if (category != null) {
            flower.setCategory(category);
        }
        //Update the object in the collection
        super.remove(flower);
        this.add(flower);
    }

    /**
     * Removes a flower from the collection by its ID.
     *
     * @param id The ID of the flower to remove.
     * @return true if the flower is removed successfully, false if a flower with the specified ID is not found.
     */
    public boolean removeByID(String id) {
        //Get the flower object based on ID
        Flower flower = this.getByID(id);
        boolean result = super.remove(flower);
        return result;
    }
}
