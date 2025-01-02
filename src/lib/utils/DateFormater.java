/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author jack1
 */
public class DateFormater {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy" ,Locale.ENGLISH);
    
    //Simple way to turn a LocalDate object to a string using the format specified by the formater variable
    public static String dateToString(LocalDate unfDate){
        return unfDate.format(formatter);
    }
    // method to parse the date withing a database that is in yyyy-mm-dd format
    public static LocalDate databaseResultDate(String date){
        return LocalDate.parse(date);
    }
    // method to parse the formated string to a localdate
    public static LocalDate stringToDate(String date){
        return LocalDate.parse(date,formatter);
    }
    // method to bring the date inside the now variable to the start of its week
    public static LocalDate bringToStartOfWeek(LocalDate now){
        LocalDate start = now.minusDays(now.getDayOfWeek().getValue()-1);
        if(start.getMonth() != now.getMonth()){
            start = LocalDate.of(now.getYear(), now.getMonth(), 1);
        }
        return start;
    }
    
    public static LocalDate bringToEndOfWeek(LocalDate now){
        LocalDate end = now.plusDays(7-now.getDayOfWeek().getValue());
        if(end.getMonth() != now.getMonth()){
            end = LocalDate.of(now.getYear(), now.getMonth(), 31);
        }
        return end;
    }
    
    public static LocalDate bringToStartOfMonth(LocalDate now){
        return now.minusDays(now.getDayOfMonth()-1);
    }
    
    public static LocalDate bringToEndOfMonth(LocalDate now){
        return now.plusDays(now.getMonth().maxLength()-now.getDayOfMonth());
    }
    
    public static LocalDate bringToStartOfYear(LocalDate now){
        return bringToStartOfMonth(now.minusMonths(now.getMonthValue()-1));
    }
    
    public static LocalDate bringToEndOfYear(LocalDate now){
        return bringToEndOfMonth(now.plusMonths(12-now.getMonthValue()));
    }
}

/*
todays is 2024-12-30
start of year is 2024-01-01
end of year is 2024-12-31

*/

