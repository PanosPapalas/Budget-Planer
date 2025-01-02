/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.components;

import java.time.LocalDate;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import lib.models.Transaction_Table_Data_Model;

/**
 *
 * @author jack1
 */
public class Transactions_Table_View {
    private final TableView<Transaction_Table_Data_Model> table = new TableView<>();
    private TableRow<Transaction_Table_Data_Model> selectedRow;
    private final ObservableList<Transaction_Table_Data_Model> item_list = FXCollections.observableArrayList();
    private final TableColumn<Transaction_Table_Data_Model, HBox> titleColoumn = new TableColumn<>("Title");
    private final TableColumn<Transaction_Table_Data_Model,String> amountColoumn = new TableColumn<>("Amount");
    private final TableColumn<Transaction_Table_Data_Model,String> typeColoumn = new TableColumn<>("Type");
    private final TableColumn<Transaction_Table_Data_Model,LocalDate> dateColoumn = new TableColumn<>("Date");
    private final SimpleBooleanProperty picked = new SimpleBooleanProperty(false);
    private boolean oldPick = false;
    
    public Transactions_Table_View(){
        ClickedRowSelector();
        setRowFactories();
        columnsInsert();
    }
    
    private void ClickedRowSelector(){
        this.table.setRowFactory(tv ->{
            TableRow<Transaction_Table_Data_Model> row = new TableRow<>();
            row.setOnMouseClicked(event ->{
                if(!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
                    this.selectedRow = row;
                    this.oldPick = true;
                    this.picked.set(true);
                }
            });
            return row;
        });
    }
    
     public TableView<Transaction_Table_Data_Model> getTable(){
        return table;
    }
    public ObservableList<Transaction_Table_Data_Model> getList(){
        return item_list;
    }
    
    public TableRow<Transaction_Table_Data_Model> getselectedRow(){
        return selectedRow;
    }
    
    private void setRowFactories(){
        titleColoumn.setCellValueFactory(new PropertyValueFactory<>("HBox"));
        titleColoumn.setPrefWidth(170);
        amountColoumn.setCellValueFactory(new PropertyValueFactory<>("StringedAmount"));
        amountColoumn.setPrefWidth(170);
        typeColoumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColoumn.setPrefWidth(170);
        dateColoumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColoumn.setPrefWidth(170);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    private void columnsInsert(){
        table.getColumns().addAll(titleColoumn,typeColoumn,amountColoumn,dateColoumn);
        table.setItems(item_list);
    } 
    public SimpleBooleanProperty isPicked(){
        return picked;
    }
    public boolean getOldPick(){
        return oldPick;
    }
    
    public void setOldPick(boolean value){
        oldPick = value;
    }
    
    public void setPicked(boolean value){
        picked.set(value);
    }
    
    public void setNewItem(Transaction_Table_Data_Model newTransactio){
        item_list.add(0, newTransactio);
    }
    
}
