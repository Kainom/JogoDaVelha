/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author User
 */
public class TelaJogo extends javax.swing.JFrame {

    private javax.swing.JPanel jpInicial, jpVolta;
    private JLabel lblPlayer1, lblPLayer2, lblJogar1, lblJogar2;
    private JTextField txtNome1, txtNome2;
    private JButton bntContinuar, bntVoltar;
    private ImageIcon sinaliza, sinaliza2, play, volta;
    private Timer timer;

    TelaJogo() {
        configurarJanela();
        configurarPanel();
    }

    private void configurarJanela() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setTitle("BEM VINDO AO GAME");
        this.setSize(1000, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0, 0, 0));
        this.setVisible(true);

    }

    private void configurarPanel() {
        jpInicial = new javax.swing.JPanel();
        jpVolta = new javax.swing.JPanel();

        this.jpInicial.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 100));
        this.jpInicial.setPreferredSize(new java.awt.Dimension(400, 500));
        this.setBackground(Color.white);
        this.jpInicial.setOpaque(false);

        this.jpVolta.setLayout(null);
        this.jpVolta.setPreferredSize(new java.awt.Dimension(280, 500));
        this.jpVolta.setBackground(Color.red);
        this.jpVolta.setOpaque(false);

        configurarElementos();
        this.add(this.jpVolta);
        this.add(this.jpInicial);
        this.jpInicial.add(this.lblPlayer1);
        this.jpInicial.add(this.txtNome1);
        this.jpInicial.add(this.lblPLayer2);
        this.jpInicial.add(this.txtNome2);
        this.jpInicial.add(this.lblJogar1);
        this.jpInicial.add(this.bntContinuar);
        this.jpInicial.add(this.lblJogar2);
        this.jpVolta.add(this.bntVoltar);

        this.bntContinuar.addActionListener(new java.awt.event.ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                String aviso = "";
                int teste = 0;

                java.awt.event.ActionListener tempo = new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        txtNome1.setText("");
                        txtNome2.setText("");
                        timer.stop();

                    }
                };
                timer = new Timer(500, tempo);
                timer.setRepeats(false);

                if (txtNome1.getText().equals("") || txtNome2.getText().equals("")) {
                    timer.start();
                    txtNome1.setText(aviso = (txtNome1.getText().equals("")) ? "VAZIO" : txtNome1.getText());
                    txtNome2.setText(aviso = (txtNome2.getText().equals("")) ? "VAZIO" : txtNome2.getText());
                    teste++;
                } else if (txtNome1.getText().equals(txtNome2.getText())) {
                    timer.start();
                    txtNome1.setText("DIFERENCIE");
                    txtNome2.setText("DIFERENCIE");
                    teste++;

                } else if (txtNome1.getText().length() < 3 || txtNome2.getText().length() < 3) {
                    timer.start();
                    txtNome1.setText(aviso = (txtNome1.getText().length() < 3) ? "MUITO CURTO" : txtNome1.getText());
                    txtNome2.setText(aviso = (txtNome2.getText().length() < 3) ? "MUITO CURTO" : txtNome2.getText());
                    teste++;
                } else if (txtNome1.getText().length() > 7 || txtNome2.getText().length() > 7) {
                    timer.start();
                    txtNome1.setText(aviso = (txtNome1.getText().length() > 7) ? "MUITO LONGO" : txtNome1.getText());
                    txtNome2.setText(aviso = (txtNome2.getText().length() > 7) ? "MUITO LONGO" : txtNome2.getText());
                    teste++;
                } else if (Partidas.testar(txtNome1.getText()) == 1 || Partidas.testar(txtNome2.getText()) == 1) {
                    System.out.println("EXISTE");
                    timer.start();
                    txtNome1.setText(aviso = Partidas.testar(txtNome1.getText()) == 1 ? "JOGADOR JA EXISTENTE" : txtNome1.getText());
                    txtNome2.setText(aviso = Partidas.testar(txtNome2.getText()) == 1 ? "JOGADOR JA EXISTENTE" : txtNome2.getText());

                    teste++;
                }
                if (teste == 0) {
                    new JogoVelha(txtNome1.getText(), txtNome2.getText());
                    TelaJogo.this.dispose();
                } else {

                }

            }

        });
        
        this.bntVoltar.addActionListener(new ActionListener(){
           @Override
           public void  actionPerformed(ActionEvent e){
                    TelaJogo.this.dispose();
                    new TelaInicial().setVisible(true);
            }
        });

    }

    private void configurarElementos() {
        lblPlayer1 = new JLabel("Player 1");
        lblPLayer2 = new JLabel("Player 2");
        txtNome1 = new JTextField();
        txtNome2 = new JTextField();
        sinaliza = new ImageIcon(getClass().getResource("/imagens/sinaliza.jpg"));
        sinaliza2 = new ImageIcon(getClass().getResource("/imagens/sinaliza2.jpg"));
        play = new ImageIcon(getClass().getResource("/imagens/play.jpg"));
        volta = new ImageIcon(getClass().getResource("/imagens/volta.png"));
        bntContinuar = new JButton(play);
        bntVoltar = new JButton(volta);
        lblJogar1 = new JLabel(sinaliza);
        lblJogar2 = new JLabel(sinaliza2);

        this.lblPlayer1.setForeground(Color.red);
        this.lblPlayer1.setFont(new Font("Arial Black", Font.BOLD, 14));

        this.txtNome1.setFont(new Font("Arial", Font.PLAIN, 14));
        this.txtNome1.setBackground(Color.black);
        this.txtNome1.setForeground(Color.cyan);
        this.txtNome1.setPreferredSize(new java.awt.Dimension(200, 30));

        this.lblPLayer2.setForeground(Color.red);
        this.lblPLayer2.setFont(new Font("Arial Black", Font.BOLD, 14));

        this.txtNome2.setFont(new Font("Arial", Font.PLAIN, 14));
        this.txtNome2.setBackground(Color.black);
        this.txtNome2.setForeground(Color.cyan);
        this.txtNome2.setPreferredSize(new java.awt.Dimension(200, 30));

        this.bntContinuar.setPreferredSize(new java.awt.Dimension(120, 50));
        this.bntContinuar.setBackground(Color.cyan);
        this.bntContinuar.setBorder(null);

        this.bntVoltar.setBounds(0, 387, 70, 70);
        this.bntVoltar.setBackground(new Color(0, 0, 0));
        this.bntVoltar.setBorder(null);

    }

}
