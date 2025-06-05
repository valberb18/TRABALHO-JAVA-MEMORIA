    import javax.swing.*;
    import java.awt.BorderLayout;
    import java.awt.Dimension;
    import java.awt.GridLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.List;
    import java.util.ArrayList;
    import java.util.Collections;

    public class TelaJogo extends JPanel {
        private TelaInicial telaInicial;

        public TelaJogo(TelaInicial telaInicial, int dificuldade) {
            this.telaInicial = telaInicial;


            setLayout(new BorderLayout());

            int linhas = 0;
            int colunas = 0;

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
                default:
                    linhas = 4;
                    colunas = 4;
                    break;
            }

            int totalCartas = linhas * colunas;
            List<Integer> pares = new ArrayList<>();

            for (int i = 0; i < totalCartas / 2; i++) {
                pares.add(i);
                pares.add(i);
            }
            Collections.shuffle(pares);

            // painel central onde vão ficar os botões
            JPanel painelCartas = new JPanel();
            painelCartas.setLayout(new GridLayout(linhas, colunas, 10, 10)); // espaçamento entre botões
            painelCartas.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // margem

            for (int valor : pares) {
                JButton carta = new JButton(" ??? ");
                painelCartas.add(carta);
            }

            add(painelCartas, BorderLayout.CENTER);


            JButton voltar = new JButton("VOLTAR");
            voltar.setPreferredSize(new Dimension(150, 40));
            JPanel painelInferior = new JPanel();
            painelInferior.add(voltar);
            add(painelInferior, BorderLayout.SOUTH);

            voltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent voltar) {
                    telaInicial.mostrarPainelInicial();
                }
            });
        }
    }
