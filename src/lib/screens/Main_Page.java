/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.screens;

import java.io.File;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lib.components.Custom_Button;
import lib.utils.AnchorPaneInsets;


/**
 *
 * @author jack1
 */
public class Main_Page {
    private final HBox root;
    private final AnchorPane navigationBox;
    private AnchorPane content = new AnchorPane();
    
    private final OverView_Page overviewPage = new OverView_Page();
    private final Transactions_Page transactionsPage = new Transactions_Page();
    private final Goals_Page goalsPage = new Goals_Page();
    private final Button exitButton = new Button("X");
    private final Button minimizeButton = new Button("_");
    
    private void setContent(Node... content){
        if(content.length>=1){
            this.content.getChildren().clear();
            this.content.getChildren().addAll(content);
        }
        this.content.getChildren().addAll(exitButton,minimizeButton);
    }
    
    private void logic(){
        overviewPage.getChartPane().getLeft().setOnMouseClicked(event ->{
            overviewPage.getChartPane().changeBy(-1);
        });
        overviewPage.getChartPane().getRight().setOnMouseClicked(event -> {
            overviewPage.getChartPane().changeBy(1);
        });
    }
    
    private AnchorPane navBox(){
        AnchorPane contentHolder = new AnchorPane();
        
        Label appTitle = new Label("Smart Money", new ImageView(new Image(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Budget-Planer\\resources\\AppIcon.png").toURI().toString())));
        appTitle.getStyleClass().add("main-label");
        AnchorPaneInsets.Insets(appTitle,30.0,25.0,-1.0,20.0);
        VBox buttonHolder = new VBox(11);
        buttonHolder.setAlignment(Pos.CENTER);
        AnchorPaneInsets.Insets(buttonHolder,89.0,25.0,-1.0,20.0);
        Custom_Button[] bs = new Custom_Button[4];
        bs[0] = new Custom_Button("Overview","overview1.png","overview_in1.png","new-button");
        bs[1] = new Custom_Button("Transactions","transaction1.png","transaction_in1.png","new-button");
        bs[2] = new Custom_Button("Goals","award1.png","award_in1.png","new-button");
        bs[3] = new Custom_Button("Settings","settings1.png","settings_in1.png","new-button");
        bs[0].setValid(true);
        
        bs[0].getButton().setOnMouseClicked(event ->{
            if(!bs[0].isValid()){
                this.setContent(overviewPage.getContent());
                bs[0].setValid(true);
                bs[1].setValid(false);
                bs[2].setValid(false);
                bs[3].setValid(false);
            }
        });
        bs[1].getButton().setOnMouseClicked(event ->{
            this.setContent(transactionsPage.getContent());
            bs[0].setValid(false);
            bs[1].setValid(true);
            bs[2].setValid(false);
            bs[3].setValid(false);
        });
        bs[2].getButton().setOnMouseClicked(event ->{
            this.setContent(goalsPage.getContent());
            bs[0].setValid(false);
            bs[1].setValid(false);
            bs[2].setValid(true);
            bs[3].setValid(false);
        });
        bs[3].getButton().setOnMouseClicked(event ->{
            this.setContent(new HBox(new Label("WORK IN PROGRESS. \n COME BACK LATER. ")));
            bs[0].setValid(false);
            bs[1].setValid(false);
            bs[2].setValid(false);
            bs[3].setValid(true);
        });
        buttonHolder.getChildren().addAll(bs[0].getButton(),bs[1].getButton(),bs[2].getButton(),bs[3].getButton());
        
        VBox circleHolder = new VBox(4);
        circleHolder.setAlignment(Pos.CENTER);
        AnchorPaneInsets.Insets(circleHolder,-1.0,25.0,10.0,20.0);
        Circle circle  = new Circle(50,50,35);
        circle.setFill(new ImagePattern(new Image(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Budget-Planer\\resources\\ME.jpg").toURI().toString())));
        Label created  = new Label("Created by:\n Panagiotis K.\n   Papalas ");
        circleHolder.getChildren().addAll(circle,created);
        contentHolder.getChildren().addAll(appTitle,buttonHolder,circleHolder);
        return contentHolder;
    }
    
    private void windowButtons(){
        this.exitButton.getStyleClass().add("exit-button");
        this.exitButton.setOnMouseClicked(event->{
            Platform.exit();
        });
        AnchorPaneInsets.Insets(exitButton, 10.0, 25, -1, -1);
        this.minimizeButton.getStyleClass().add("min-button");
        this.minimizeButton.setOnMouseClicked(event ->{
            Stage stage = (Stage)root.getScene().getWindow();
            stage.setIconified(true);
        });
        AnchorPaneInsets.Insets(minimizeButton, 10.0, 55, -1, -1);
        this.content.getChildren().addAll(exitButton,minimizeButton);
    }
    
    
    public Main_Page(){
        this.root = new HBox();
        this.root.getStyleClass().add("root-hbox");
        this.navigationBox = navBox();
        this.navigationBox.getStyleClass().add("main-menu");
        this.content = new AnchorPane(this.overviewPage.getContent());
        logic();
        HBox.setHgrow(content, Priority.ALWAYS);
        windowButtons();
        overviewPage.getGoalHolder().draw();
        goalsPage.draw();
        this.content.getStyleClass().add("middle-menu");
        this.root.getChildren().addAll(this.navigationBox,this.content);
    }
    
    public HBox getRoot(){
        return root;
    }
}
