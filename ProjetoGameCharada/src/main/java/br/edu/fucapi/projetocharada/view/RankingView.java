/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fucapi.projetocharada.view;

import br.edu.fucapi.projetocharada.dao.RankingDAO;
import br.edu.fucapi.projetocharada.model.Ranking;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Desenvolvimento
 */
public class RankingView extends JFrame{
    private HomeView home;
    private JTable table;
    private RankingDAO dao ;
    String[] colunas = {"ID","NOME","PONTOS"};
    
    public RankingView(HomeView homeView) {
        super.setTitle(homeView.getTitle());
        super.setSize(homeView.getSize());       
        super.setVisible(true);
        super.setLayout(null);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.home = homeView;
        this.initComponents();
    }
    
   public void initComponents(){
       Icon voltarIcon = new ImageIcon("voltar.png");

       //Criando botão
       JButton btnVoltar = new JButton("Voltar",voltarIcon);
       btnVoltar.setBounds(50, 280, 150, 50);
       //evento voltar
       btnVoltar.addActionListener( (e) -> {
          //acao para remover a tela 
          super.dispose();
          // voltar tela home
          home.setVisible(true);
       });
       // criar obj tabela
        table = new JTable();
        
        // setar modelo padrão da tabela adicionando array das colunas
        table.setModel(new DefaultTableModel(new Object [][]{}, colunas));
        //criando um painel com scroll e adicionando a tabela dentro
        JScrollPane scrollPane = new JScrollPane(table);
        // tamanhos do painel da tabela
        scrollPane.setBounds(50, 60, 500, 200);
        // setar as linhas para 0
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setNumRows(0);
        //instancia de jogador para obter os dados do banco
        ArrayList<Ranking> rankings;
        //buscar jogador pelo ID
        dao = new RankingDAO();
        rankings = dao.findAll();
        
        //adicionar o jogador na tabela, caso de um array list usar foreach
        for(Ranking r : rankings){
           modelo.addRow(new Object[]{r.getJogador().getIdJogador(),r.getJogador().getTxNome(),r.getNbPontuacao()});
        }
        JLabel infoRanking = new JLabel("Ranking dos jogadores:");
        infoRanking.setBounds(50,-10, 500, 100);
       // adicionar componentes no Frame     
       super.add(scrollPane);
       super.add(btnVoltar);
       super.add(infoRanking);
       
   }
}
