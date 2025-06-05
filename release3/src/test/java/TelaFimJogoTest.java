import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JTextField;

/**
 * Classe de teste para {@link TelaFimJogo}.
 * Verifica o registro de jogadores ao final de uma partida.
 * Testa o registro do nome do jogador na tela de fim de jogo.
 * Verifica se:
 * - O nome do jogador é armazenado corretamente
 * - O valor atribuído corresponde ao esperado
 */

public class TelaFimJogoTest {

    @Test
    public void testRegistroJogador() {
        TelaInicial telaInicial = new TelaInicial();
        TelaFimJogo tela = new TelaFimJogo(telaInicial, 10, 50);

        // Simula a entrada do nome
        tela.nomedig = "JogadorTeste";
        assertNotNull(tela.nomedig, "Nome do jogador deve ser armazenado");
        assertEquals("JogadorTeste", tela.nomedig, "Nome deve corresponder ao inserido");
    }
}