/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fucapi.projetocharada.model;

/**
 *
 * @author Desenvolvimento
 */
public class Genero {
    private Integer idGenero;
    private String txGenero;
    private String txImagem;

    public String getTxImagem() {
        return txImagem;
    }

    public void setTxImagem(String txImagem) {
        this.txImagem = txImagem;
    }
    
    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getTxGenero() {
        return txGenero;
    }

    public void setTxGenero(String txGenero) {
        this.txGenero = txGenero;
    }
    
}
