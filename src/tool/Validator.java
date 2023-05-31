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
public class Validator {
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
    /**
    * Validates a string to check if it represents a positive integer.
    *
    * @param str The string to validate.
    * @return True if the string represents a positive integer, false otherwise.
    */
    public static boolean validateNumber(String str) {
        String regex = "^[1-9]\\d*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    public static boolean validateDouble(String str) {
        String regex = "[0-9]{1,13}(\\\\.[0-9]*)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
