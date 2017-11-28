
package br.edu.fucapi.projetocharada.dao;

import br.edu.fucapi.projetocharada.connection.ConnectionDB;
import br.edu.fucapi.projetocharada.model.Ranking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Desenvolvimento
 */
public class RankingDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String sql;
    
    public ArrayList<Ranking> findAll(){
        
        ArrayList<Ranking> rankings = new ArrayList<>();
        try {
            connection = ConnectionDB.getConnection();
            sql = "select id_jogador,sum(nb_pontuacao) as total from ranking group by id_jogador order by total desc;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Ranking r = new Ranking();
                r.setNbPontuacao(resultSet.getInt("total"));
                r.setJogador(new JogadorDAO().findById(resultSet.getInt("id_jogador")));
                rankings.add(r);
            }
            connection.close();
            System.out.println("Fim da consulta!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  rankings;
    }
      
    public Ranking findByGenero(Integer idGenero,Integer idJogador){
        Ranking ranking = new Ranking();
        sql= "SELECT * FROM ranking "
                + "WHERE id_genero = ? AND id_jogador = ? ;";
        try{
        connection = ConnectionDB.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idGenero);
        preparedStatement.setInt(2, idJogador);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        ranking.setDtJogada(resultSet.getDate("dt_data_jogada"));
        ranking.setIdRanking(resultSet.getInt("id_ranking"));
        ranking.setNbPontuacao(resultSet.getInt("nb_pontuacao"));
        connection.close();
        
        }catch(SQLException e){System.out.println(e.getMessage());}
        
        return ranking;
    }
    
    public void save(Integer idGenero,Integer idJogador,Integer pontuacao){
        try {
            sql = "INSERT INTO ranking (id_jogador,id_genero,nb_pontuacao) VALUES(?,?,?);";
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idJogador);
            preparedStatement.setInt(2, idGenero);
            preparedStatement.setInt(3, pontuacao);
            preparedStatement.execute();
            connection.close();
            System.out.println("Ranking salvo");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(Integer idGenero,Integer idJogador,Integer pontuacao){
        try {
            sql = "UPDATE ranking SET nb_pontuacao = ?"
                  + " WHERE id_jogador = ? AND id_genero = ?;";
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pontuacao);
            preparedStatement.setInt(2, idJogador);
            preparedStatement.setInt(3, idGenero);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
}
