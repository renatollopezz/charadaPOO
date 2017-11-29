/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fucapi.projetocharada.view;

import br.edu.fucapi.projetocharada.dao.GeneroDAO;
import br.edu.fucapi.projetocharada.dao.JogadorDAO;
import br.edu.fucapi.projetocharada.model.Genero;
import br.edu.fucapi.projetocharada.model.Jogador;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Desenvolvimento
 */
public class GeneroView extends JFrame {
    private GeneroDAO dao;
    private Genero genero;
    private Jogador jogador;
    private final HomeView homeView;
    private JButton marcado;
    
    public GeneroView(HomeView homeView) {
        super.setTitle(homeView.getTitle());
        super.setSize(homeView.getSize());       
        super.setVisible(true);
        super.setLayout(null);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.homeView =  homeView;
        initComponents();
    }

    private void initComponents() {
        //Instancia dos componentes
        Icon icone = new ImageIcon("voltar.png");
        Icon iconeIniciar = new ImageIcon("iniciar.png");
        JTextField edtNome = new JTextField();
        JButton btnVoltar = new JButton("Voltar",icone);
        JLabel lblNomeJogador = new JLabel("Informe seu nome:");
        JButton btnIniciar = new JButton("Iniciar",iconeIniciar);
        
        // Definir posição dos componentes
        btnIniciar.setBounds(400, 300, 150, 50);
        edtNome.setBounds(20,40,430,40);       
        btnVoltar.setBounds(20, 300, 150, 50);
        lblNomeJogador.setBounds(20,20,140  ,10);
        
        //buscar todos os generos e criar os botões de forma dinamica
        dao = new GeneroDAO();
        ArrayList<Genero> generos = dao.findAll();
        int x;
        int y = 20;
        int i =2;
        
        for (Genero g : generos) {
              Icon iconeBtn = new ImageIcon(g.getTxImagem());
              JButton btn = new JButton(g.getTxGenero(),iconeBtn);
              if((i%2)==0){
                  y+=80;
                  x=20;
                  i++;
              }else{
                  x=300;
                  i++;
              }
              // Adicionar evento ao botão generico gerado
              btn.addActionListener((ActionEvent e) -> {
                  definirEscolha(g);
                  if(marcado == null){
                      btn.setBackground(new Color(153,218,155));
                      marcado = btn;
                  }else{
                    marcado.setBackground(null);
                    marcado = btn;
                      btn.setBackground(new Color(153,218,155));
                  }

                  
              });
              //Definir posição do botão
              btn.setBounds(x, y, 150, 50);
              super.add(btn);
        }

        //Adicionar eventos nos botões comecar e voltar
        btnIniciar.addActionListener((e) -> {
                iniciarPartida(edtNome.getText());
        });
        btnVoltar.addActionListener((ae) -> {
            this.dispose();
            homeView.setVisible(true);
        });
        
        //Adicionando os componentes no JFrame de Genero
        super.add(edtNome);
        super.add(btnIniciar);
        super.add(btnVoltar);
        super.add(lblNomeJogador);

    }
    
    //Este metodo define a escolha do usuário
    private void definirEscolha(Genero genero) {
        this.genero = genero;
    }
    //Este metodo verifica as obrigatoriedades e inicia partida
    private void iniciarPartida(String text) {
        if(this.genero == null){
            JOptionPane.showMessageDialog(this, "Selecione um gênero!");
        }else{
            if(!text.isEmpty() && text.trim().length() > 0){
            JogadorDAO dao = new JogadorDAO();
            this.jogador = dao.findByName(text) ; 
                if(jogador.getIdJogador() == null){
                    dao.save(text);
                    jogador = dao.findByName(text);
                }
                   super.dispose();
                   new PerguntaView(this.homeView,jogador,genero);
            }else{
                JOptionPane.showMessageDialog(this, "Informe um nome valido!");
            }
        }

    }
    
}
