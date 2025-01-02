/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.util.HashMap;
import lib.models.Goal_Data_Model;

/**
 *
 * @author jack1
 */
public class Goals_Map {
    private static HashMap<Integer, Goal_Data_Model> map = new HashMap<>();
    
    public static void populateMap(){
        map = db.getGoalsResults(map, "");
    }
    public static HashMap<Integer, Goal_Data_Model> getMap(){
        return map;
    }
}
