/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fucapi.projetocharada.dao;

import br.edu.fucapi.projetocharada.connection.ConnectionDB;
import br.edu.fucapi.projetocharada.model.Genero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Desenvolvimento
 */
public class GeneroDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String sql;
    
    
    public ArrayList<Genero> findAll(){
       
        ArrayList<Genero> generos = new ArrayList<>();
        try {
            connection = ConnectionDB.getConnection();
            sql = "SELECT * FROM genero;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                Genero g = new Genero();
                g.setIdGenero(resultSet.getInt("id_genero"));
                g.setTxGenero(resultSet.getString("tx_nome"));
                g.setTxImagem(resultSet.getString("tx_dir_img"));
                generos.add(g);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return generos;
    }
}
