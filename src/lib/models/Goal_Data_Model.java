/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.models;

/**
 *
 * @author jack1
 */
public class Goal_Data_Model {
    private String title;
    private double currentAmount;
    private double totalAmount;
    private String iconName;
    private int id;
    
    public Goal_Data_Model(int ID,String titleValue,double cAmount,double tAmount, String icon){
        id = ID;
        title = titleValue;
        currentAmount = cAmount;
        totalAmount = tAmount;
        iconName = icon;
    }
    
    public int getID(){
        return id;
    }
    
    public void setTitle(String newValue){
        title = newValue;
    }
    
    public String getTitle(){
        return title;
    }
    
    public double getCurrentAmount(){
        return currentAmount;
    }
    
    public void setCurrentAmount(double amount){
        currentAmount = amount;
    }
    
    public double getTotalAmount(){
        return totalAmount;
    }
    
    public void setTotalAmount(double value){
        totalAmount = value;
    }
    
    public String getIconName(){
        return iconName;
    }
    
    public void setIconName(String value){
        iconName = value;
    }
    
    @Override
    public String toString(){
        return "Goal title: "+title+", Current amount: "+currentAmount+", Total amount: "+totalAmount+", Icon name: "+iconName;
    }
}
