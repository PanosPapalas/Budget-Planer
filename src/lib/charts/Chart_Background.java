/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.charts;

import java.util.Timer;
import java.util.TimerTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;
import lib.utils.NumberFormater;

/**
 *
 * @author jack1
 */
public class Chart_Background {
    
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
    
    private final StackedBarChart<String, Number> background = new StackedBarChart<>(xAxis, yAxis);
    private final XYChart.Series<String, Number> seriesData1 = new XYChart.Series<>();//gray part
    private final XYChart.Series<String, Number> seriesData2 = new XYChart.Series<>();//green part
    private final XYChart.Series<String, Number> seriesData3 = new XYChart.Series<>();//red part
    
    public Chart_Background(){
        setYaxisDefault();
    }
    public void redrawCategories(ObservableList categories){
        if(categories.size()<seriesData1.getData().size()){
            for(int i = categories.size(); i<seriesData1.getData().size();i++){
                seriesData1.getData().get(i).setYValue(0.0);
                seriesData2.getData().get(i).setYValue(0.0);
                seriesData3.getData().get(i).setYValue(0.0);
            }
        }else{
            for(int i = xAxis.getCategories().size(); i<categories.size();i++){
                seriesData1.getData().get(i).setYValue(upperBound-2*((upperBound/10)/5));
                seriesData2.getData().get(i).setYValue(tickStep/5);
                seriesData3.getData().get(i).setYValue(tickStep/5);
            }
        }
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            
            @Override
            public void run() {
                xAxis.getCategories().clear();
                xAxis.setCategories(FXCollections.<String>observableArrayList(categories));
                t.cancel();
            }
        }, 900);
    }
    public void setCategories(ObservableList categories){
        if(!categories.isEmpty()){
            xAxis.setCategories(FXCollections.<String>observableArrayList(categories));
            setDefaultValues();
        }
    }
    
    public CategoryAxis getXAxis(){
        return xAxis;
    }
    
    private void setYaxisDefault(){
        yAxis.setAutoRanging(false);
        yAxis.setTickUnit(upperBound/tickStep);
        yAxis.setLowerBound(0.0);
        yAxis.setUpperBound(100.0);
        yAxis.setTickLabelFormatter(converter);
        background.setLegendVisible(false);
        background.getStyleClass().add("background-chart");
        background.getData().addAll(seriesData2,seriesData3,seriesData1);
        background.setCategoryGap(2);
        seriesData1.setName("Background");
    }
    
    private void setDefaultValues(){
        xAxis.getCategories().forEach(category -> {
            seriesData1.getData().add(CreateData(category,upperBound-2*((upperBound/10)/5)));
            seriesData1.getData().get(seriesData1.getData().size()-1).setExtraValue(seriesData1.getData().size()-1);
            seriesData2.getData().add(CreateData(category,tickStep/5));
            seriesData2.getData().get(seriesData2.getData().size()-1).setExtraValue(seriesData1.getData().size()-1);
            seriesData3.getData().add(CreateData(category,tickStep/5));
            seriesData3.getData().get(seriesData3.getData().size()-1).setExtraValue(seriesData1.getData().size()-1);
        });
    }
    
    private XYChart.Data<String, Number> CreateData(String name, Number number){
        return new XYChart.Data<>(name,number);
    }
    
    public StackedBarChart<String, Number> getChart(){
        return background;
    }
    
    public void setSpending(int index,Number value){
        seriesData2.getData().get(index).setYValue(value);
        seriesData1.getData().get(index).setYValue(upperBound-((double)seriesData2.getData().get(index).getYValue()+(double)seriesData3.getData().get(index).getYValue()));
    }
    public void setIncome(int index,Number value){
        seriesData3.getData().get(index).setYValue(value);
        seriesData1.getData().get(index).setYValue(upperBound-((double)seriesData2.getData().get(index).getYValue()+(double)seriesData3.getData().get(index).getYValue()));
    }
    private void setValues(Number amount){
        seriesData1.getData().forEach( item -> { item.setYValue(amount); } );
    }
    private void setMinValues(Number amount){
        seriesData2.getData().forEach( item -> { item.setYValue(amount); } );
        seriesData3.getData().forEach( item -> { item.setYValue(amount); } );
    }
    public void setTickStep(double step){
        this.tickStep = step;
        this.yAxis.setTickUnit(step);
        setMinValues(step/5);
    }
    
    public void setUpperBound(double upperBound){
        this.upperBound = upperBound;
        this.yAxis.setUpperBound(upperBound);
        setValues(upperBound - 2 * ((upperBound/10)/5));
        setTickStep(upperBound/10);
    }
    
    public void setChartSize(double width,double height){
        this.background.setMaxWidth(width);
        this.background.setMaxHeight(height);
    }
    
}