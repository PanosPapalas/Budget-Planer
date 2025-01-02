/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.screens;

import database.Transactions_Map;
import database.db;
import java.io.File;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import lib.components.Editor_Window;
import lib.components.Transactions_Table_View;
import lib.models.Transaction_Table_Data_Model;
import lib.utils.AnchorPaneInsets;
import lib.utils.DateFormater;
import lib.utils.TextFieldFormater;

/**
 *
 * @author jack1
 */
public class Transactions_Page {
    private final Label title = new Label("Transaction History");
    private final Transactions_Table_View table = new Transactions_Table_View();
    private final Editor_Window editPane = new Editor_Window("Confirm","Delete","Cancel","Title","Type","Amount");
    private final Node[] content = new Node[4];
    private final String url = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Budget-Planer\\resources\\transactionIcons";
    
    public Transactions_Page(){
        editPane.setAppearButtonClass("main-menu-button");
        AnchorPaneInsets.Insets(title,16,-1.0,-1.0,35.0);
        AnchorPaneInsets.Insets(editPane.getAppearButton(),55,-1.0,-1.0,35.0);
        AnchorPaneInsets.Insets(table.getTable(),115,20.0,10.0,35.0);
        
        editPane.populateSelection(new File(url));
        editPane.setDoubleFormater(new TextFieldFormater().getFormat(), editPane.getAmountTextField());
        editPane.setAppearText("New Transaction");
        editPane.getRoot().visibleProperty().bind(table.isPicked());
        editPane.getRoot().visibleProperty().addListener((ob,oldValue,newValue)->{
            if(newValue && table.getOldPick()){
                Transaction_Table_Data_Model t = table.getselectedRow().getItem();
                editPane.setPickerValues(t.getID(),t.getIconName(),t.getTitle(),t.getType().getText(),String.valueOf(t.getAmount()),t.getImage().getImage(),t.getIsNegative(),t.getDate());
            }
        });
        Transactions_Map.getMap().forEach((key,item) -> {
            table.setNewItem(new Transaction_Table_Data_Model(item));
        });
        editPane.setAlertTitle("Delete Transaction");
        editPane.setAlertHeader("Deleting Selected");
        editPane.setAlertContent("Do you want to delete the selected transaction? \n This action is not reversible.");
        AnchorPaneInsets.Insets(editPane.getRoot(),115,-1.0,-1.0,200.0);
        EditPaneLogic();
        
        content[0] = title;
        content[1] = editPane.getAppearButton();
        content[2] = table.getTable();
        content[3] = editPane.getRoot();
    }
    
    public Transactions_Table_View getTable(){
        return table;
    }
    
    public Node[] getContent(){
        return content;
    }
    
    private void EditPaneLogic(){
        editPane.getDeleteButton().setOnMouseClicked(event ->{
            if(editPane.hasValues() && table.getOldPick())
            editPane.getAlert().showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK){
                    if(editPane.hasValues() && table.getOldPick()){
                        table.getList().remove(table.getselectedRow().getIndex());
                        db.deleteWithID("TRANSACTIONS", table.getselectedRow().getItem().getID());
                        table.setPicked(false);
                        table.setOldPick(false);
                        editPane.clear();
                    }
                }
            });
        });
        editPane.getEditButton().setOnMouseClicked(event ->{
            if(editPane.hasValues() && table.getOldPick()){
                Transaction_Table_Data_Model t = new Transaction_Table_Data_Model(
                        editPane.getIconName(),
                        editPane.getImageFile(),
                        editPane.getTitleTextField().getText(),
                        Double.parseDouble(editPane.getAmountTextField().getText()),
                        editPane.getTypeTextField().getText(),
                        editPane.isSpending(),
                        DateFormater.dateToString(editPane.getDatePicker().getValue()));
                t.setID(editPane.getID());
                table.getList().set(table.getselectedRow().getIndex(), t);
                db.updateTransactionTable(t.getTitle(), t.getAmount(), t.getType().getText(), t.getDate(), t.getIsNegative(), t.getIconName(), t.getID());
            }else{
                table.setNewItem(new Transaction_Table_Data_Model(
                        editPane.getIconName(),
                        editPane.getImageFile(),
                        editPane.getTitleTextField().getText(),
                        Double.parseDouble(editPane.getAmountTextField().getText()),
                        editPane.getTypeTextField().getText(),
                        editPane.isSpending(),
                        DateFormater.dateToString(editPane.getDatePicker().getValue())
                ));
                db.insertToTransactions(editPane.getTitleTextField().getText(),  Double.parseDouble(editPane.getAmountTextField().getText()), editPane.getTypeTextField().getText(), editPane.getDatePicker().getValue().toString(), editPane.isSpending(), editPane.getIconName());
            }
            table.setPicked(false);
            table.setOldPick(false);
            editPane.clear();
        });
        editPane.getCancelButton().setOnMouseClicked(event->{editPane.clear();table.setPicked(false); table.setOldPick(false);});
        editPane.getAppearButton().setOnMouseClicked(event -> {
            table.setPicked(true);
        });
    }
}