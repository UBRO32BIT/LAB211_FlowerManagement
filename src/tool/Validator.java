/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ubro3
 */
public final class Validator {
    /**
     * Validate date on format dd/mm/yyyy using Regular Expression
     * @param strDate
     * @return 
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
    public static boolean validatePositiveDouble(String str) {
        return validateRegexPattern("[0-9]{1,13}(\\\\.[0-9]*)?", str);
    }
    
    public static boolean validateFlowerID(String str) {
        return validateRegexPattern("^\\d{4}$", str);
    }
    public static boolean validateDescription(String str) {
        return validateRegexPattern("^.{3,50}$", str);
    }
    public static boolean validateOrderID(String str) {
        return validateRegexPattern("^\\d{4}$", str);
    }
}
