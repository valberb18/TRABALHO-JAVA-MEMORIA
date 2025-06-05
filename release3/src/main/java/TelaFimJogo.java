import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Tela exibida ao final de uma partida, mostrando resultados
 * e permitindo ao jogador registrar seu nome no ranking.
 * Constrói a tela de fim de jogo.
 */

public class TelaFimJogo extends JPanel {
    private TelaInicial telaInicial;
    public String nomedig;

    public TelaFimJogo (TelaInicial telaInicial, int tentativas, int pontuacao){

        //configuração da tela
        this.telaInicial = telaInicial;
        setLayout(null);
        telaInicial.setSize(400,500);
        telaInicial.setLocationRelativeTo(null);

        //MENSAGEM DE FIM DE JOGO
        JLabel titulo = new JLabel("FIM DE JOGO!");
        titulo.setBounds(160, 30, 200, 30);
        add(titulo);

        //INSTANCIAS DE BOTÕES E LABELS
        JLabel score = new JLabel("NÚMERO DE TENTATIVAS:  " + tentativas);
        JLabel tenta = new JLabel("SUA PONTUAÇÃO:  " + pontuacao);
        JLabel seuNome = new JLabel();
        JButton voltar = new JButton("VOLTAR");
        JButton novoJogo = new JButton("NOVO JOGO");

        JLabel pedido = new JLabel("DIGITE SEU NOME: ");
        pedido.setBounds(10, 70, 200, 30);
        add(pedido);

        JTextField campoNome = new JTextField(30);
        campoNome.setBounds(115,70,200,30);
        add(campoNome);

        JButton btnok = new JButton("OK");
        btnok.setBounds(325,69,51,30);
        add(btnok);

        btnok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ok) {
                campoNome.setEnabled(false);
                btnok.setEnabled(false);
                nomedig = campoNome.getText();
                seuNome.setText("SEU NOME É: " + nomedig);
                add(seuNome);
                seuNome.setBounds(10,110,200,50);
                add(score);
                score.setBounds(10,140,200,50);
                add(tenta);
                tenta.setBounds(10,170,200,50);
                novoJogo.setEnabled(true);
                voltar.setEnabled(true);
                TelaRanking.dadosJogador(nomedig, pontuacao, tentativas);
            }
        });

        JButton salvar = new JButton("SALVAR");

        //VOLTA AO MENU PRINCIPAL

        voltar.setEnabled(false);
        voltar.setBounds(70, 400, 120, 40);
        add(voltar);
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent voltar) {
                telaInicial.mostrarPainelInicial();
            }
        });

        //VOLTA PARA A TELA DE SELEÇÃO DE DIFICULDADE
        novoJogo.setEnabled(false);
        novoJogo.setBounds(200,400,120,40);
        add(novoJogo);
        novoJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent novoJogo) {
                telaInicial.setContentPane(new TelaSelc(telaInicial));
                telaInicial.revalidate();
                telaInicial.repaint();
            }
        });
    }
}
