/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Hiro
 */
public class DBHelper {

    private DBHelper() {
    }
    
    /**
     *
     * @param Query to Execute 
     * @return Returns the ResultSet of the Query executed
     */
    public static ResultSet GetResultSetFromQuerry(String Query){
        ResultSet ResultSetToReturn = null;
        try {
            PreparedStatement PreparedST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
            ResultSetToReturn = PreparedST.executeQuery();
        } catch (SQLException ex) {
            //In case of an error
            System.out.print("DBHelper:: Error at Request in the Query"+ Query);
        }
        return ResultSetToReturn;
    }
    
    
    /**
     * EXPERIMENTAL
     * @param TableName The name of the Table
     * @param ColumnNames The Columns to display
     * @param Condition The Conditions to add.    Note : without ().
     * @return Returns the ResultSet of the Tables
     */
    public static ResultSet GetResultSetFromQuerryOptions(String TableName, String[] ColumnNames, String Condition){

        // Initializing the Query
        String QueryToExec = "SELECT ";
        
        
        //Adding the Columns to Display
        if (ColumnNames.length > 0){
            String ColumnReqString = "";
            boolean isMultipleColumns = ( ColumnNames.length > 1 );
            for(String Column : ColumnNames){
                if (isMultipleColumns){
                    ColumnReqString = ColumnReqString + "," + Column;
                }
                else {
                    ColumnReqString = Column;
                }
            }
            QueryToExec = QueryToExec + ColumnReqString + " ";
        }
        
        //Adding the Table to display from
        QueryToExec = QueryToExec + "FROM " + TableName + " ";
        
        //Adding the Condition
        if (!Condition.isEmpty()){
            QueryToExec = QueryToExec + "WHERE (" + Condition + ")"; 
        }
        
        // Execute the Query into a ResultSet
        ResultSet ResultSetToReturn = null;
        try {
            PreparedStatement PreparedST =ConnectionBase.getInstance().getCnx().prepareStatement(QueryToExec);
            ResultSetToReturn = PreparedST.executeQuery();
        } catch (SQLException ex) {
            //In case of an error
            System.out.print("DBHelper:: Error at Request in "+ TableName);
        }
        return ResultSetToReturn;
    }
    
    /**
     *
     * @param Query The query to execute Example: "INSERT INTO TableName (ColumnName1, ColumnName2) VALUES (? ,?)"
     * @param ObjectsToAdd Objects to add as array
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    public static int ExecuteUpdateQuery(String Query,Object ObjectsToAdd[]) throws SQLException{
       
        //Init Statement
        PreparedStatement PreparedST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        
        //Bind the values to the Statement
        for(Object ObjectTemp : ObjectsToAdd){
            PreparedST.setObject(GlobalLibrary.find(ObjectsToAdd, ObjectTemp), ObjectTemp);
        }

        
        return PreparedST.executeUpdate(Query);
   }
   
    
    

    
    

}
