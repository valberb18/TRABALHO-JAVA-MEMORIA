import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.FlowLayout;

/**
 * Tela principal do jogo onde ocorre a partida de memória.
 * Gerencia toda a lógica do jogo, incluindo:
 * - Distribuição das cartas
 * - Controle de tentativas
 * - Cálculo de pontuação
 * - Verificação de pares
 */

public class TelaJogo extends JPanel {
    //declarações
    private TelaInicial telaInicial;
    private JButton primeiraCarta = null;
    private JButton segundaCarta = null;
    private int primeiroValor, segundoValor;
    private boolean podeJogar = true;
    private Timer timer;
    int linhas = 0, colunas = 0;
    private int tentativas = 0;
    private int combo = 1;
    private int pontuacao = 0;
    public int totalCartas;
    private int paresFeitos = 0;

    public TelaJogo(final TelaInicial telaInicial, int dificuldade) {
        this.telaInicial = telaInicial;
        this.setLayout(new BorderLayout());
        JButton tenta = new JButton("TENTATIVAS: ");
        JButton score = new JButton("SCORE: ");


        //atribui o tamanho ao tabuleiro
        switch (dificuldade) {
            case 1:
                linhas = 4;
                colunas = 4;
                break;
            case 2:
                linhas = 6;
                colunas = 6;
                break;
            case 3:
                linhas = 8;
                colunas = 8;
                break;
        }
        totalCartas = linhas * colunas;

        // lista de emojis disponiveis
        List<String> emojis = Arrays.asList(
                "🐍", "🐸", "🐙", "🐝", "🐢", "🐬", "🦊", "🐵",
                "🦄", "🐉", "🐮", "🐺", "🐨", "🐹",
                "🐰", "🦁", "🐯", "🐷", "🐔", "🐧", "🐦", "🐴", "🐛", "🦋", "🐞", "🐢", "🐍", "🐬", "🐘"
        );


        // cria os pares dos emojis
        List<Integer> pares = new ArrayList<>();
        for (int i = 0; i < totalCartas / 2; ++i) {
            pares.add(i);
            pares.add(i);
        }

        //embaralha os emojis
        Collections.shuffle(pares);

        //cria o painel das cartas
        JPanel painelCartas = new JPanel(new GridLayout(linhas, colunas, 10, 10));
        painelCartas.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        Map<JButton, Integer> mapaCartas = new HashMap<>();

        for (int valor : pares) {
            JButton carta = new JButton("❓");
            carta.setFont(carta.getFont().deriveFont(60f));
            mapaCartas.put(carta, valor);

            carta.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!podeJogar || carta.getText().trim().matches("\\d+")) return;

                    carta.setText(emojis.get(valor));

                    if (primeiraCarta == null) {
                        primeiraCarta = carta;
                        primeiroValor = valor;
                    } else if (segundaCarta == null && carta != primeiraCarta) {
                        segundaCarta = carta;
                        segundoValor = valor;
                        podeJogar = false;

                        timer = new Timer(1000, new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                if (primeiroValor == segundoValor) {
                                    primeiraCarta.setEnabled(false);
                                    segundaCarta.setEnabled(false);
                                    tentativas++;
                                    tenta.setText("TENTATIVAS: " + tentativas);

                                    if (combo == 1){
                                        pontuacao += 10;
                                        combo++;
                                    } else {
                                        pontuacao += 10 * combo;
                                        combo++;
                                    }
                                    score.setText("SCORE: " + pontuacao);

                                    paresFeitos++;

                                    if (paresFeitos == totalCartas / 2) {
                                        // fim de jogo
                                        JOptionPane.showMessageDialog(null, "Fim de jogo!");
                                        telaInicial.setContentPane(new TelaFimJogo(telaInicial, tentativas, pontuacao));
                                        telaInicial.revalidate();
                                        telaInicial.repaint();
                                    }
                                } else {
                                    primeiraCarta.setText("❓");
                                    segundaCarta.setText("❓");
                                    tentativas++;
                                    combo = 1;
                                    tenta.setText("TENTATIVAS: " + tentativas);
                                }

                                primeiraCarta = null;
                                segundaCarta = null;
                                podeJogar = true;
                                timer.stop();
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            });

            painelCartas.add(carta);
        }

        this.add(painelCartas, BorderLayout.CENTER);


        //configuração do botão reiniciar
        JButton reiniciar = new JButton("REINICIAR");
        reiniciar.setPreferredSize(new Dimension(150, 40));
        reiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent reiniciar) {
                switch (dificuldade){
                    case 1:
                        telaInicial.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        telaInicial.setContentPane(new TelaJogo(telaInicial, 1));
                        telaInicial.revalidate();
                        telaInicial.repaint();
                        break;
                    case 2:
                        telaInicial.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        telaInicial.setContentPane(new TelaJogo(telaInicial, 2));
                        telaInicial.revalidate();
                        telaInicial.repaint();
                        break;
                    case 3:
                        telaInicial.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        telaInicial.setContentPane(new TelaJogo(telaInicial, 3));
                        telaInicial.revalidate();
                        telaInicial.repaint();
                        break;
                }
            }
        });

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        //botao q leva a tela inicial
        JButton voltar = new JButton("VOLTAR");
        voltar.setPreferredSize(new Dimension(150, 40));


        painelBotoes.add(voltar);
        painelBotoes.add(score);
        painelBotoes.add(tenta);
        painelBotoes.add(reiniciar);

        tenta.setPreferredSize(new Dimension(150, 40));
        score.setPreferredSize(new Dimension(150, 40));


        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent voltar) {
                telaInicial.mostrarPainelInicial();
            }
        });
        this.add(painelBotoes, BorderLayout.SOUTH);
    }
}
