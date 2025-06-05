import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaRanking extends JPanel {
    private TelaInicial telaInicial;
    public TelaRanking(TelaInicial telaInicial){
        this.telaInicial = telaInicial;
        setLayout(null);

        JLabel msg = new JLabel("EM DESENVOLVIMENTO");
        add(msg);
        msg.setBounds(20,5, 225,40);


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