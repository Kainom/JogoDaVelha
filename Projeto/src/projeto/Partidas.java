package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author User
 */
public class Partidas extends JFrame {

    final private String nome;
    private char tabuleiro[][] = new char[3][3];
    private JLabel lblPeças[], lblTabuleiro, lblVence, lblPerde, lblMarca, lblVS, lblCampeao;
    private Fundo jpPartida;
    private ImageIcon icon, xis, bola, black, volta, vitoria;
    private JButton bntVolta;
    private JPanel jpVolta;

    public Partidas(String nome) {
        this.nome = nome;
        configurarJanela();
        configurarPainel();
        configurarTabuleiro();

    }

    public final static int testar(String nome) {
        Path arquivo = Path.of("./files/projeto.txt");
        try {
            List<String> lendo = Files.readAllLines(arquivo);
            for (String linha : lendo) {
                 String[] joga = linha.split(" ");
                 System.out.println(joga[0]);
                 System.out.println(joga[2]);
                if (joga[0].equals(nome) || joga[2].equals(nome)) {
                    return 1; //  retorna 1 se houver registro // 
                }
            }
        } catch (IOException ex) {
            System.out.println("ERRO");
        }
        return 0; // caso nao haja 
    }

     private final  void ler() {
        String peças[] = new String[1], jogadores[] = new String[1];
        String converte, vencedor, perdedor;

        int k = 0;
        Path arquivo = Path.of("./files/projeto.txt");
        try {
            List<String> lendo = Files.readAllLines(arquivo);
            for (String linha : lendo) {
                 String[] joga = linha.split(" ");
                 System.out.println(joga[0]);
                 System.out.println(joga[2]);
                if (joga[0].equals(nome) || joga[2].equals(nome)) {
                    System.out.println(linha);
                    peças = linha.split("-");
                    jogadores = linha.split(" ");
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("ERRO");
        }
//        System.out.println(nome); 
//        System.out.println(jogadores[0]);
//                    System.out.println(jogadores[2]);

        this.lblVence.setText(jogadores[0].toString());
        this.lblPerde.setText(jogadores[2].toString());

        converte = peças[1].toString();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = converte.charAt(k);
                k++;

            }
        }
        System.out.println("");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

            }
        }

    }

    private void configurarJanela() {
        this.setTitle("PARTIDA");
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 80));
        this.setSize(1000, 500);
        this.getContentPane().setBackground(Color.black);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void configurarPainel() {
        configurarElementos();
        jpPartida = new Fundo(icon);
        jpVolta = new JPanel();
        this.jpPartida.setPreferredSize(new Dimension(320, 400));
        this.jpPartida.setLayout(null);
        this.jpPartida.setBackground(Color.red);
        this.jpPartida.setOpaque(false);

        this.jpVolta.setPreferredSize(new Dimension(320, 400));
        this.jpVolta.setBackground(Color.red);
        this.jpVolta.setLayout(null);
        this.jpVolta.setOpaque(false);

        this.add(this.jpVolta);
        this.add(this.jpPartida);

        this.jpVolta.add(this.bntVolta);
        this.jpVolta.add(this.lblCampeao);

        this.jpPartida.add(this.lblVence);
        this.jpPartida.add(this.lblVS);
        this.jpPartida.add(this.lblPerde);

        for (int i = 0; i < 9; i++) {
            this.jpPartida.add(this.lblPeças[i]);
        }

        this.bntVolta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Partidas.this.dispose();
                new TelaInicial().setVisible(true);
            }

        });

    }

    private void configurarElementos() {
        lblPeças = new JLabel[9];
        lblTabuleiro = new JLabel();
        icon = new ImageIcon(getClass().getResource("/imagens/JogoDaVelha.jpg"));
        xis = new ImageIcon(getClass().getResource("/imagens/X.png"));
        bola = new ImageIcon(getClass().getResource("/imagens/Bola2.png"));
        black = new ImageIcon(getClass().getResource("/imagens/blacks.png"));
        volta = new ImageIcon(getClass().getResource("/imagens/volta.png"));
        vitoria = new ImageIcon(getClass().getResource("/imagens/vitoria.png"));
        bntVolta = new JButton(volta);
        lblVence = new JLabel();
        lblPerde = new JLabel();
        lblMarca = new JLabel();
        lblVS = new JLabel("VS");
        lblCampeao = new JLabel(vitoria);

        for (int i = 0; i < 9; i++) {
            lblPeças[i] = new JLabel();
            lblPeças[i].setBackground(Color.red);
            this.lblPeças[i].setIcon(this.xis);

        }
        this.bntVolta.setBounds(0, 310, 70, 70);
        this.bntVolta.setBackground(new Color(0, 0, 0));
        this.bntVolta.setBorder(null);

        this.lblVence.setBounds(35, 330, 83, 30);
        this.lblVence.setForeground(Color.cyan);
        this.lblVence.setFont(new Font("Arial Black", Font.PLAIN, 14));

        this.lblPerde.setBounds(225, 330, 80, 30);
        this.lblPerde.setForeground(Color.cyan);
        this.lblPerde.setFont(new Font("Arial Black", Font.PLAIN, 14));

        this.lblVS.setBounds(150, 330, 30, 30);
        this.lblVS.setFont(new Font("Arial Black", Font.BOLD, 12));
        this.lblVS.setForeground(Color.cyan);

        this.lblCampeao.setBounds(259, 310, 80, 80);
        this.setBackground(new Color(0, 0, 0));

        lblPeças[0].setBounds(15, 12, 50, 80);
        lblPeças[1].setBounds(130, 12, 50, 80);
        lblPeças[2].setBounds(240, 12, 50, 80);
        lblPeças[3].setBounds(15, 125, 50, 74);
        lblPeças[4].setBounds(130, 125, 50, 70);
        lblPeças[5].setBounds(240, 125, 50, 74);
        lblPeças[6].setBounds(15, 230, 50, 77);
        lblPeças[7].setBounds(130, 230, 50, 80);
        lblPeças[8].setBounds(240, 230, 50, 80);

    }

    private void configurarTabuleiro() {
        ler();
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.tabuleiro[i][j] == 'X') {
                    this.lblPeças[k].setIcon(xis);

                } else if (this.tabuleiro[i][j] == 'O') {
                    this.lblPeças[k].setIcon(bola);
                } else if (this.tabuleiro[i][j] == '0') {
                    this.lblPeças[k].setIcon(black);
                }
                k++;
            }
        }

    }

}
