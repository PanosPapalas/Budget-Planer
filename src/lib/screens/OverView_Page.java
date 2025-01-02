/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.screens;

import javafx.scene.Node;
import javafx.scene.control.Label;
import lib.components.Chart_Pane;
import lib.components.OverView_Goal_Node;
import lib.utils.AnchorPaneInsets;

/**
 *
 * @author jack1
 */
public class OverView_Page {
    private final Chart_Pane chartPane = new Chart_Pane();
    private final OverView_Goal_Node goalsNode = new OverView_Goal_Node();
    private final Label title = new Label("Weekly Sumup");
    private final Node[] content = new Node[6];
    private int index = 0;
    
    public OverView_Page(){
        title.setStyle("-fx-font-size:24; -fx-font-weight:bold;");
        AnchorPaneInsets.Insets(title,16,-1.0,-1.0,35.0);
        AnchorPaneInsets.Insets(chartPane.getRoot(),59.0,-1.0,-1.0,36.0);
        AnchorPaneInsets.Insets(goalsNode.getTitleBar(),-1.0,-1.0,220.0,36.0);
        AnchorPaneInsets.Insets(goalsNode.getScrollable().getRoot(),-1.0,-1.0,1.0,36.0);
        AnchorPaneInsets.Insets(goalsNode.getEditPane().getRoot(),150,-1.0,-1.0,250.0);
        AnchorPaneInsets.Insets(chartPane.getEditorWindow().getRoot(), 35.0, -1.0, -1.0, 45.0);
        
        title.setOnMouseClicked(event -> {
            switch (index) {
                case 0:
                    title.setText("Monthly Sumup");
                    this.chartPane.showMonthChart();
                    index++;
                    break;
                case 1:
                    title.setText("Yearly Sumup");
                    this.chartPane.showYearChart();
                    index++;
                    break;
                default:
                    title.setText("Weekly Sumup");
                    this.chartPane.showWeekChart();
                    index=0;
                    break;
            }
        });
        
        content[0] = title;
        content[1] = chartPane.getRoot();
        content[2] = goalsNode.getTitleBar();
        content[3] = goalsNode.getScrollable().getRoot();
        content[4] = goalsNode.getEditPane().getRoot();
        content[5] = chartPane.getEditorWindow().getRoot();
    }
    
    public OverView_Goal_Node getGoalHolder(){
        return goalsNode;
    }
    public Chart_Pane getChartPane(){
        return chartPane;
    }
    public Label getTitle(){
        return title;
    }
    
    public Node[] getContent(){
        return content;
    }
}
