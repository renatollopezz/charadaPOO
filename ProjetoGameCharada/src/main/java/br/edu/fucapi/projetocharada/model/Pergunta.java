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
public class Pergunta {
    private Integer idPergunta;
    private String txPergunta;
    private String txResposta;
    private Genero genero;
    private String txDica;

    public String getTxDica() {
        return txDica;
    }

    public void setTxDica(String txDica) {
        this.txDica = txDica;
    }
    
    

    public Integer getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(Integer idPergunta) {
        this.idPergunta = idPergunta;
    }

    public String getTxPergunta() {
        return txPergunta;
    }

    public void setTxPergunta(String txPergunta) {
        this.txPergunta = txPergunta;
    }

    public String getTxResposta() {
        return txResposta;
    }

    public void setTxResposta(String txResposta) {
        this.txResposta = txResposta;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    
    
    
}
