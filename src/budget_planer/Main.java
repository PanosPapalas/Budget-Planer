/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package budget_planer;

import database.Goals_Map;
import database.Transactions_Map;
import database.db;
import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lib.components.Chart_Pane;
import lib.components.Editor_Window;
import lib.screens.Main_Page;
import lib.utils.DateFormater;
import lib.utils.MakeDraggalbe;

/**
 *
 * @author jack1
 */
public class Main extends Application {
    /*Remove the Platform.exit() when the project is complete*/
    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        db dataBaseConnection = new db();
        Transactions_Map.populateMap();
        Goals_Map.populateMap();
        Main_Page main = new Main_Page();
        StackPane root = new StackPane(main.getRoot());
        Scene scene = new Scene(root, 987, 680);
        System.out.println(DateFormater.bringToStartOfWeek(LocalDate.of(2024, Month.DECEMBER, 1)));
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("/lib/styles.css").toExternalForm());
        root.setStyle("-fx-background-color: transparent;");
        MakeDraggalbe.makedragable(scene, primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
