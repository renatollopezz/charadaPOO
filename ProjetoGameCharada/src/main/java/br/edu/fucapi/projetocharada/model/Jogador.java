/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fucapi.projetocharada.model;

/**
 *
 * @author renatolopes
 */
public class Jogador {
    private Integer idJogador;
    private String txNome;

    public Integer getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Integer idJogador) {
        this.idJogador = idJogador;
    }

    public String getTxNome() {
        return txNome;
    }

    public void setTxNome(String txNome) {
        this.txNome = txNome;
    }

    @Override
    public String toString() {
        return "Retorno Jogador";
    }
    
    
}
