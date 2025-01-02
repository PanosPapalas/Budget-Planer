/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.components;

import java.io.File;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lib.utils.DateFormater;

/**
 *
 * @author jack1
 */
public class Editor_Window {
    private final Pane root = new Pane();
    private final ComboBox iconPicker = new ComboBox();
    private final Button appear = new Button(" ");
    private final Button cancel = new Button(" ");
    private final Button confirm = new Button(" ");
    private final Button delete = new Button(" ");
    private final ImageView view = new ImageView();
    private final TextField title = new TextField();
    private final TextField type = new TextField();
    private final TextField amount = new TextField();
    private final RadioButton buttonSpending = new RadioButton("Spending");
    private final RadioButton buttonIncome = new RadioButton("Income");
    private final DatePicker picker = new DatePicker();
    private final ToggleGroup group = new ToggleGroup();
    private final Label labelTitle = new Label("Title");
    private final Label labelType = new Label("Type");
    private final Label labelAmount = new Label("Amount"); 
    private String iconName = " ";
    private String currentImageFileSelected = " ";
    private String defaultImageFile = " ";
    private final Alert alert = new Alert(AlertType.CONFIRMATION);
    // keep it for when it will be used private final File images = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Budget-Planer\\resources");
    private int id =-1;
    
    
    public Editor_Window(String button1,String button2,String button3, String Label1, String Label2, String Label3){
        this.root.setStyle("-fx-border-width:2px; -fx-border-color:black; -fx-background-color:white; ");
        root.setVisible(false);
        appear.setOnMouseClicked(event -> {root.setVisible(true);});
        VBox contentHolder = new VBox();
        
        contentHolder.setStyle("-fx-padding:5;");
        Pane imageHolder = new Pane(view);
        this.view.setLayoutX(5);
        this.view.setLayoutY(5);
        Pane imageBackground = new Pane(imageHolder);
        imageHolder.setLayoutX(6);
        imageHolder.setLayoutY(6);
        imageBackground.setStyle("-fx-padding: 0 0 5 5; -fx-background-color: white; -fx-border-color: black;");
        imageHolder.setStyle("-fx-padding: 0 0 5 5; -fx-background-color: white;-fx-border-color: black; -fx-min-width: 36; -fx-min-height:36;");
        confirm.setText(button1);
        delete.setText(button2);
        cancel.setText(button3);
        labelTitle.setText(Label1);
        labelType.setText(Label2);
        labelAmount.setText(Label3);
        HBox top = new HBox(15,imageBackground,iconPicker);
        top.setAlignment(Pos.BOTTOM_CENTER);
        top.setStyle("-fx-padding:5;");
        VBox.setVgrow(picker, Priority.ALWAYS);
        this.picker.setValue(LocalDate.now());
        VBox textBox = new VBox(15,labelTitle,title,labelType,type,labelAmount,amount,picker);
        textBox.setStyle("-fx-padding:5;");
        labelTitle.setStyle("-fx-font-size: 16; -fx-font-weight:bold;");
        labelType.setStyle("-fx-font-size: 16; -fx-font-weight:bold;");
        labelAmount.setStyle("-fx-font-size: 16; -fx-font-weight:bold;");
        HBox radioGroup = new HBox(30,buttonSpending,buttonIncome);
        radioGroup.setAlignment(Pos.CENTER);
        radioGroup.setStyle("-fx-padding:5;");
        this.buttonSpending.setToggleGroup(group);
        this.buttonIncome.setToggleGroup(group);
        this.buttonSpending.setSelected(true);
        HBox buttonBox = new HBox(35, confirm, delete,cancel);
        buttonBox.setStyle("-fx-padding:5;");
        buttonBox.setAlignment(Pos.CENTER);
        
        contentHolder.getChildren().add(top);
        contentHolder.getChildren().add(textBox);
        contentHolder.getChildren().add(radioGroup);
        contentHolder.getChildren().add(buttonBox);
        root.getChildren().add(contentHolder);
    }
    
    public void populateSelection(File directory){
        if(directory.exists() && directory.isDirectory()){
            this.iconPicker.setItems(FXCollections.observableArrayList(directory.list()));
            this.currentImageFileSelected = directory.listFiles()[0].toURI().toString();
            this.defaultImageFile = directory.listFiles()[0].toURI().toString();
            this.view.setImage(new Image(directory.listFiles()[0].toURI().toString()));
            this.iconPicker.getSelectionModel().selectFirst();
            this.iconName = directory.list()[0];
            this.iconPicker.setOnAction(event ->{ 
                this.iconName = directory.list()[this.iconPicker.getSelectionModel().getSelectedIndex()];
                this.currentImageFileSelected = directory.listFiles()[this.iconPicker.getSelectionModel().getSelectedIndex()].toURI().toString(); 
                this.view.setImage(new Image(directory.listFiles()[this.iconPicker.getSelectionModel().getSelectedIndex()].toURI().toString()));});
        }
    }
    
    public void setAlertTitle(String title){
        alert.setTitle(title);
    }
    
    public void setAlertHeader(String header){
        alert.setHeaderText(header);
    }
    
    public Alert getAlert(){
        return alert;
    }
    
    public void setAlertContent(String content){
        alert.setContentText(content);
    }
    
    public Button getAppearButton(){
        return appear;
    }
    
    public void setAppearText(String value){
        appear.setText(value);
    }
    
    public void setAppearButtonClass(String className){
        appear.getStyleClass().add(className);
    }
    
    public RadioButton getSpenButton(){
        return buttonSpending;
    }
    
    public RadioButton getIncButton(){
        return buttonIncome; 
    }
    
    public void setButtonSpendingName(String value){
        buttonSpending.setText(value);
    }
    
    public void setButtonIncomeName(String value){
        buttonIncome.setText(value);
    }
    
    public Button getDeleteButton(){
        return delete;
    }
    
    public Pane getRoot(){
        return root;
    }
    
    public String getIconName(){
        return iconName;
    }
    
    public void setDoubleFormater(TextFormatter textFormatter, TextField field){
        field.setTextFormatter(textFormatter);
    }
    
    public TextField getTitleTextField(){
        return title;
    }
    
    public TextField getTypeTextField(){
        return type;
    }
    
    public TextField getAmountTextField(){
        return amount;
    }
    
    public String getImageFile(){
        return currentImageFileSelected;//selectedImage
    }
    
    public boolean hasValues(){
        return title.getText().trim().length()>=1 && amount.getText().trim().length()>=1 && type.getText().trim().length()>=1;
    }
    
    public ToggleGroup getToggleGroup(){
        return group;
    }
    
    public boolean isSpending(){
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        
        return toogleGroupValue.equalsIgnoreCase(buttonSpending.getText());
    }
    
    public void setTitleLabel(String value){
        labelTitle.setText(value);
    }
    
    public void setTypeLabel(String value){
        labelType.setText(value);
    }
    
    public void setAmountLabel(String value){
        labelAmount.setText(value);
    }
    
    public void clear(){
        id = -1;
        title.setText("");
        type.setText("");
        amount.setText("");
        this.currentImageFileSelected = this.defaultImageFile; 
        this.view.setImage(new Image( this.defaultImageFile));
        this.picker.setValue(LocalDate.now());
    }
    
    public Button getEditButton(){
        return confirm;
    }
    
    public DatePicker getDatePicker(){
        return picker;
    }
    
    public int getID(){
        return id;
    }
    
    public void setPickerValues(int ID,String iconName,String newTitle,String newType,String newAmount,Image newImage,boolean spending,String date){
        id = ID;
        this.iconPicker.getSelectionModel().select(iconName);
        this.title.setText(newTitle);
        this.type.setText(newType);
        this.amount.setText(newAmount);
        this.view.setImage(newImage);
        this.buttonIncome.setSelected(!spending);
        this.buttonSpending.setSelected(spending);
        this.picker.setValue(DateFormater.stringToDate(date));
    }
    
    public Button getCancelButton(){
        return cancel;
    }
    
    public void show(){
        root.setVisible(true);
    }
    
    public void hide(){
        root.setVisible(false);
    }
    
    public boolean isVisible(){
        return root.isVisible();
    }
    public void insertNode(int index, Node node){
        root.getChildren().add(index, node);
    }
    public void setConfirmButtonText(String text){
        confirm.setText(text);
    }
    public void setCancelButtonText(String text){
        cancel.setText(text);
    }
    public void setDeleteButtonText(String text){
        delete.setText(text);
    }
}
