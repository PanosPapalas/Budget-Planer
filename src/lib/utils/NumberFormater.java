/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.utils;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;


public class NumberFormater {
    
    private static final NavigableMap<Long, String> suffixes = new TreeMap<> ();
    static {
      suffixes.put(1_000L, "k");
      suffixes.put(1_000_000L, "M");
      suffixes.put(1_000_000_000L, "G");
      suffixes.put(1_000_000_000_000L, "T");
      suffixes.put(1_000_000_000_000_000L, "P");
      suffixes.put(1_000_000_000_000_000_000L, "E");
    }
    /**
    * 
    * @author assylias
    *  https://stackoverflow.com/users/829571/assylias
     * @param value
     * @return Strings
    */
    public static String format(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }
    
    public static String format(double value) {
        long newValue = (long) value;
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (newValue == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (newValue < 0) return "-" + format(-newValue);
        if (newValue < 1000) return Double.toString(newValue); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(newValue);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = newValue / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }
    
    public static String format(int value) {
        long newValue = (long) value;
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (newValue == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (newValue < 0) return "-" + format(-newValue);
        if (newValue < 1000) return Double.toString(newValue); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(newValue);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = newValue / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }
}
