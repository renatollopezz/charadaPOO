/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fucapi.projetocharada.view;


import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author renatolopes
 */
public class HomeView extends JFrame{
    public HomeView() throws IOException{
        super.setTitle("Qual é a charada?");
        super.setVisible(true);
        super.setSize(600, 400);
        super.setLayout(null);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }
    
    public static void main(String[] args) throws IOException {
        new HomeView();
    }

    private void initComponents() throws IOException {
        // icone start
        Icon startIcon = new ImageIcon("start.png");
       
        // icone de ranking
       Icon rankingIcon = new ImageIcon("ranking.png");

        // Criação do Botao de Start
        JButton btnStart = new JButton("Começar",startIcon);
        //btnStart.setIcon(startIcon);
        btnStart.setBounds(100, 200, 130, 50);
        
        btnStart.addActionListener((e) -> {
            super.dispose();
            //chamar tela de genero
            new GeneroView(this);
        });
        
        // Criacao do Botao de Ranking
        JButton btnRanking = new JButton("Ranking",rankingIcon);
       // btnRanking.setIcon(rankingIcon);
        btnRanking.setBounds(350, 200, 130, 50);
        
        
        
        btnRanking.addActionListener((e) -> {
            super.dispose();
            new RankingView(this);
        });
        
        // Label dos desenvolvedores
        JLabel devs =  new JLabel("Developed by: renato lopes & Ana Paula");
        devs.setBounds(160, 300, 500, 100);
        
        //Adicionar componentes no JFrame
        super.add(btnStart);
        super.add(btnRanking);
        super.add(devs);
        
    }
}
