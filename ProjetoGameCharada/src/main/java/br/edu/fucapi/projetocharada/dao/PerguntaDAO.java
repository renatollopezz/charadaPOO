/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fucapi.projetocharada.dao;

import br.edu.fucapi.projetocharada.connection.ConnectionDB;
import br.edu.fucapi.projetocharada.model.Pergunta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author renatolopes
 */
public class PerguntaDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String sql;
    
    public ArrayList<Pergunta> findByGenero(Integer id){
        ArrayList<Pergunta> perguntas = new ArrayList<>();
        
        try {
            connection = ConnectionDB.getConnection();
            sql = "SELECT * FROM pergunta WHERE id_genero = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Pergunta p = new Pergunta();
                p.setIdPergunta(resultSet.getInt("id_pergunta"));
                p.setTxPergunta(resultSet.getString("tx_pergunta"));
                p.setTxResposta(resultSet.getString("tx_resposta"));
                perguntas.add(p);
            }
            System.out.println("Perguntas carregadas!");
            connection.close();
        } catch (Exception e) {
        }
        return perguntas;
    }
}
