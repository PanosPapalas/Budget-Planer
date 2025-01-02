/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.utils;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author jack1
 */
public class AnchorPaneInsets {
    
    public static void Insets(Node node, double top, double right, double bottom, double left){
        AnchorPane.setTopAnchor(node, top >= 0.0 ? top : null);
        AnchorPane.setRightAnchor(node, right >= 0.0 ? right : null);
        AnchorPane.setBottomAnchor(node, bottom >= 0.0 ? bottom : null);
        AnchorPane.setLeftAnchor(node, left >= 0.0 ? left : null);
    }
}
