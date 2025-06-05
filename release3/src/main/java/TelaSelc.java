import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tela de seleção de dificuldade do jogo.
 * Oferece três níveis de dificuldade que alteram o tamanho do tabuleiro.
 */

public class TelaSelc extends JPanel{
    private TelaInicial telaInicial;
    public TelaSelc (TelaInicial telaInicial){
        this.telaInicial = telaInicial;
        setLayout(null);

        //IMPLEMENTAÇÃO DOS BOTÕES DE NIVEL DE DIFICULDADE DO JOGO
        JButton facil = new JButton("FÁCIL");
        facil.setBounds(100, 100, 200, 40);
        JButton medio = new JButton("MÉDIO");
        medio.setBounds(100, 160, 200, 40);
        JButton dificil = new JButton("DIFICIL");
        dificil.setBounds(100, 220, 200, 40);

        facil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent facil) {
                telaInicial.setExtendedState(JFrame.MAXIMIZED_BOTH);
                telaInicial.setContentPane(new TelaJogo(telaInicial, 1));
                revalidate();
                repaint();
            }
        });

        medio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent medio) {
                telaInicial.setExtendedState(JFrame.MAXIMIZED_BOTH);
                telaInicial.setContentPane(new TelaJogo(telaInicial, 2));
                revalidate();
                repaint();
            }
        });

        dificil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent dificil) {
                telaInicial.setExtendedState(JFrame.MAXIMIZED_BOTH);
                telaInicial.setContentPane(new TelaJogo(telaInicial, 3));
                revalidate();
                repaint();
            }
        });

        add(facil);
        add(medio);
        add(dificil);

        //AÇÃO DE VOLTAR AO MENU PRINCIPAL
        JButton voltar = new JButton("VOLTAR");
        voltar.setBounds(120, 320, 150, 40);
        add(voltar);
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent voltar) {
                telaInicial.mostrarPainelInicial();
            }
        });



    }
}
