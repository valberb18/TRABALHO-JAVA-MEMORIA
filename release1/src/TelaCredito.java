import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCredito extends JPanel {
    private TelaInicial telaInicial;
    //TUDO FUNCIONANDO PERFEITAMENTE
    public TelaCredito(TelaInicial telaInicial) {
        this.telaInicial = telaInicial;

        setLayout(null);

        JLabel dev = new JLabel("DESENVOLVIDO POR:");
        dev.setBounds(20,5, 125,40);
        add(dev);

        JLabel nome1 = new JLabel("<html> Pedro Rodrigues Barreto <br> RA: 127189611025 </html>");
        nome1.setBounds(20, 60, 250, 40);
        add(nome1);

        JLabel nome2 = new JLabel("<html> Valber Barbosa de Abreu <br> RA: 340198011025 </html>");
        nome2.setBounds(20, 110, 250, 40);
        add(nome2);

        JLabel nome3 = new JLabel("<html> Gabriel de Almeida Ribeiro Vieira <br> RA: 340183911025 </html>");
        nome3.setBounds(20, 160, 250, 40);
        add(nome3);

        JLabel nome4 = new JLabel("<html> Pedro Enrico Oliveira Melo <br> RA: 372649611025 </html>");
        nome4.setBounds(20, 210, 250, 40);
        add(nome4);

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
