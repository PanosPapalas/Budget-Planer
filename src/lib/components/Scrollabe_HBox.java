/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.components;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import lib.models.Goal_Data_Model;
import lib.models.Goal_Node_Model;

/**
 *
 * @author jack1
 */
public class Scrollabe_HBox {
    
    private final ScrollPane root = new ScrollPane();
    private final HBox goalHolder = new HBox(35);
    int pos = 0;
    final int minPos = 0;
    final int maxPos = 100;
    
    public Scrollabe_HBox(){
        goalHolder.setStyle("-fx-padding: 10 10 10 20; -fx-background-color:white;");
        goalHolder.setOnScroll(event -> {
            if (event.getDeltaY() > 0)
                    root.setHvalue(pos == minPos ? minPos : pos--);
                else
                    root.setHvalue(pos == maxPos ? maxPos : pos++);
        });
        
        root.setHmin(minPos);
        root.setHmax(maxPos);
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        root.setPannable(true);
        root.setContent(goalHolder);
        root.setStyle("-fx-background: #FFFFFF;");
        goalHolder.setAlignment(Pos.CENTER_LEFT);
        goalHolder.setMaxWidth(Double.MAX_VALUE);
    }
    
    public void remove(int id){
        this.goalHolder.getChildren().remove(id);
    }
    
    public void clear(){
       goalHolder.getChildren().clear();
    }
    
    public void insert(String title, double curAmount, double totAmount, String iconNam){
        this.goalHolder.getChildren().add(new Goal_Node_Model(title,curAmount,totAmount,iconNam).getRoot());
    }
    
    public void insert(Goal_Data_Model model){
        this.goalHolder.getChildren().add(new Goal_Node_Model(model).getRoot());
    }
    
    public ScrollPane getRoot(){
        return this.root;
    }
}
