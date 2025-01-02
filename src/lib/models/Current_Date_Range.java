/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.models;

import java.time.LocalDate;
import lib.utils.DateFormater;

/**
 *
 * @author jack1
 */
public class Current_Date_Range {
    private static LocalDate currentStart = DateFormater.bringToStartOfYear(LocalDate.now());
    private static LocalDate currentEnd = DateFormater.bringToEndOfYear(LocalDate.now());
    private static LocalDate start = DateFormater.bringToStartOfWeek(LocalDate.now());
    private static LocalDate end = DateFormater.bringToEndOfWeek(LocalDate.now());
    private static int currentYear = LocalDate.now().getYear();
    private static boolean yearChange = false;
    
    public static void redo(){
        start = DateFormater.bringToStartOfWeek(LocalDate.now());
        end = DateFormater.bringToEndOfWeek(LocalDate.now());
    }
    
    public static LocalDate getStartDate(){
        return start;
    }
    
    public static LocalDate getEndDate(){
        return end;
    }
    
    public static boolean hasYearChanged(){
        return yearChange;
    }
    
    public static int getCurrentYear(){
        return currentYear;
    }
    
    public static void transformForWeek(){
        start = DateFormater.bringToStartOfWeek(start);
        end = DateFormater.bringToEndOfWeek(end);
    }
    
    public static void transformForMonth(){
        start = DateFormater.bringToStartOfMonth(start);
        end = DateFormater.bringToEndOfMonth(end);
    }
    
    public static void transformForYear(){
        start = DateFormater.bringToStartOfYear(start);
        end = DateFormater.bringToEndOfYear(end);
    }
    
    public static String getStringStart(){
        return DateFormater.dateToString(start);
    }
    
    public static String getStringEnd(){
        return DateFormater.dateToString(end);
    }
    
    private static boolean checkYear(){
        yearChange = false;
        if(start.getYear() != currentYear){ currentYear = start.getYear(); yearChange = true;}
        if(end.getYear() != currentYear){ currentYear = end.getYear(); yearChange = true;}
        return yearChange;
    }
    public static boolean changeDatesByWeeks(int value){
        transformForWeek();
        start = start.plusWeeks(value);
        end = end.plusWeeks(value);
        return checkYear();
    }
    
    public static boolean changeDatesByMonths(int value){
        transformForMonth();
        start = start.plusMonths(value);
        end = end.plusMonths(value);
        return checkYear();
    }
    
    public static boolean changeDateRangeByYears(int value){
        transformForYear();
        start = start.plusYears(value);
        end = end.plusYears(value);
        return checkYear();
    }
}
