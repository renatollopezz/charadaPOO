/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fucapi.projetocharada.model;

import java.util.Date;

/**
 *
 * @author Desenvolvimento
 */
public class Ranking {
    private Integer idRanking;
    private Date dtJogada;
    private Integer nbPontuacao;
    private Jogador jogador;

    public Integer getIdRanking() {
        return idRanking;
    }

    public void setIdRanking(Integer idRanking) {
        this.idRanking = idRanking;
    }

    public Date getDtJogada() {
        return dtJogada;
    }

    public void setDtJogada(Date dtJogada) {
        this.dtJogada = dtJogada;
    }

    public Integer getNbPontuacao() {
        return nbPontuacao;
    }

    public void setNbPontuacao(Integer nbPontuacao) {
        this.nbPontuacao = nbPontuacao;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
    
}
