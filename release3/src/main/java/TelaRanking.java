import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

/**
 * Tela que gerencia o ranking de melhores jogadores.
 * Armazena e exibe os 10 melhores resultados em arquivo CSV.
 * Adiciona um novo jogador ao ranking e mantém apenas os 10 melhores.
 */

public class TelaRanking extends JPanel {
    private TelaInicial telaInicial;
    //NÃO SOBE AGORA

    // salva o dado do jogador no ranking.csv mantendo só o top 10
    public static void dadosJogador(String nome, int score, int tentativas){
        try {
            List<String[]> registros = new ArrayList<>();

            File arquivo = new File("ranking.csv");
            if (arquivo.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                    String linha;
                    while ((linha = br.readLine()) != null) {
                        String[] parts = linha.split(",");
                        if (parts.length == 3) {
                            registros.add(parts);
                        }
                    }
                }
            }

            registros.add(new String[]{nome, String.valueOf(score), String.valueOf(tentativas)});

            // ordenar: pontuação decrescente, tentativas crescente
            registros.sort((a, b) -> {
                int scoreA = Integer.parseInt(a[1]);
                int scoreB = Integer.parseInt(b[1]);
                if (scoreB != scoreA) {
                    return Integer.compare(scoreB, scoreA); // Ordena por score decrescente
                } else {
                    int tentA = Integer.parseInt(a[2]);
                    int tentB = Integer.parseInt(b[2]);
                    return Integer.compare(tentA, tentB); // Em caso de empate, ordena por tentativas crescentes
                }
            });

            // salvar só top 10
            try (FileWriter fw = new FileWriter(arquivo, false)) {
                int max = Math.min(10, registros.size());
                for (int i = 0; i < max; i++) {
                    String[] r = registros.get(i);
                    fw.write(r[0] + "," + r[1] + "," + r[2] + "\n");
                }
                fw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // construtor que monta o painel e mostra o ranking formatado
    public TelaRanking(TelaInicial telaInicial){
        this.telaInicial = telaInicial;
        setLayout(null);

        JLabel msg = new JLabel("RANKING DOS JOGADORES");
        msg.setBounds(110,5, 225,40);
        add(msg);

        JTextArea areaRanking = new JTextArea();
        areaRanking.setEditable(false);
        areaRanking.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12)); // fonte monoespaçada pra alinhar
        JScrollPane scrol = new JScrollPane(areaRanking);
        scrol.setBounds(20,40,350,260);
        add(scrol);

        File arquivo = new File("ranking.csv");
        if (arquivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%-4s %-15s %-10s %-10s\n", "Nº", "NOME", "SCORE", "TENTATIVAS"));
                sb.append("=================================================\n");

                String linha;
                int contador = 1;
                while ((linha = br.readLine()) != null && contador <= 10) {
                    String[] parts = linha.split(",");
                    if (parts.length == 3) {
                        sb.append(String.format("%-4d %-15s %-10s %-10s\n", contador, parts[0], parts[1], parts[2]));
                        contador++;
                    }
                }
                areaRanking.setText(sb.toString());
            } catch (IOException e) {
                areaRanking.setText("Erro ao carregar o ranking.");
                e.printStackTrace();
            }
        } else {
            areaRanking.setText("Nenhum ranking salvo ainda.");
        }

        JButton voltar = new JButton("VOLTAR");
        voltar.setBounds(120, 320, 150, 40);
        add(voltar);
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaInicial.mostrarPainelInicial();
            }
        });
    }
}
