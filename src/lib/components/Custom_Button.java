/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.components;

import java.io.File;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author jack1
 */
public class Custom_Button {
    private final ImageView view;
    private final HBox button; 
    private final Label title;
    private final Image imageAct;
    private final Image imageInAct;
    private final BooleanProperty isFocused = new SimpleBooleanProperty(false);
    
    public Custom_Button(String title, String imageName, String imageNameIn, String styleClass){
        this.button = new HBox(6.5);
        this.button.getStyleClass().add(styleClass);
        this.button.setAlignment(Pos.CENTER);
        this.title = new Label(title);
        this.title.getStyleClass().add("new-button-label");
        this.title.setAlignment(Pos.CENTER);
        HBox.setHgrow(this.title, Priority.ALWAYS);
        
        isFocused.addListener((observable, oldValue, newValue) ->{
            if(newValue){
                this.imageAct();
                this.button.getStyleClass().clear();
                this.button.getStyleClass().add(styleClass+"-focus");
            }else{
                this.imageInAct();
                this.button.getStyleClass().clear();
                this.button.getStyleClass().add(styleClass);
            }
        });
        this.button.setOnMouseEntered(event ->{
            this.imageAct();
            this.button.getStyleClass().clear();
            this.button.getStyleClass().add(styleClass+"-hover");});
        this.button.setOnMouseExited(event ->{
            if(!this.isValid()){
                this.imageInAct(); 
                this.button.getStyleClass().clear();
                this.button.getStyleClass().add(styleClass);
            }
        });
        this.imageAct = new Image(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Budget-Planer\\resources\\buttonIcons\\"+imageName).toURI().toString());
        this.imageInAct = new Image(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Budget-Planer\\resources\\buttonIcons\\"+imageNameIn).toURI().toString());
        this.view = new ImageView(this.imageInAct);
        this.button.getChildren().addAll(this.view,this.title);
    }
    
    public final boolean isValid() {
        return this.isFocused.get();
    }

    public final void setValid(boolean valid) {
        this.isFocused.set(valid);
    }
    
    public void imageInAct(){
        this.view.setImage(this.imageInAct);
    }
    
    public void imageAct(){
        this.view.setImage(this.imageAct);
    }
    
    public HBox getButton(){
        return button;
    }
}
