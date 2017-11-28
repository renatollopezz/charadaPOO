/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fucapi.projetocharada.dao;

import br.edu.fucapi.projetocharada.connection.ConnectionDB;
import br.edu.fucapi.projetocharada.model.Jogador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author renatolopes
 */
public class JogadorDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String sql;
    
    public Jogador findById(Integer id){
        Jogador jogador = new Jogador();
        try {
            connection = ConnectionDB.getConnection();
            sql = "SELECT * FROM jogador WHERE id_jogador = ? ;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            jogador.setIdJogador(resultSet.getInt("id_jogador"));
            jogador.setTxNome(resultSet.getString("tx_nome"));
            connection.close();
            System.out.println("Fim da consulta!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return  jogador;
    }
    
    public ArrayList<Jogador> findAll(){
        ArrayList<Jogador> jogadores = new ArrayList<>();
        try {
            connection = ConnectionDB.getConnection();
            sql = "SELECT * FROM jogador ;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                Jogador j = new Jogador();
                j.setIdJogador(resultSet.getInt("id_jogador"));
                j.setTxNome(resultSet.getString("tx_nome"));
                jogadores.add(j);
            }
            System.out.println("Fim da consulta!");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return  jogadores;
    }
    
    public Integer findPts(Integer id){
        Integer pts=0;
        try {
            connection = ConnectionDB.getConnection();
            sql = "select nb_pontuacao from ranking where id_jogador = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery(); 
            resultSet.next();
            pts = resultSet.getInt("nb_pontuacao");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return pts;
    }
 
    public void save(String nome){
        try {
                connection = ConnectionDB.getConnection();
                sql = "INSERT INTO jogador (tx_nome) VALUES (?);";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, nome);
                resultSet = preparedStatement.executeQuery();
                connection.close();
                System.out.println("Regitrado com sucesso!");
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public Jogador findByName(String name){
    Jogador jogador = new Jogador();
        try {
            connection = ConnectionDB.getConnection();
            sql = "SELECT id_jogador,tx_nome FROM jogador WHERE tx_nome = ? ;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getInt("id_jogador"));
            jogador.setIdJogador(resultSet.getInt("id_jogador"));
            jogador.setTxNome(resultSet.getString("tx_nome"));
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return jogador;   
    }
}
