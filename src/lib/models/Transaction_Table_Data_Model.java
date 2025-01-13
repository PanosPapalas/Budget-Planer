/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.models;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import lib.utils.DateFormater;

/**
 *
 * @author jack1
 */
public class Transaction_Table_Data_Model {
     private final HBox iconedTitle;
    private final ImageView view;
    private final SimpleStringProperty transactionTitle;
    private final SimpleDoubleProperty amount;
    private final SimpleStringProperty type;
    private final Text text = new Text();
    private LocalDate date;
    private boolean isNegative;
    private String iconName = " ";
    private final String url = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Budget-Planer\\resources\\transactionIcons\\"; 
    private int id;
    
    public Transaction_Table_Data_Model(Transaction_Data_Model model){
        this.id = model.getID();
        this.iconName = model.getIconName();
        this.view = new ImageView(new Image(new File(url+model.getIconName()).toURI().toString()));
        this.transactionTitle = new SimpleStringProperty(model.getTitle());
        this.amount = new SimpleDoubleProperty(model.getAmount());
        this.type = new SimpleStringProperty(model.getType());
        this.isNegative = model.isSpending();
        this.date = DateFormater.databaseResultDate(model.getDate());
        Label title = new Label(this.transactionTitle.get());
        title.setMaxHeight(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);
        this.iconedTitle = new HBox(5,this.view,title);  
    }
    
    public Transaction_Table_Data_Model(String iconName,String iconPath, String title, double amount, String type, boolean isNegative, String time){
        this.iconName = iconName;
        this.view = new ImageView(new Image(iconPath));
        this.transactionTitle = new SimpleStringProperty(title);
        this.amount = new SimpleDoubleProperty(amount);
        this.type = new SimpleStringProperty(type);
        this.isNegative = isNegative;
        this.date = DateFormater.stringToDate(time);
        Label tit = new Label(this.transactionTitle.get());
        tit.setMaxHeight(Double.MAX_VALUE);
        tit.setAlignment(Pos.CENTER);
        this.iconedTitle = new HBox(5,this.view,tit);   
    }
    
    public void setHBox(){
        this.iconedTitle.getChildren().clear();
        this.iconedTitle.getChildren().addAll(view,new Label(transactionTitle.getValue()));
    }
    
    public HBox getHBox(){
        return this.iconedTitle;
    }
    public int getID(){
        return id;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public void setImage(Image value) {
        this.view.setImage(value);
    }
    public void setID(int ID){
        id =ID;
    }
    public String getIconName(){
        return iconName;
    }

    public ImageView getImage() {
        return view;
    }
     public String getTitle() {
        return this.transactionTitle.get();
    }
    public void setFirstName(String fName) {
        transactionTitle.set(fName);
    }
    
    public String getStringedAmount(){
        return isNegative ? "-"+this.amount.get():String.valueOf(this.amount.get());
    }
    
    public double getAmount(){
        return amount.get();
    }
    public void setAmount(double value){
        this.amount.set(value);
    }
    public Boolean getIsNegative(){
        return isNegative;
    }
    
    public void setIsNegative(boolean value){
        this.isNegative = value;
    }
    
    public Text getType(){
        text.setText(type.get());
        text.setWrappingWidth(150);
        return text;
    }
    public void setType(String value){
        text.setText(value);
        text.setWrappingWidth(150);
    }
    
    public String getDate(){
        return DateFormater.dateToString(date);
    }
    
    @Override
    public String toString(){
        return "Name: "+this.transactionTitle.get()+" Type: "+ this.type.get()+" Amount: "+ amount.get() + " Date: "+ date.format(DateTimeFormatter.ofPattern("dd MMM yyyy" ,Locale.ENGLISH))+  " isNegative: "+ isNegative;
    }
}
