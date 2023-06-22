/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class provides various methods to validate different types of data.
 * It includes methods for validating dates, regular expressions, positive integers,
 * positive doubles, flower IDs, descriptions, and order IDs.
 * 
 * Note: This class is intended to be used as a utility class and should not be instantiated.
 * All methods in this class are static.
 *
 * @author ubro3
 */
public final class Validator {
    /**
     * Validate date in the format dd/mm/yyyy using Regular Expression.
     * 
     * @param strDate The string representation of the date to be validated.
     * @return True if the date is valid, false otherwise.
     */
    public static boolean dateValidate(String strDate) {
        try {
            // Create a formatter for the desired format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
            // Parse the string into a LocalDate object using the formatter
            LocalDate date = LocalDate.parse(strDate, formatter); 
            // Additional check to validate the day, month, and year
            return date.format(formatter).equals(strDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    /**
     * Validates a string against a regular expression pattern.
     * 
     * @param regex The regular expression pattern to match against.
     * @param str The string to validate.
     * @return True if the string matches the regular expression, false otherwise.
     */
    public static boolean validateRegexPattern(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    /**
     * Validates a string to check if it represents a positive integer.
     *
     * @param str The string to validate.
     * @return True if the string represents a positive integer, false otherwise.
     */
    public static boolean validateNumber(String str) {
        return validateRegexPattern("^[1-9]\\d*$", str);
    }
    
    /**
     * Validates a string to check if it represents a positive double.
     *
     * @param str The string to validate.
     * @return True if the string represents a positive double, false otherwise.
     */
    public static boolean validatePositiveDouble(String str) {
        return validateRegexPattern("[0-9]{1,13}(\\\\.[0-9]*)?", str);
    }
    
    /**
     * Validates a string to check if it represents a flower ID.
     *
     * @param str The string to validate.
     * @return True if the string represents a valid flower ID, false otherwise.
     */
    public static boolean validateFlowerID(String str) {
        return validateRegexPattern("^\\d{4}$", str);
    }
    
    /**
     * Validates a string to check if it represents a valid description.
     *
     * @param str The string to validate.
     * @return True if the string represents a valid description, false otherwise.
     */
    public static boolean validateDescription(String str) {
        return validateRegexPattern("^.{3,50}$", str);
    }
    
    /**
     * Validates a string to check if it represents a valid order ID.
     *
     * @param str The string to validate.
     * @return True if the string represents a valid order ID, false otherwise.
     */
    public static boolean validateOrderID(String str) {
        return validateRegexPattern("^\\d{4}$", str);
    }
}
