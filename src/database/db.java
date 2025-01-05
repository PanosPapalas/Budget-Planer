package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import lib.models.Goal_Data_Model;
import lib.models.Transaction_Data_Model;

/*
SELECT column1, column2....columnN FROM table_name WHERE CONDITION-1 {AND|OR} CONDITION-2;
SELECT column1, column2....columnN FROM table_name WHERE column_name BETWEEN val-1 AND val-2;
DELETE FROM table_name WHERE {CONDITION};
SELECT column1, column2....columnN FROM table_name WHERE column_name EXISTS (SELECT * FROM   table_name );
<*>INSERT INTO table_name( column1, column2....columnN) VALUES ( value1, value2....valueN);
<*>UPDATE table_name SET column1 = value1, column2 = value2....columnN=valueN [ WHERE  CONDITION ];
SELECT * FROM TRANSACTIONS WHERE TIME>= [MONDAY] AND TIME <= [SUNDAY];
*/
public class db {
    private final String path = "C:/Users/"+System.getProperty("user.name")+"/Documents/Budget-Planer";
    private final String url = "jdbc:sqlite:"+path+"/Database/"+"db.db";
    private static ResultSet rs = null;
    private static Statement stmt = null;
    private static Connection conn = null; 
    
    private boolean createFile(){
        return new File(path+"/Database").mkdir();
    }
    
    private static void execSTMT(String command){
        try{
            if(conn != null){
                stmt = conn.createStatement();
                stmt.executeUpdate(command);
                stmt.close();
            }
        }catch(SQLException e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    public static HashMap<Integer, Goal_Data_Model> getGoalsResults(HashMap<Integer, Goal_Data_Model> map, String Condition){
        map = new HashMap<>();
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery( "SELECT * FROM GOALS "+Condition+";" );
            while(rs.next() !=false){
                map.put(rs.getInt("ID"), new Goal_Data_Model(rs.getInt("ID"),rs.getString("TITLE"),rs.getDouble("CAMOUNT"),rs.getDouble("TAMOUNT"),rs.getString("ICONNAME")));
            }
            rs.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return map;
    }
    
    public static HashMap<Integer, Transaction_Data_Model> getTransactionsResults(HashMap<Integer, Transaction_Data_Model> map,String argument){
        map = new HashMap<>();
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery( "SELECT * FROM TRANSACTIONS WHERE TIME "+ argument +";" );
            while(rs.next() !=false){
                map.put(rs.getInt("ID"), new Transaction_Data_Model(rs.getInt("ID"),rs.getString("TITLE"),rs.getDouble("AMOUNT"),rs.getString("TYPE"),rs.getString("TIME"),rs.getBoolean("ISSPENDNIG"),rs.getString("ICONNAME")));
            }
            rs.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return map;
    }
    
    public ResultSet getResults(String table, String Condition){
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery( "SELECT * FROM "+table+" "+Condition+";" );
            return rs;
        }catch(SQLException e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return null;
    }
    public static void deleteWithID(String table, int id){
        execSTMT("DELETE FROM "+table+" WHERE ID = "+id+";");
    }
    
    public static void updateTransactionTable(String title, double amount, String type, String date, boolean tranType, String iconName, int id){
        execSTMT("UPDATE TRANSACTIONS SET TITLE ="+title+",AMOUNT ="+amount+",TYPE ="+type+",TIME ="+date+",ISSPENDNIG ="+tranType+",ICONNAME ="+iconName+"WHERE ID ="+id+";");
    }
    
    public static void updateGoalTable(String titleValue,double cAmount,double tAmount, String icon, int id){
        execSTMT("UPDATE GOAlS SET TITLE ="+titleValue+",CAMOUNT ="+cAmount+",TAMOUNT ="+tAmount+",ICONNAME ="+icon+"WHERE ID ="+id+";");
    }
    
    public static void insertToTransactions(String title, double amount, String type, String date, boolean tranType, String iconName){
        execSTMT("INSERT INTO TRANSACTIONS(TITLE,AMOUNT,TYPE,TIME,ISSPENDNIG,ICONNAME) VALUES ('" + title + "'," + amount + ",'" + type + "','" + date + "','" + (tranType ?1:0) + "','" + iconName + "');");
    }
    public static void insertToGoals(String titleValue,double cAmount,double tAmount, String icon){
        execSTMT("INSERT INTO GOALS(TITLE, CAMOUNT, TAMOUNT, ICONNAME) VALUES ('" + titleValue + "'," + cAmount + "," + tAmount + ",'" + icon + "');");
    }
    
    public static void closeConnection() throws SQLException{
        if(conn != null){
            conn.close();
        }
    }
    
    public db(){
        createFile();
        try{
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            if(conn !=null){
                execSTMT("CREATE TABLE IF NOT EXISTS TRANSACTIONS(ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE VARCHAR(100) NOT NULL, AMOUNT DOUBLE NOT NULL, TYPE VARCHAR(100), TIME CHAR(11) NOT NULL, ISSPENDNIG BOOLEAN NOT NULL, ICONNAME VARCHAR(50))");
                execSTMT("CREATE TABLE IF NOT EXISTS GOALS(ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE VARCHAR(30), CAMOUNT DOUBLE, TAMOUNT DOUBLE, ICONNAME VARCHAR(50))");
            }
        }catch(SQLException ex){
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        }
    }
}
