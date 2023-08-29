/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author User
 */
public class TelaHistorico extends javax.swing.JFrame {

    private JTextField txtNome;
    private JButton bntHistoric, bntVolta;
    private JPanel jpHistoric, jpVolta;
    private JLabel lblSeta, lblSeta2, lblPlayer;
    private ImageIcon seta, seta2, historico, volta;
    private Timer tempo;

    public TelaHistorico() {
        configurarJanela();
        configurarPanel();
    }

    private void configurarJanela() {
        this.setSize(1000, 500);
        this.setTitle("HISTÓRICO");
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.getContentPane().setBackground(new Color(0, 0, 0));
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void configurarPanel() {
        jpHistoric = new JPanel();
        jpVolta = new JPanel();

        this.jpHistoric.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 130));
        this.jpHistoric.setPreferredSize(new Dimension(400, 500));
        this.jpHistoric.setBackground(Color.red);
        this.jpHistoric.setOpaque(false);

        this.jpVolta.setLayout(null);
        this.jpVolta.setPreferredSize(new Dimension(280, 500));
        this.jpVolta.setBackground(Color.red);
        this.jpVolta.setOpaque(false);

        this.add(this.jpVolta);
        this.add(this.jpHistoric);

        configurarElementos();
        this.jpVolta.add(this.bntVolta);
        this.jpHistoric.add(this.lblPlayer);
        this.jpHistoric.add(this.txtNome);
        this.jpHistoric.add(this.lblSeta);
        this.jpHistoric.add(this.bntHistoric);
        this.jpHistoric.add(this.lblSeta2);

        this.bntHistoric.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent hitorico) {
                ActionListener apaga = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent deleta) {
                        txtNome.setText("");
                        tempo.stop();
                    }

                };
                tempo = new Timer(500, apaga);
                tempo.setRepeats(false);

                if (Partidas.testar(txtNome.getText()) == 1 && !txtNome.getText().equals("")) {
                    int existe = Partidas.testar(txtNome.getText());
                    if (existe == 1) {
                        TelaHistorico.this.dispose();
                        new Partidas(txtNome.getText());
                    } else {
                        System.out.println("Não Há");
                    }
                } else {
                    tempo.start();
                    txtNome.setText("PLAYER INEXISTENTE");
                }
            }

        });

        this.bntVolta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent volta) {
                TelaHistorico.this.dispose();
                new TelaInicial().setVisible(true);
            }

        });

    }

    private void configurarElementos() {
        txtNome = new JTextField();
        seta = new ImageIcon(getClass().getResource("/imagens/sinaliza.jpg"));
        seta2 = new ImageIcon(getClass().getResource("/imagens/sinaliza2.jpg"));
        historico = new ImageIcon(getClass().getResource("/imagens/historico.jpg"));
        volta = new ImageIcon(getClass().getResource("/imagens/volta.png"));
        lblSeta = new JLabel(seta);
        lblSeta2 = new JLabel(seta2);
        bntHistoric = new JButton(historico);
        lblPlayer = new JLabel("PLAYER:");
        bntVolta = new JButton(volta);

        this.txtNome.setPreferredSize(new Dimension(200, 30));
        this.txtNome.setBackground(new Color(0, 0, 0));
        this.txtNome.setForeground(Color.cyan);
        this.txtNome.setFont(new Font("Arial Black", Font.PLAIN, 14));

        this.lblPlayer.setFont(new Font("Arial Black", Font.BOLD, 14));
        this.lblPlayer.setForeground(new Color(200, 0, 0));

        this.bntHistoric.setPreferredSize(new Dimension(80, 70));
        this.bntHistoric.setBackground(new Color(0, 0, 0));
        this.bntHistoric.setBorder(null);

        this.bntVolta.setBounds(0, 387, 70, 70);
        this.bntVolta.setBackground(new Color(0, 0, 0));
        this.bntVolta.setBorder(null);

    }

}
