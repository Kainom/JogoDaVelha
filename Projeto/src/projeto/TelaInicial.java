/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class TelaInicial extends JFrame implements ActionListener {
    
    private int pausado;
    private JogoVelha pause;
    private JPanel jpInicial;
    private JButton bntHistor, bntGame;
    private ImageIcon game, historia,icon;
    
    public TelaInicial() {
        configurarJanela();
        configurarPanel();
    }
    
    public TelaInicial(int pausado, JogoVelha pause) {
        configurarJanela();
        configurarPanel();
        this.pausado = pausado;
        this.pause = pause;
    }
    
    private void configurarJanela() {
        this.setSize(1000, 500);
        this.setTitle("BEM VINDO AO GAME");
        this.getContentPane().setBackground(Color.black);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
    }
    
    private void configurarPanel() {
        jpInicial = new JPanel();
        this.jpInicial.setLayout(new FlowLayout(FlowLayout.LEFT, 60, 200));
        this.jpInicial.setPreferredSize(new Dimension(400, 500));
        this.jpInicial.setBackground(Color.red);
        this.jpInicial.setOpaque(false);
        this.add(this.jpInicial);
        
        configurarElementos();
        this.jpInicial.add(this.bntGame);
        this.jpInicial.add(this.bntHistor);
        
        this.bntGame.addActionListener(this);
        this.bntHistor.addActionListener(this);
        
    }
    
    private void configurarElementos() {
        game = new ImageIcon(getClass().getResource("/imagens/play.jpg"));
        historia = new ImageIcon(getClass().getResource("/imagens/historico.jpg"));
        bntGame = new JButton(game);
        bntHistor = new JButton(historia);
        
        this.bntGame.setPreferredSize(new Dimension(120, 50));
        this.bntGame.setBackground(Color.black);
        this.bntGame.setBorder(null);
        
        this.bntHistor.setPreferredSize(new Dimension(80, 70));
        this.bntHistor.setBackground(Color.black);
        this.bntHistor.setBorder(null);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.bntGame) {
            if (this.pausado != 1) {
                this.dispose();
                new TelaJogo();
            } else {;
                Object[] options = {"SIM", "NÃO"};
                int escolha = JOptionPane.showOptionDialog(null, "Finalizar jogo pausado ? ", "RETOMAR", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (escolha == 0) {
                    TelaInicial.this.dispose();
                    this.pause.setVisible(true);

                } else {
                    this.pause.dispose();
                    TelaInicial.this.dispose();
                    new TelaJogo();
                }
                
            }
        } else {
            this.dispose();
            new TelaHistorico();
        }
    }
    
}
