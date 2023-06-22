/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ubro3
 */
public final class Converter {
    /**
    * Converts a string representation of a date on format 'dd/mm/yyyy' to a LocalDate object.
    *
    * @param strDate The string representation of the date.
    * @return The parsed LocalDate object.
    */
    public static LocalDate strToDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(strDate, formatter);
    }
    
    /**
    * Converts a LocalDate object to a string representation of a date on format 'dd/mm/yyyy'.
    *
    * @param date The LocalDate object to convert.
    * @return The string representation of the date.
    */
    public static String dateToStr(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
    
    public static String convertToFixedLengthString(int number, int length) {
        String format = "%0" + length + "d";
        return String.format(format, number);
    }
}
