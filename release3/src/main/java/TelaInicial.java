import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe principal que representa a janela do jogo.
 * Gerencia a navegação entre todas as telas do sistema.
 * Constrói a tela principal com menu de opções.
 */
public class TelaInicial extends JFrame {
    private JPanel painelInicial;
    //TUDO FUNCIONANDO PERFEITAMENTE
    public TelaInicial() {
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("JOGO DA MEMÓRIA");

        painelInicial = new JPanel();
        painelInicial.setLayout(null);


        // instancias com configs
        JButton jbtIniciar = new JButton("INICIAR JOGO");
        jbtIniciar.setBounds(100, 100, 200, 40);

        JButton jbtRanking = new JButton("RANKING");
        jbtRanking.setBounds(100, 160, 200, 40);

        JButton jbtCredito = new JButton("CRÉDITO");
        jbtCredito.setBounds(100, 220, 200, 40);


        //ações
        jbtIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent iniciar) {
                setContentPane(new TelaSelc(TelaInicial.this));
                revalidate();
                repaint();
            }
        });

        jbtRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ranking) {
                setContentPane(new TelaRanking(TelaInicial.this));
                revalidate();
                repaint();
            }
        });

        jbtCredito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent credito) {
                setContentPane(new TelaCredito(TelaInicial.this));
                revalidate();
                repaint();

            }
        });

        painelInicial.add(jbtIniciar);
        painelInicial.add(jbtRanking);
        painelInicial.add(jbtCredito);
        add(painelInicial);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void mostrarPainelInicial() {
        setSize(400, 450);
        setLocationRelativeTo(null);
        setContentPane(painelInicial);
        revalidate();
        repaint();
    }
}
