/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.ConnectionBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author rim
 */
public class ContenusService {
	
protected    PreparedStatement preStmt ;
protected    Statement stmt ;
protected    String query;
protected    Connection connection ;
protected    ResultSet rs;

	public ContenusService() {
		preStmt = null;
		stmt = null;
		query = "";
		connection = ConnectionBase.getInstance().getCnx(); 
			
	}
	
	
		



	
}
