/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.models;

import java.io.File;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import lib.utils.NumberFormater;

/**
 *
 * @author jack1
 */
public class Goal_Node_Model {
    private final VBox root;
    private final Label curGoalAmount;
    private final Label totGoalAmount;
    private final ImageView goalIconHolder;
    private final Label goalTitle;
    private final String url = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Budget-Planer\\resources\\goalIcons\\";
    private final String defaultIcon = url+"flag.icon";
    private String value;
    private int id;
    
    public Goal_Node_Model(Goal_Data_Model model){
        this.root = new VBox();
        this.root.setMouseTransparent(false);
        this.root.getStyleClass().add("goal-background");
        
        this.curGoalAmount = new Label("$ "+ NumberFormater.format(model.getCurrentAmount()));
        this.curGoalAmount.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        this.curGoalAmount.setMouseTransparent(true);
        
        this.totGoalAmount = new Label("/$ "+ NumberFormater.format(model.getTotalAmount()));
        this.totGoalAmount.setStyle("-fx-font-size: 23; -fx-font-weight: bold; -fx-padding: 0 0 12 0;");
        this.totGoalAmount.setMouseTransparent(true);
        File imageFile = new File(url+model.getIconName());
        
        if(imageFile.exists() && !imageFile.isDirectory()){
            this.value = model.getIconName();
            this.goalIconHolder = new ImageView(new Image(imageFile.toURI().toString()));
        }else{
            this.goalIconHolder = new ImageView(new Image(defaultIcon));
            this.value = "flag.png";
        }
        this.goalIconHolder.setMouseTransparent(true);
        this.goalTitle = new Label(model.getTitle());
        this.goalTitle.setMouseTransparent(true);
        this.goalTitle.setStyle("-fx-font-size: 26;");
        this.id = model.getID();
        
        this.root.getChildren().addAll(curGoalAmount,totGoalAmount,goalIconHolder,goalTitle);
    }
    
    public Goal_Node_Model(String title, double curAmount, double totAmount, String iconName){
        this.root = new VBox();
        
        this.root.setMouseTransparent(false);
        this.root.getStyleClass().add("goal-background");
        
        this.curGoalAmount = new Label("$ "+ NumberFormater.format(curAmount));
        this.curGoalAmount.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        this.curGoalAmount.setMouseTransparent(true);
        
        this.totGoalAmount = new Label("/$ "+ NumberFormater.format(totAmount));
        this.totGoalAmount.setStyle("-fx-font-size: 23; -fx-font-weight: bold; -fx-padding: 0 0 12 0;");
        this.totGoalAmount.setMouseTransparent(true);
        File imageFile = new File(url+iconName);
        
        if(imageFile.exists() && !imageFile.isDirectory()){
            this.value = iconName;
            this.goalIconHolder = new ImageView(new Image(imageFile.toURI().toString()));
        }else{
            this.goalIconHolder = new ImageView(new Image(defaultIcon));
            this.value = "flag.png";
        }
        this.goalIconHolder.setMouseTransparent(true);
        this.goalTitle = new Label(title);
        this.goalTitle.setMouseTransparent(true);
        this.goalTitle.setStyle("-fx-font-size: 26;");
        
        this.root.getChildren().addAll(curGoalAmount,totGoalAmount,goalIconHolder,goalTitle);
    }
    
     public VBox getRoot(){
        return root;
    }
    
    public void setName(String newName){
        this.goalTitle.setText(newName);
    }
    
    public void setCurrentAmout(double newValue){
        this.curGoalAmount.setText("$ "+ newValue);
    }
    
    public void setTotalAmount(double newValue){
        this.totGoalAmount.setText("/$ "+ newValue);
    }
    
    public int getID(){
        return id;
    }
    
    public void setIcon(String iconName){
        File imageFile = new File(url+iconName);
        if(imageFile.exists() && !imageFile.isDirectory()){
            this.goalIconHolder.setImage(new Image(imageFile.toURI().toString()));
            value = iconName;
        }else{
            this.goalIconHolder.setImage(new Image(defaultIcon));
            this.value = "flag.png";
        }
    }
    
    public String getTitle(){
        return goalTitle.getText();
    }
    
    public String getStartAmount(){
        return curGoalAmount.getText();
    }
    
    public String getEndAmount(){
        return totGoalAmount.getText();
    }
    
    public String getIconName(){
        return value;
    }
    
    public Image getImage(){
        return goalIconHolder.getImage();
    }
}
