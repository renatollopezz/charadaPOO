/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fucapi.projetocharada.view;
import br.edu.fucapi.projetocharada.dao.PerguntaDAO;
import br.edu.fucapi.projetocharada.dao.RankingDAO;
import br.edu.fucapi.projetocharada.model.Genero;
import br.edu.fucapi.projetocharada.model.Jogador;
import br.edu.fucapi.projetocharada.model.Pergunta;
import br.edu.fucapi.projetocharada.model.Ranking;
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
public class PerguntaView extends JFrame{
    private JLabel lblGenero;
    private JLabel lblJogador;
    private JLabel lblPontos;
    private JLabel lblPergunta;
    private JTextField edtResposta;
    private JButton btnDesistir;
    private JButton btnConfirmar;
    private ArrayList<Pergunta> perguntas;
    private final HomeView homeView;
    private final Genero genero;
    private Pergunta perguntaAtual;
    private final Jogador jogador;
    private Integer pontosAtual=0;
    private Integer posPergunta =0;
    private final  RankingDAO daoR = new RankingDAO();
    
    public PerguntaView(HomeView homeView,Jogador jogador,Genero genero) {
    super.setTitle(homeView.getTitle());
    super.setSize(homeView.getSize());
    super.setLayout(null);
    super.setLocationRelativeTo(null);
    super.setVisible(true);
    super.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.homeView = homeView;
    this.genero = genero;
    this.jogador = jogador;
    initComponents();
    }

    private void initComponents() {
        //Criação dos componentes
        Icon iconeConfirmar = new ImageIcon("confirmar.png");
        Icon iconeDesistir = new ImageIcon("desistir.png");
        lblGenero = new JLabel("Gênero: "+genero.getTxGenero());
        lblJogador = new JLabel("Jogador: "+jogador.getTxNome());
        lblPontos = new JLabel("Pts:0");
        lblPergunta = new JLabel();
        edtResposta = new JTextField();
        btnConfirmar = new JButton("Confirmar",iconeConfirmar);
        btnDesistir = new JButton("Desistir",iconeDesistir);
        
        // Defirnir propriedade dos componentes
        lblJogador.setBounds(20, 20, 200, 30);
        lblGenero.setBounds(20, 50, 200, 30);
        lblPontos.setBounds(500, 20, 200, 30);
        btnConfirmar.setBounds(130, 300, 130, 50);
        btnDesistir.setBounds(330, 300, 130, 50);
        edtResposta.setBounds(100, 200, 400,40);

        //Carregar Pergunta
        proximaPergunta();
        lblPergunta.setText(perguntaAtual.getTxPergunta());
        lblPergunta.setBounds(100, 150, 400, 40);
        
        //Adicionar eventos do btnConfirmar
        btnConfirmar.addActionListener((ae) -> {
            validarResposta();
        });
        
        
        btnDesistir.addActionListener((ae) -> {
           desistir();
        });
        
        super.add(lblPontos);
        super.add(lblGenero);
        super.add(lblJogador);
        super.add(lblPergunta);
        super.add(edtResposta);
        super.add(btnConfirmar);
        super.add(btnDesistir);
        
    }

    private void proximaPergunta() {
        PerguntaDAO dao = new PerguntaDAO();
        perguntas = dao.findByGenero(this.genero.getIdGenero());
        
        if(perguntaAtual == null){
            perguntaAtual = perguntas.iterator().next();
            System.out.println(perguntaAtual.getTxPergunta());

        }else{
            posPergunta++;
            if(perguntas.size() > posPergunta){
                perguntaAtual =  perguntas.get(posPergunta);
                System.out.println(perguntaAtual.getTxPergunta());
                lblPergunta.setText(perguntaAtual.getTxPergunta());
                atualizarRanking();

            }else{
                Icon iconeFim = new ImageIcon("fim.png");
                JOptionPane.showMessageDialog(this, "Parabéns, você finalizou!","Fim de jogo",JOptionPane.INFORMATION_MESSAGE,iconeFim);
                atualizarRanking();
                this.dispose();
                this.homeView.setVisible(true); 
            }
        }
    }

    private void validarResposta() {
        String resposta;
        resposta = edtResposta.getText().toUpperCase();
        if(resposta.equals(perguntaAtual.getTxResposta().toUpperCase())){
            this.pontosAtual += 10;

            edtResposta.setText(null);
            lblPontos.setText("Pts:"+pontosAtual.toString());
            JOptionPane.showMessageDialog(this, "Parabéns, resposta correta!");
            
             proximaPergunta();
        }else{
            JOptionPane.showMessageDialog(this, "Resposta incorreta!");

        }
        
    }

    private void desistir() {
        Integer opcao;
        opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente desistir?");
        if(opcao == 0){
            this.dispose();
            this.homeView.setVisible(true); 
        }
       
        
    }

    private void atualizarRanking() {
       
        Ranking r;
        r = daoR.findByGenero(genero.getIdGenero(), jogador.getIdJogador());
        if(r.getIdRanking() == null){
            daoR.save(genero.getIdGenero(), jogador.getIdJogador(),pontosAtual);
        }else{
            daoR.update(genero.getIdGenero(), jogador.getIdJogador(),pontosAtual);
        }   
    }
    
    
}
