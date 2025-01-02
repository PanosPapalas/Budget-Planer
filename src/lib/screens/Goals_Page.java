/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.screens;

import database.Goals_Map;
import database.db;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import lib.components.Editor_Window;
import lib.models.Goal_Node_Model;
import lib.utils.AnchorPaneInsets;
import lib.utils.TextFieldFormater;

/**
 *
 * @author jack1
 */
public class Goals_Page {
    private final GridPane goalHolder = new GridPane();
    private final Button left = new Button("<-");
    private final Button right = new Button("->");
    private final Label title = new Label("Goals");
    private final Editor_Window editPane  =  new Editor_Window("Confirm","Delete","Cancel","Title","Starting Amount","Total Amount");
    private final Node[] content = new Node[6];
    private final ArrayList<Goal_Node_Model> c = new ArrayList<>();
    private int selected = 0;
    private boolean hasPicked = false;
    
    public Goals_Page(){
        goalHolder.setVgap(10);
        goalHolder.setHgap(10);
        
        editPane.populateSelection(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Budget-Planer\\resources\\goalIcons"));
        style();
        insets();
        handlePlacement();
        
        left.setOnMouseClicked(event ->{
            
            if(selected-8>=0){
                selected = selected-8;
                handlePlacement();
            }
        });
        right.setOnMouseClicked(event ->{
            
            if(selected+8<c.size()){
                selected = selected+8;
                handlePlacement();
            }
        });
        
        editPane.getEditButton().setOnMouseClicked(event ->{
            if(editPane.hasValues()){
                double start = Double.parseDouble(editPane.getTypeTextField().getText());
                double end = Double.parseDouble(editPane.getAmountTextField().getText());
                Goal_Node_Model g = new Goal_Node_Model(editPane.getTitleTextField().getText(),start,end,editPane.getIconName());
                db.insertToGoals(g.getTitle(), start, end, g.getIconName());
                insert(g);
                editPane.getRoot().setVisible(false);
                editPane.clear();
                hasPicked = false;
            }
        });
        editPane.getCancelButton().setOnMouseClicked(event ->{
            editPane.getRoot().setVisible(false);
            editPane.clear();
        });
        editPane.getDeleteButton().setOnMouseClicked(event ->{
            if(editPane.hasValues() & hasPicked){
                c.remove(editPane.getID()-1);
                db.deleteWithID("GOALS", editPane.getID());
                hasPicked = false;
                editPane.clear();
                editPane.getRoot().setVisible(false);
            }
        });
        
        Goals_Map.getMap().forEach((key,item) ->{ insert(new Goal_Node_Model(item)); });
        
        content[0] = goalHolder;
        content[1] = title;
        content[2] = editPane.getAppearButton();
        content[3] = left;
        content[4] = right;
        content[5] = editPane.getRoot();
    }
    
    private void insets(){
        AnchorPaneInsets.Insets(editPane.getRoot(),150,-1.0,-1.0,250.0);
        AnchorPaneInsets.Insets(editPane.getAppearButton(),55,-1.0,-1.0,35.0);
        AnchorPaneInsets.Insets(right,-1.0,35.0,15.0,-1.0);
        AnchorPaneInsets.Insets(left,-1.0,500.0,15.0,35.0);
        AnchorPaneInsets.Insets(goalHolder, 115.0, -1.0, 1.0, 35.0);
        AnchorPaneInsets.Insets(title,16,-1.0,-1.0,35.0);
    }
    
    public void draw(){
        c.clear();
        goalHolder.getChildren().clear();
        Goals_Map.getMap().forEach((key,item) ->{ insert(new Goal_Node_Model(item)); });
    }
    
    public void insert(Goal_Node_Model value){
        c.add(value);
        value.getRoot().setOnMouseClicked(action -> {
                    if(action.getButton() == MouseButton.PRIMARY && action.getClickCount() == 2){
                        editPane.setPickerValues(value.getID(),value.getIconName(), value.getTitle(), value.getStartAmount(), value.getEndAmount(), value.getImage(), true, "01 Dec 2002");
                        editPane.getRoot().setVisible(true);
                        hasPicked = true;
                    }
                });
        handlePlacement();
    }
    private void style(){
        editPane.setTitleLabel("Goal Name");
        editPane.setTypeLabel("Starting Amount");
        editPane.setAmountLabel("Ending Amount");
        editPane.getIncButton().setVisible(false);
        editPane.getSpenButton().setVisible(false);
        editPane.getRoot().setVisible(false);
        editPane.getDatePicker().setVisible(false);
        editPane.setDoubleFormater(new TextFieldFormater().getFormat(),editPane.getTypeTextField());
        editPane.setDoubleFormater(new TextFieldFormater().getFormat(),editPane.getAmountTextField());
        this.title.setStyle("-fx-font-size:24; -fx-font-weight:bold; -fx-underline: true;");
        editPane.setAppearText("New Goal");
        editPane.setAppearButtonClass("main-menu-button");
        this.left.getStyleClass().add("main-menu-button");
        this.right.getStyleClass().add("main-menu-button");
        this.right.setStyle("-fx-font-size: 50;");
        this.left.setStyle("-fx-font-size: 50;");
    }
    private void handlePlacement(){
        int index = selected;
        goalHolder.getChildren().clear();
        for(int i=0;i<2;i++){
            for(int j =0;j<4;j++){
                if(index<c.size())goalHolder.add(c.get(index++).getRoot(), j, i);
            }
        }
    }
    public ArrayList getList(){
        return c;
    }
    public Node[] getContent(){
        return content;
    }
}
