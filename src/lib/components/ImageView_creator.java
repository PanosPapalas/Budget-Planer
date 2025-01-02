/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lib.components;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author jack1
 */
public class ImageView_creator {
    //Might have to delete this
    public static ImageView createView(File imageFile){
        if(imageFile.exists() && !imageFile.isDirectory()){
            return new ImageView(new Image(imageFile.toURI().toString()));
        }
        return new ImageView();
    }
}
