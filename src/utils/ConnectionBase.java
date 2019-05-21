/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author FirasM
 */
public class ConnectionBase {

    private String url = "jdbc:mysql://localhost:3306/ecosystem";
    private String login = "root";
    private String pwd = "";
    private Connection cnx;
    private static ConnectionBase instance; // Pour éviter plusieurs  connexions

    private ConnectionBase() {

        try {
            cnx = (Connection) DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection établie ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static ConnectionBase getInstance() {
        if (instance == null) {
            instance = new ConnectionBase();
        }
        return (instance);
    }

    public Connection getCnx() {
        return cnx;
    }

}
