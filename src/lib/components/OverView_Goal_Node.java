/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.components;

import database.Goals_Map;
import database.db;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lib.utils.TextFieldFormater;

/**
 *
 * @author jack1
 */
public class OverView_Goal_Node {
    private final HBox goalTitleBar;
    private final Scrollabe_HBox sHBox = new Scrollabe_HBox();
    private final Editor_Window editPane = new Editor_Window("Confirm","Delete","Cancel","Title","Starting Amount","Total Amount");
    
    public OverView_Goal_Node(){
        this.goalTitleBar = new HBox(10);
        this.goalTitleBar.setAlignment(Pos.CENTER);
        editPane.populateSelection(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Budget-Planer\\resources\\goalIcons"));
        editPane.getDatePicker().setVisible(false);
        editPane.getSpenButton().setVisible(false);
        editPane.getIncButton().setVisible(false);
        editPane.setTitleLabel("Goal Name");
        editPane.setTypeLabel("Current Amount");
        editPane.setAmountLabel("Total Amount");
        editPane.setDoubleFormater(new TextFieldFormater().getFormat(), editPane.getTypeTextField());
        editPane.setDoubleFormater(new TextFieldFormater().getFormat(), editPane.getAmountTextField());
        
        Label title = new Label("Goals");//goalss
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        
        editPane.getAppearButton().setText("+");
        editPane.setAppearButtonClass("goal-button");
        
        editPane.getDeleteButton().setOnMouseClicked(event -> {
            editPane.getAlert().showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK){
                    System.out.println("OK!!");
                }else if(response == ButtonType.CANCEL){
                    System.out.println("Cancel!!");
                    editPane.getAlert().close();
                }
            });
        });
        System.out.println(Goals_Map.getMap());
        Goals_Map.getMap().forEach((key,item) -> {
          sHBox.insert(item);
        });
        
        this.goalTitleBar.getChildren().addAll(title,editPane.getAppearButton());
        logic();
    }
    
    public void draw(){
        sHBox.clear();
        Goals_Map.getMap().forEach((key,item) -> {
          sHBox.insert(item);
        });
    }
    
    private void logic(){
        editPane.getAppearButton().setOnMouseClicked(event -> {
            editPane.show();
        });
        editPane.getEditButton().setOnMouseClicked(event -> {
            if(editPane.hasValues()){
                sHBox.insert(editPane.getTitleTextField().getText(), Double.parseDouble(editPane.getTypeTextField().getText()), Double.parseDouble(editPane.getAmountTextField().getText()), editPane.getIconName());
                db.insertToGoals(editPane.getTitleTextField().getText(), Double.parseDouble(editPane.getTypeTextField().getText()), Double.parseDouble(editPane.getAmountTextField().getText()), editPane.getIconName());
                editPane.clear();
                editPane.hide();
            }
        });
        editPane.getCancelButton().setOnMouseClicked(event -> {
            editPane.hide();
            editPane.clear();
        });
    }
    
    public Scrollabe_HBox getScrollable(){
        return sHBox;
    }
     public HBox getTitleBar(){
        return goalTitleBar;
    }
    
    public Button getGoalButton(){
        return editPane.getAppearButton();
    }
    
    public Editor_Window getEditPane(){
        return editPane;
    }
}
