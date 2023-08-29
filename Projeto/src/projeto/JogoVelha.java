/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author User
 */
public class JogoVelha extends JFrame {

    private String nome1, nome2;
    private char[][] tabuleiro = new char[3][3];
    private int jogada, jogador, caso; // JOGADA - atributo que alterna entre as jogadas X e O ||JOGADOR - alterna as peças entre os jogadores ||  CASO sinaliza o jogador pecaVencedora da rodada
    private int velha, pontos1, pontos2;     // VELHA - atributo que conta as 9 jogadas sem pecaVencedora,caracterizando  como velha || PONTOS - pontos do jogador //
    private char pecaVencedora;
    private int x1 = 0, y1 = 0, x2 = 0, y2 = 0;  //atributos referentes as coordenadas da linha que risca a jogada vencedora
    private JPanel jpJogo, jpSinal1, jpSinal2;
    private JLabel lblTabuleiro, lblJogador1, lblJogador2, lblVS, lblSinaliza, lblSinaliza2, lblWinner;
    private ImageIcon icon, bola, xis, inicial, sinaliza1, sinaliza2, volta;
    protected JButton bnt[], bntVolta;
    private JOptionPane joPtion, joVelha;
    private JDialog dialog, dialog2;
    private Timer time;

    {
       
        
        
        
        
        this.pecaVencedora = ' ';
        this.jogada = 0; // JOGADA começa no X
        this.jogador = 1; // jogador 1 começa com o X
        for (int i = 0; i < 3; i++) { // inicializando a matriz com o valor padrao espaço //
            for (int j = 0; j < 3; j++) {
                this.tabuleiro[i][j] = ' ';
            }
        }
    }

    public JogoVelha() {
        configurarJanela();
        configurarPainel();
        UIManager.getDefaults().put("OptionPane.background", new Color(64, 224, 208)); // OptionPane 
        UIManager.put("Panel.background", new Color(64, 224, 208)); // panel 

    }

    public JogoVelha(String nome1, String nome2) {
        this.nome1 = nome1 + ": ";
        this.nome2 = nome2 + ": ";
        configurarJanela();
        configurarPainel();
        UIManager.getDefaults().put("OptionPane.background", new Color(0, 255, 255)); // OptionPane 
        UIManager.put("Panel.background", new Color(0, 255, 255)); // panel 

    }

    public void setJogada(int jogada) {
        this.jogada = jogada;
    }

    public int getJogada() {
        return jogada;
    }

    public void setVelha(int velha) {
        this.velha = velha;
    }

    public int getVelha() {
        return velha;
    }

    public void setVencedor(char vencedor) {
        this.pecaVencedora = vencedor;
    }

    public char getVencedor() {
        return pecaVencedora;
    }

    private void configurarJanela() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 80));
        this.setSize(1000, 500);
        this.setTitle("JOGO DA VELHA");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.getContentPane().setBackground(new Color(0, 0, 0));
        this.setVisible(true);

    }

    private void configurarPainel() {
        jpJogo = new JPanel();
        jpSinal1 = new JPanel();
        jpSinal2 = new JPanel();

        this.jpSinal1.setLayout(null);
        this.jpSinal1.setPreferredSize(new Dimension(240, 400)); // painel que contém o lbl que sinaliza o pecaVencedora //

        this.jpSinal2.setLayout(null);
        this.jpSinal2.setPreferredSize(new Dimension(240, 400)); // painel que contém o lbl que sinaliza o pecaVencedora //

        this.jpJogo.setLayout(null);
        this.jpJogo.setBackground(Color.red);
        this.jpJogo.setPreferredSize(new Dimension(500, 400));

        this.jpJogo.setOpaque(false);
        this.jpSinal1.setOpaque(false);
        this.jpSinal2.setOpaque(false);

        configurarElementos();

        this.add(this.jpSinal1);
        this.add(this.jpJogo);
        this.add(this.jpSinal2);

        this.jpJogo.add(this.lblTabuleiro);
        this.jpJogo.add(this.lblJogador1);
        this.jpJogo.add(this.lblJogador2);
        this.jpJogo.add(this.lblVS);
        this.jpJogo.add(this.lblWinner);

        this.jpSinal1.add(this.bntVolta);
        this.jpSinal1.add(this.lblSinaliza);
        this.jpSinal2.add(this.lblSinaliza2);

        for (int i = 0; i < this.bnt.length; i++) {
            this.jpJogo.add(this.bnt[i]);
            this.bnt[i].addActionListener(new ActionListener() {   // classe anônima implementando a interface do evento // 
                @Override
                public void actionPerformed(ActionEvent game) {
                    JogoVelha.this.jogo(game);
                }
            });

        }
        this.bntVolta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pontos1 < 2 && pontos2 < 2) {
                    JogoVelha.this.setVisible(false);
                    new TelaInicial(1, JogoVelha.this).setVisible(true);
                }
            }

        });
    }

    private void configurarElementos() {
        icon = new ImageIcon(getClass().getResource("/imagens/JogoDaVelha.jpg"));
        bola = new ImageIcon(getClass().getResource("/imagens/Bola.png"));
        xis = new ImageIcon(getClass().getResource("/imagens/X.png"));
        inicial = new ImageIcon(getClass().getResource("/imagens/blacks.png"));
        sinaliza1 = new ImageIcon(getClass().getResource("/imagens/sinaliza.jpg"));
        sinaliza2 = new ImageIcon(getClass().getResource("/imagens/sinaliza2.jpg"));
        volta = new ImageIcon(getClass().getResource("/imagens/volta.png"));
        bntVolta = new JButton(volta);

        this.nome1 = this.nome1.replace(":", "");
        this.nome2 = this.nome2.replace(":", "");

        bnt = new JButton[9];
        lblTabuleiro = new JLabel(icon);
        lblJogador1 = new JLabel(this.nome1);
        lblJogador2 = new JLabel(this.nome2);
        lblVS = new JLabel(" VS ");
        lblSinaliza = new JLabel(sinaliza1);
        lblSinaliza2 = new JLabel(sinaliza2);
        lblWinner = new JLabel();

        for (int i = 0; i < 9; i++) {  // configura todos os buttons com os valores padrões iniciais 
            bnt[i] = new JButton();
            this.bnt[i].setBackground(Color.black);
            this.bnt[i].setIcon(this.inicial);         // icon preto utilizado para resetar o jogo quando o mesmo foi finalizado //
            this.bnt[i].setBorder(null);              // retira a borda dos bnts que oferecem um contraste negativo com o mesmo 
            this.bnt[i].setContentAreaFilled(false);  // retira a marcação padrão inicial do bnt,pois a mesma oferece um  contraste negativo com o fundo preto

        }

        this.lblTabuleiro.setBounds(50, 10, 400, 300); //Optei por um layout nulo afim de conseguir alocar com mais facilidade os bnts no tabuleiro

        this.lblJogador1.setBounds(10, 300, 100, 100);
        this.lblJogador1.setForeground(Color.cyan);
        this.lblJogador1.setFont(new Font("Arial Black", Font.PLAIN, 14));

        this.lblJogador2.setBounds(420, 300, 100, 100);
        this.lblJogador2.setForeground(Color.cyan);
        this.lblJogador2.setFont(new Font("Arial Black", Font.PLAIN, 14));

        this.lblVS.setBounds(235, 300, 100, 100);
        this.lblVS.setForeground(Color.cyan);
        this.lblVS.setFont(new Font("Arial Black", Font.PLAIN, 14));

        this.lblSinaliza.setBounds(120, 250, 100, 200);
        this.lblSinaliza2.setBounds(20, 250, 100, 200);

        this.lblSinaliza.setVisible(false);
        this.lblSinaliza2.setVisible(false);

        this.lblWinner.setBounds(150, 250, 300, 200);
        this.lblWinner.setFont(new Font("Arial Black", Font.ROMAN_BASELINE, 22));
        this.lblWinner.setForeground(Color.cyan);
        this.lblWinner.setVisible(false);

        this.bntVolta.setBounds(0, 310, 70, 70);
        this.bntVolta.setBackground(new Color(0, 0, 0));
        this.bntVolta.setBorder(null);

        this.bnt[0].setBounds(94, 12, 87, 80);         // configurações da posição dos bnt e do tabuleiro no panel //
        this.bnt[1].setBounds(209, 12, 85, 80);
        this.bnt[2].setBounds(324, 12, 80, 80);
        this.bnt[3].setBounds(96, 124, 85, 74);
        this.bnt[4].setBounds(210, 125, 80, 70);
        this.bnt[5].setBounds(324, 124, 80, 74);
        this.bnt[6].setBounds(96, 229, 85, 77);
        this.bnt[7].setBounds(214, 230, 77, 80);
        this.bnt[8].setBounds(324, 230, 80, 80);

    }

    public void limpa() { // configura o jogo para o padrão inicial // 
        int limpa = 0;
        this.setJogada(0); // jogada inicial x 
        this.setVelha(0);  // velha zerada //
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.bnt[limpa].setIcon(this.inicial); // icon preto padrão 
                this.bnt[limpa].setContentAreaFilled(false); // bnt invisivel padrao //
                this.tabuleiro[i][j] = ' '; // limpa o tabuleiro 
                limpa++;
            }
        }
        this.lblTabuleiro.setIcon(this.inicial);   // seto o tabuleiro como inicial preto  e depois como o tabuleiro normal,isto limpa o resíduo deixado pelas linhas //
        this.lblTabuleiro.setIcon(icon);
    }

    private int verificarJogo(char element) {

        this.setVelha(this.getVelha() + 1);

        if (this.tabuleiro[0][0] == element && this.tabuleiro[0][1] == element && this.tabuleiro[0][2] == element) { // verifica todas as posições do tabuleiro
            this.pecaVencedora = (element == 'X') ? 'X' : 'O';
            x1 = 94;  // coordenadas específicas pas riscar com a linha
            y1 = 50;
            x2 = 400;
            y2 = 50;
        } else if (this.tabuleiro[0][0] == element && this.tabuleiro[1][1] == element && this.tabuleiro[2][2] == element) {
            this.pecaVencedora = (element == 'X') ? 'X' : 'O';
            x1 = 94;
            y1 = 12;
            x2 = 400;
            y2 = 300;
        } else if (this.tabuleiro[0][0] == element && this.tabuleiro[1][0] == element && this.tabuleiro[2][0] == element) {
            this.pecaVencedora = (element == 'X') ? 'X' : 'O';
            x1 = 135;
            y1 = 12;
            x2 = 135;
            y2 = 300;
        } else if (this.tabuleiro[1][0] == element && this.tabuleiro[1][1] == element && this.tabuleiro[1][2] == element) {
            this.pecaVencedora = (element == 'X') ? 'X' : 'O';
            x1 = 96;
            y1 = 160;
            x2 = 400;
            y2 = 160;
        } else if (this.tabuleiro[1][1] == element && this.tabuleiro[0][1] == element && this.tabuleiro[2][1] == element) {
            this.pecaVencedora = (element == 'X') ? 'X' : 'O';
            x1 = 250;
            y1 = 12;
            x2 = 250;
            y2 = 300;
        } else if (this.tabuleiro[1][2] == element && this.tabuleiro[0][2] == element & this.tabuleiro[2][2] == element) {
            this.pecaVencedora = (element == 'X') ? 'X' : 'O';
            x1 = 364;
            y1 = 12;
            x2 = 364;
            y2 = 300;
        } else if (this.tabuleiro[2][0] == element && this.tabuleiro[2][1] == element && this.tabuleiro[2][2] == element) {
            this.pecaVencedora = (element == 'X') ? 'X' : 'O';
            x1 = 96;
            y1 = 265;
            x2 = 400;
            y2 = 265;
        } else if (this.tabuleiro[2][0] == element && this.tabuleiro[1][1] == element && this.tabuleiro[0][2] == element) {
            this.pecaVencedora = (element == 'X') ? 'X' : 'O';
            x1 = 400;
            y1 = 12;
            x2 = 94;
            y2 = 305;
        }

        if (this.pecaVencedora != ' ') { // retorna 1 caso haja um  pecaVencedora //
            joPtion = new JOptionPane("Boa Jogada");
            dialog = new JDialog();

            this.dialog = this.joPtion.createDialog(rootPane, "" + this.pecaVencedora);
            Image img = icon.getImage();
            this.dialog.setIconImage(img);
            this.dialog.setBounds(510, 174, 350, 72);
            Timer tim = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent tempo) {
                    dialog.dispose();
                }

            });
            tim.start();
            this.dialog.setVisible(true);
            tim.stop();
            return 1;
        } else if (this.pecaVencedora == ' ' && this.velha == 9) {
            return 2;
        }
        return 0;

    }

    private void jogo(ActionEvent game) {
        int i = 0;
        int option = 0;
        while (i < 9) {
            this.bnt[i].setContentAreaFilled(true);
            i++;
        }
        if (game.getSource().equals(bnt[0]) && this.tabuleiro[0][0] == 32) { //32 é o espaço em ASCII
            //this.bnt[0].setContentAreaFilled(true); 
            if (this.getJogada() == 0) {
                this.setJogada(1);
                this.bnt[0].setIcon(xis);
                this.tabuleiro[0][0] = 'X';
                option = verificarJogo('X');
            } else {
                this.setJogada(0);
                this.bnt[0].setIcon(bola);
                this.tabuleiro[0][0] = 'O';
                option = verificarJogo('O');

            }
        } else if (game.getSource().equals(bnt[1]) && this.tabuleiro[0][1] == 32) {
            if (this.getJogada() == 0) {
                this.setJogada(1);
                this.bnt[1].setIcon(xis);
                this.tabuleiro[0][1] = 'X';
                option = verificarJogo('X');

            } else {
                this.setJogada(0);
                this.bnt[1].setIcon(bola);
                this.tabuleiro[0][1] = 'O';
                option = verificarJogo('O');

            }
        } else if (game.getSource().equals(bnt[2]) && this.tabuleiro[0][2] == 32) {
            if (this.getJogada() == 0) {
                this.setJogada(1);
                this.bnt[2].setIcon(xis);
                this.tabuleiro[0][2] = 'X';
                option = verificarJogo('X');

            } else {
                this.setJogada(0);
                this.bnt[2].setIcon(bola);
                this.tabuleiro[0][2] = 'O';
                option = verificarJogo('O');

            }
        } else if (game.getSource().equals(bnt[3]) && this.tabuleiro[1][0] == 32) {
            if (this.getJogada() == 0) {
                this.setJogada(1);
                this.bnt[3].setIcon(xis);
                this.tabuleiro[1][0] = 'X';
                option = verificarJogo('X');

            } else {
                this.setJogada(0);
                this.bnt[3].setIcon(bola);
                this.tabuleiro[1][0] = 'O';
                option = verificarJogo('O');

            }
        } else if (game.getSource().equals(bnt[4]) && this.tabuleiro[1][1] == 32) {
            if (this.getJogada() == 0) {
                this.setJogada(1);
                this.bnt[4].setIcon(xis);
                this.tabuleiro[1][1] = 'X';
                option = verificarJogo('X');

            } else {
                this.setJogada(0);
                this.bnt[4].setIcon(bola);
                this.tabuleiro[1][1] = 'O';
                option = verificarJogo('O');

            }

        } else if (game.getSource().equals(bnt[5]) && this.tabuleiro[1][2] == 32) {
            if (this.getJogada() == 0) {
                this.setJogada(1);
                this.bnt[5].setIcon(xis);
                this.tabuleiro[1][2] = 'X';
                option = verificarJogo('X');

            } else {
                this.setJogada(0);
                this.bnt[5].setIcon(bola);
                this.tabuleiro[1][2] = 'O';
                option = verificarJogo('O');

            }
        } else if (game.getSource().equals(bnt[6]) && this.tabuleiro[2][0] == 32) {
            if (this.getJogada() == 0) {
                this.setJogada(1);
                this.bnt[6].setIcon(xis);
                this.tabuleiro[2][0] = 'X';
                option = verificarJogo('X');

            } else {
                this.setJogada(0);
                this.bnt[6].setIcon(bola);
                this.tabuleiro[2][0] = 'O';
                option = verificarJogo('O');

            }
        } else if (game.getSource().equals(bnt[7]) && this.tabuleiro[2][1] == 32) {
            if (this.getJogada() == 0) {
                this.setJogada(1);
                this.bnt[7].setIcon(xis);
                this.tabuleiro[2][1] = 'X';
                option = verificarJogo('X');

            } else {
                this.setJogada(0);
                this.bnt[7].setIcon(bola);;
                this.tabuleiro[2][1] = 'O';
                option = verificarJogo('O');

            }
        } else if (game.getSource().equals(bnt[8]) && this.tabuleiro[2][2] == 32) {
            if (this.getJogada() == 0) {
                this.setJogada(1);
                this.bnt[8].setIcon(xis);
                this.tabuleiro[2][2] = 'X';
                option = verificarJogo('X');

            } else {
                this.setJogada(0);
                this.bnt[8].setIcon(bola);
                this.tabuleiro[2][2] = 'O';
                option = verificarJogo('O');

            }
        }
        if (option == 1) { // pecaVencedora 
            Graphics linha = this.jpJogo.getGraphics();  // cria o objeto linha para desenhar a mesma no panel // 
            linha.setColor(Color.cyan);

            try {
                linha.drawLine(x1, y1, x2, y2);         // desenha a linha nas coordenadas dos simbolos vencedores //
                Thread.sleep(1000);                     // "Dorme" o programa tempo o suficiente para expressar a linha do pecaVencedora // 
            } catch (InterruptedException ex) {
            }

            time = new Timer((int) (1000), new ActionListener() { // Timer que espera 1 segundo  e mostra a seta que indica o pecaVencedora  // 
                @Override
                public void actionPerformed(ActionEvent fecha) {
                    if (pontos1 == 2 || pontos2 == 2) {   // ação que é realizada quando o jogador chega a sua vitoria definitiva // 

                        try {
                            Thread.sleep(700);  // dorme o programa para conceder mais tempo ao jogador afim de visualizar o pecaVencedora // 
                            JogoVelha.this.dispose();     // finaliza o game // 
                            new TelaInicial().setVisible(true); // inicializa a tela inicial // 

                        } catch (InterruptedException ex) {
                        }
                    }

                    if (caso == 1 || caso == 4) {   // caso 1 e 4 são os casos em que o jogador 1 vence a rodada //
                        lblSinaliza.setVisible(false);
                    } else {                        // os unicos possiveis restante são os casos 2 e 3 que não necessitam ser especificados //
                        lblSinaliza2.setVisible(false);

                    }
                    time.stop(); // finaliza a thread da seta  // 
                }
            });

            if (this.pecaVencedora == 'X' && this.jogador == 1) { // if que analisa o simbolo e o jogador para evidenciar o pecaVencedora e trocar os simbolos // 
                this.caso = 1;

                time.start();
                this.lblSinaliza.setVisible(true);

                this.pontos1++;
                this.jogador = 2;
                this.lblJogador1.setText(this.nome1 + this.pontos1); // atualiza os pontos do jogador numero1 quando este vence // 
            } else if (this.pecaVencedora == 'X' && this.jogador == 2) {
                this.caso = 2;

                time.start();
                this.lblSinaliza2.setVisible(true);

                this.pontos2++;
                this.jogador = 1;
                this.lblJogador2.setText(this.nome2 + this.pontos2);
            } else if (this.pecaVencedora == 'O' && this.jogador == 1) {
                this.caso = 3;

                time.start();
                this.lblSinaliza2.setVisible(true);

                this.pontos2++;
                this.jogador = 2;
                this.lblJogador2.setText(this.nome2 + this.pontos2);
            } else if (this.pecaVencedora == 'O' && this.jogador == 2) {
                this.caso = 4;

                time.start();
                this.lblSinaliza.setVisible(true);

                this.pontos1++;
                this.jogador = 1;
                this.lblJogador1.setText(this.nome1 + this.pontos1);
            }

            this.pecaVencedora = ' ';
            if (this.pontos1 != 2 && this.pontos2 != 2) {
                limpa();
            }

        } else if (option == 2) {
            joVelha = new JOptionPane("Deu Velha");
            dialog2 = new JDialog();
            Image img = icon.getImage();

            this.dialog2 = this.joVelha.createDialog(rootPane, "");
            this.dialog2.setIconImage(img);
            this.dialog2.setBounds(510, 174, 350, 72);
            ActionListener action = new ActionListener() { // ação que implementa o joption para mencionar que deu velha // 
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog2.dispose();
                    time.stop();

                }
            };

            time = new Timer(1000, action);

            time.start();
            this.dialog2.setVisible(true);

            limpa();
        }

        if (this.pontos1 == 2 || this.pontos2 == 2) {
            String winner, defeated;
            winner = (this.pontos1 == 2) ? this.nome1 : this.nome2;
            defeated = (this.pontos1 < 2) ? this.nome1 : this.nome2;
            this.lblWinner.setText("VENCEDOR " + winner);
            this.lblWinner.setVisible(true);
            lblSinaliza.setVisible(true);
            lblSinaliza2.setVisible(true);
            this.lblJogador1.setVisible(false);
            this.lblJogador2.setVisible(false);
            this.lblVS.setVisible(false);

            grava(winner, defeated);
            limpa();

        }

    }

    final private void grava(String vencedor, String perdedor) {
        Path arquivo = Path.of("C:/Users/User/Documents/Programação/NetBeansProjects/Projeto/src/files/projeto.txt");
        perdedor += "-";     
        vencedor += " ";
        String recebe = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.tabuleiro[i][j] == ' ') {
                    this.tabuleiro[i][j] = '0';
                }
                recebe += "" + this.tabuleiro[i][j];
            }
        }
        recebe += "\n";
        try {
            
            Files.writeString(arquivo, vencedor, StandardOpenOption.APPEND);
            Files.writeString(arquivo, perdedor, StandardOpenOption.APPEND);
            Files.writeString(arquivo, recebe, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("ERRO");
        }
    }

}
