/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.components;

import database.Transactions_Map;
import database.db;
import java.io.File;
import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lib.charts.Chart_Month;
import lib.charts.Chart_Week;
import lib.charts.Chart_Year;
import lib.models.Current_Date_Range;
import lib.utils.NumberFormater;
import lib.utils.TextFieldFormater;

/**
 *
 * @author jack1
 */
public class Chart_Pane {
    private final VBox root;
    private final Label title;
    private final HBox chartBox;//chartHolder
    private final Button left;//but1
    private final Button right;//but2
    private final Chart_Week weekChart = new Chart_Week();
    private final Chart_Month monthChart = new Chart_Month();
    private final Chart_Year yearChart = new Chart_Year();
    private final Editor_Window editor = new Editor_Window("Confirm","Delete","Cancel","Title","Type","Amount");
    private final Label currentBalanceValue;
    private final Label incomeValue;
    private final Label spendingValue;
    private final Label dates;
    private int chart = 0;
    
    public Chart_Pane(){
        this.root = new VBox();
        this.root.getStyleClass().add("chart-menu");
        this.title = new Label("Week's Chart");
        this.dates = new Label(LocalDate.now()+"~"+LocalDate.now());
        HBox titlesAndDates = new HBox(100,title,dates);
        editor.getDeleteButton().setVisible(false);
        editor.setDoubleFormater(new TextFieldFormater().getFormat(), editor.getAmountTextField());
        
        this.chartBox = new HBox();
        this.chartBox.setStyle("-fx-padding: 0 0 25 0;");
        
        this.left = new Button("<");
        this.left.getStyleClass().add("chart-button");
        this.left.setMaxHeight(Double.MAX_VALUE);
        
        StackPane chartPane = new StackPane(yearChart.getRoot(),monthChart.getRoot(),weekChart.getRoot());
        yearChart.getRoot().setVisible(false);
        monthChart.getRoot().setVisible(false);
        chartPane.setMaxSize(320, 200);
        chartPane.setStyle("-fx-padding:0;");
        
        
        this.right = new Button(">");
        this.right.setMaxHeight(Double.MAX_VALUE);
        this.right.getStyleClass().add("chart-button");
        
        VBox breakDown = new VBox();
        breakDown.getStyleClass().add("breakDown");
        
        this.currentBalanceValue = new Label("$ 0");
        this.currentBalanceValue.setMaxWidth(Double.MAX_VALUE);
        this.currentBalanceValue.getStyleClass().add("curNum");
        
        Label curTitle = new Label("Current Balance");
        curTitle.getStyleClass().add("titleText");
        curTitle.setMaxWidth(Double.MAX_VALUE);
        
        this.incomeValue = new Label("$ 0");
        this.incomeValue.setMaxWidth(Double.MAX_VALUE);
        this.incomeValue.getStyleClass().add("incomeNum");
        
        Label incTitle = new Label("Income");
        incTitle.getStyleClass().add("titleText");
        incTitle.setMaxWidth(Double.MAX_VALUE);
        
        this.spendingValue = new Label("$ 0");
        this.spendingValue.getStyleClass().add("spedingNum");
        this.spendingValue.setMaxWidth(Double.MAX_VALUE);
        
        Label spenTitle = new Label("Spending");
        spenTitle.getStyleClass().add("titleText");
        spenTitle.setMaxWidth(Double.MAX_VALUE);
        //C:\Users\jack1\Documents\Budget-Planer\resources\transactionIcons
        editor.populateSelection(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Budget-Planer\\resources\\transactionIcons"));
        Custom_Button newTransaction = new Custom_Button("new Transaction","new_transaction1.png","new_transaction1_in.png","new-button");
        newTransaction.getButton().setOnMouseClicked(event -> {
            editor.show();
        });
        editor.getEditButton().setOnMouseClicked(event -> {
            if(editor.hasValues()){
                db.insertToTransactions(editor.getTitleTextField().getText(),  Double.parseDouble(editor.getAmountTextField().getText()), editor.getTypeTextField().getText(), editor.getDatePicker().getValue().toString(), editor.isSpending(), editor.getIconName());
                
                editor.clear();
                editor.hide();
                Transactions_Map.populateMap();
            }
        });
        editor.getCancelButton().setOnMouseClicked(event -> {
            editor.hide();
            editor.clear();
        });
        breakDown.getChildren().addAll(this.currentBalanceValue,curTitle,this.incomeValue,incTitle,this.spendingValue,spenTitle,newTransaction.getButton());
        this.chartBox.getChildren().addAll(left,chartPane,right,breakDown);
        
        this.root.getChildren().addAll(titlesAndDates,this.chartBox);
        changeBy(0);
    }
    
    public Button getLeft(){
        return left;
    }
    public Button getRight(){
        return right;
    }
    public Editor_Window getEditorWindow(){
        return editor;
    }
    
    public void changeBy(int value){
        switch (chart) {
            case 0:
                if(Current_Date_Range.changeDatesByWeeks(value)) {Transactions_Map.populateMap();}
                weekChart.populateChart();
                break;
            case 1:
                if(Current_Date_Range.changeDatesByMonths(value)) {Transactions_Map.populateMap();}
                monthChart.populateChart();
                break;
            default:
                Current_Date_Range.changeDateRangeByYears(value);
                yearChart.populateChart();
                break;
        }
        dates.setText(Current_Date_Range.getStringStart() +"~"+ Current_Date_Range.getStringEnd());
        this.currentBalanceValue.setText("$ "+NumberFormater.format(Transactions_Map.getCurrentBalance()));
        this.incomeValue.setText("$ "+NumberFormater.format(Transactions_Map.getCurrentIncome()));
        this.spendingValue.setText("$ "+NumberFormater.format(Transactions_Map.getCurrenSpending()));
    }
    public void showWeekChart(){
        chart = 0;
        weekChart.getRoot().setVisible(true);
        this.title.setText("Week's Chart");
        monthChart.getRoot().setVisible(false);
        yearChart.getRoot().setVisible(false);
        Current_Date_Range.transformForWeek();
        Current_Date_Range.redo();
        dates.setText(Current_Date_Range.getStringStart() +"~"+ Current_Date_Range.getStringEnd());
    }
    
    public void showMonthChart(){
        chart = 1;
        weekChart.getRoot().setVisible(false);
        monthChart.getRoot().setVisible(true);
        this.title.setText("Month's Chart");
        yearChart.getRoot().setVisible(false);
        Current_Date_Range.transformForMonth();
        dates.setText(Current_Date_Range.getStringStart() +"~"+ Current_Date_Range.getStringEnd());
    }
    public void showYearChart(){
        chart = 2;
        weekChart.getRoot().setVisible(false);
        monthChart.getRoot().setVisible(false);
        yearChart.getRoot().setVisible(true);
        Current_Date_Range.transformForYear();
        this.title.setText("Year's Chart");
        dates.setText(Current_Date_Range.getStringStart() +"~"+ Current_Date_Range.getStringEnd());
    }
    
    public VBox getRoot(){
        return root;
    }
    
}
