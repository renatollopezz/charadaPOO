/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fucapi.projetocharada.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author renatolopes
 */
public class ConnectionDB {
    private static Connection connection;
    
    public static Connection getConnection(){
        try {
                Class.forName("org.postgresql.Driver");
                String user = "postgres";
                String pass = "123";
                String URL = "jdbc:postgresql://localhost:5432/projeto";
                connection =  DriverManager.getConnection(URL,user,pass);
                System.out.println("Conectado com sucesso!");
        } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
        }
        
        return connection;
    }

    
}
