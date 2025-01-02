/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.models;

/**
 *
 * @author jack1
 */
public class Transaction_Data_Model {
    private String transactionTitle;
    private double amount;
    private String type;
    private String date;
    private boolean tranType;
    private String iconName;
    private int id;
    
    public Transaction_Data_Model(int ID,String title, double amount, String type, String date, boolean tranType, String iconName){
        this.id = ID;
        this.transactionTitle = title;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.tranType = tranType;
        this.iconName = iconName;
    }
    
    public void setID(int newID){
        id = newID;
    }
    public String getTitle(){
        return transactionTitle;
    }
    
    public void setTitle(String newTitle){
        transactionTitle = newTitle;
    }
    
    public double getAmount(){
        return amount;
    }
    public int getID(){
        return id;
    }
    public void setAmount(double newAmount){
        amount = newAmount;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String newType){
        type = newType;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setDate(String newDate){
        date = newDate;
    }
    
    public boolean isSpending(){
        return tranType;
    }
    
    public void setTranType(boolean newValue){
        tranType = newValue;
    }
    
    public String getIconName(){
        return iconName;
    }
    
    public void setIconName(String newIcon){
        iconName = newIcon;
    }
    
    @Override
    public String toString(){
        return id+" "+transactionTitle+" "+amount+" "+date;
    }
}
