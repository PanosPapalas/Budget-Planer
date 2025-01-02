/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.util.HashMap;
import lib.models.Current_Date_Range;
import lib.models.Transaction_Data_Model;

/**
 *
 * @author jack1
 */
public class Transactions_Map {
    private static HashMap<Integer, Transaction_Data_Model> map = new HashMap<>();
    private static double currentBalance = 0.0;
    private static double income = 0.0;
    private static double spending = 0.0;
    
    public static void populateMap(){
        spending = 0.0;
        income = 0.0;
        currentBalance = 0.0;
        map = db.getTransactionsResults(map,  "BETWEEN '"+Current_Date_Range.getStartDate().getYear()+"-01-01'"+" AND '"+Current_Date_Range.getEndDate().getYear()+"-12-31'");
        map.forEach((key,item) -> {
            if(item.isSpending()){
                spending = spending + item.getAmount();
            }else{
                income = income + item.getAmount();
            }
        });
        currentBalance = income - spending;
    }
    
    public static HashMap<Integer, Transaction_Data_Model> getMap(){
        return map;
    }
    
    public static double getCurrentBalance(){
        return currentBalance;
    }
    
    public static double getCurrentIncome(){
        return income;
    }
    
    public static double getCurrenSpending(){
        return spending;
    }
}
