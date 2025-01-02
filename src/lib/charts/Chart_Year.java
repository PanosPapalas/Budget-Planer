/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.charts;

import database.Transactions_Map;
import java.time.LocalDate;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import lib.models.Current_Date_Range;
import lib.utils.DateFormater;
import lib.utils.NumberFormater;

/**
 *
 * @author jack1
 */
public class Chart_Year {
    private final String JANUARY = "JAN";
    private final String FEBRUARY = "FEB";
    private final String MARCH = "MAR";
    private final String APRIL = "APR";
    private final String MAY = "MAY";
    private final String JUNE = "JUN";
    private final String JULY = "JUL";
    private final String AUGUST = "AUG";
    private final String SEPTEMBER = "SEP";
    private final String OCTOBER = "OCT";
    private final String NOVEMBER = "NOV";
    private final String DECEMBER = "DEC";
    
    private double tickStep = 10.0;
    private double upperBound = 100.0;
    
    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    
    private final StringConverter<Number> converter = new StringConverter<Number>(){
            @Override
            public String toString(Number object) {
                return NumberFormater.format(object.longValue());
            }

            @Override
            public Number fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    private final StackedBarChart<String, Number> chart = new StackedBarChart<>(xAxis, yAxis);
    private final Chart_Background background = new Chart_Background();
    private final XYChart.Series<String, Number> spending = new XYChart.Series<>();
    private final XYChart.Series<String, Number> income = new XYChart.Series<>();
    private final Label currentSelectedSpending = new Label("Spending: $ 0");
    private final Label currentSelectedIncome = new Label("Income: $ 0");
    private final VBox selectionShowerBox;//
    private final Pane showLayer;
    private final StackPane root = new StackPane();
    
    private void defaultLook(){
        yAxis.setAutoRanging(false);
        yAxis.setTickUnit(upperBound/tickStep);
        yAxis.setLowerBound(0.0);
        yAxis.setUpperBound(upperBound);
        yAxis.setTickLabelFormatter(converter);
        chart.setLegendVisible(false);
        chart.setCategoryGap(2);
        spending.setName("Spending");
        income.setName("Income");
    }
    
    private XYChart.Data<String, Number> CreateData(String name, Number number){
        return new XYChart.Data<>(name,number);
    }
    
    public Chart_Year(){
        xAxis.setCategories(FXCollections.<String>observableArrayList(
                    Arrays.asList( JANUARY,  FEBRUARY, MARCH , APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER )));
        background.setCategories(xAxis.getCategories());
        
        defaultLook();
        backGroundLogic();
        xAxis.getCategories().forEach(day -> {
            income.getData().add(CreateData(day,0.0));
            spending.getData().add(CreateData(day,0.0));
        });
        
        chart.getData().addAll(spending, income);
        chart.getStyleClass().add("main-week-chart");
//        background.getChart().getData().forEach( series -> {
//            series.getData().forEach( item -> {
//                item.getNode().setOnMouseEntered(event ->{
//                    System.out.println("Week");
//                    System.out.println("Income: "+ getIncomeData((int)item.getExtraValue()) +" Spending: "+ getSpendingData((int)item.getExtraValue()));
//                });
//            });
//        });
        selectionShowerBox = new VBox(5,currentSelectedSpending,currentSelectedIncome);
        currentSelectedSpending.getStyleClass().add("spending-selected");
        currentSelectedIncome.getStyleClass().add("income-selected");
        selectionShowerBox.getStyleClass().add("selection-shower");
        selectionShowerBox.setVisible(false);
        currentSelectedSpending.getStyleClass().add("selection-spending-lebel");
        currentSelectedIncome.getStyleClass().add("selection-income-lebel");
        showLayer = new Pane(selectionShowerBox);
        chart.setMouseTransparent(true);
        showLayer.setMouseTransparent(true);
        root.getChildren().addAll(background.getChart(),chart,showLayer);
    }
    
    public StackPane getRoot(){
        return root;
    }
    public void populateChart(){
        clearChart();
        LocalDate end = Current_Date_Range.getEndDate();
        LocalDate start = Current_Date_Range.getStartDate();
        Transactions_Map.getMap().forEach((id,model) ->{
            LocalDate date = DateFormater.databaseResultDate(model.getDate());
            if((date.isBefore(end)&&date.isAfter(start))||date.isEqual(end)||date.isEqual(start))
            if(model.isSpending()){
                this.addSpending(DateFormater.databaseResultDate(model.getDate()).getDayOfMonth(), model.getAmount());
            }else{
                this.addIncome(DateFormater.databaseResultDate(model.getDate()).getDayOfMonth(), model.getAmount());
            }
        });
    }
    public void clearChart(){
        this.setUpperBound(100);
        this.setTickStep(10);
        income.getData().forEach(item -> {
            item.setYValue(0.0);
        });
        spending.getData().forEach(item -> {
            item.setYValue(0.0);
        });
    }
    
    public void setTickStep(double step){
        this.tickStep = step;
        this.yAxis.setTickUnit(step);
    }
    private void backGroundLogic(){
        background.getChart().getData().forEach( series -> {
            series.getData().forEach( item -> {
                item.getNode().setOnMouseEntered(event ->{
                    selectionShowerBox.setVisible(true);
                    selectionShowerBox.setLayoutX(item.getNode().getLayoutX()+32);
                    currentSelectedIncome.setText("Income: $ "+getIncomeData((int)item.getExtraValue()));
                    currentSelectedSpending.setText("Spending: $ "+getSpendingData((int)item.getExtraValue()));
                    //System.out.println("Income: "+ getIncomeData((int)item.getExtraValue()) +" Spending: "+ getSpendingData((int)item.getExtraValue()));
                });
            });
        });
        background.getChart().getData().forEach( series -> {
            series.getData().forEach( item -> {
                item.getNode().setOnMouseExited(event ->{
                    selectionShowerBox.setVisible(false);
                    //System.out.println("Income: "+ getIncomeData((int)item.getExtraValue()) +" Spending: "+ getSpendingData((int)item.getExtraValue()));
                });
            });
        });
    }
    
    public void setLowerBound(double lowerBound){
        this.yAxis.setLowerBound(lowerBound);
    }
    
    public void setUpperBound(double upperBound){
        this.upperBound = upperBound;
        this.yAxis.setUpperBound(upperBound);
    }
    
    public void setChartSize(double width,double height){
        this.chart.setMaxWidth(width);
        this.chart.setMaxHeight(height);
        background.setChartSize(width, height);
    }
    
    public double getSpendingData(int index){
        if(index<=11 && index>=0){
            return (double)spending.getData().get(index).getYValue();
        }
        return -1.0;
    }
    
    public double getIncomeData(int index){
        if(index<=11 && index>=0){
            return (double)income.getData().get(index).getYValue();
        }
        return -1.0;
    }
    public void checkIsInBounds(int index){
        double max = income.getData().get(index).getYValue().doubleValue()+spending.getData().get(index).getYValue().doubleValue();
        
        if(max>upperBound){
            setUpperBound(max);
            background.setUpperBound(max);
            background.setTickStep(max/10);
            this.setTickStep(max/10);
        }
    }
    
    public void addIncome(int index, Number value){
        if(index-1<0 || index>12) throw new UnsupportedOperationException("Index should be withing [0,6] but index is "+index);
        
        income.getData().get(index-1).setYValue(value);
        checkIsInBounds(index-1);
    }
    public void addSpending(int index, Number value){
        if(index-1<0 || index>12) throw new UnsupportedOperationException("Index should be withing [0,6] but index is "+index);
        
        spending.getData().get(index-1).setYValue(value);
        checkIsInBounds(index-1);
    }
}
