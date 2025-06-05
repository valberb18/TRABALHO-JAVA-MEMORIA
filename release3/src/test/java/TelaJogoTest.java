import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;

/**
 * Classe de teste para {@link TelaJogo}.
 * Testa a lógica de distribuição de cartas e configuração de dificuldades.
 */

public class TelaJogoTest {

    /**
     * Testa a distribuição inicial das cartas no modo fácil (4x4).
     * Verifica se:
     * - O número total de cartas corresponde ao esperado (16)
     * - As cartas são criadas corretamente
     * Verifica se cada nível cria o número correto de cartas:
     * - Fácil: 16 cartas (4x4)
     * - Médio: 36 cartas (6x6)
     * - Difícil: 64 cartas (8x8)
     */

    @Test
    public void testDistribuicaoCartas() {

        TelaInicial telaInicial = new TelaInicial();
        TelaJogo telaJogo = new TelaJogo(telaInicial, 1);

        // Verifica se o número de cartas tá correto
        assertEquals(16, telaJogo.totalCartas, "Tabuleiro 4x4 deve ter 16 cartas");


    }

    @Test
    public void testDificuldades() {
        TelaInicial telaInicial = new TelaInicial();

        TelaJogo facil = new TelaJogo(telaInicial, 1);
        assertEquals(16, facil.totalCartas, "Fácil deve ser 4x4 (16 cartas)");

        TelaJogo medio = new TelaJogo(telaInicial, 2);
        assertEquals(36, medio.totalCartas, "Médio deve ser 6x6 (36 cartas)");

        TelaJogo dificil = new TelaJogo(telaInicial, 3);
        assertEquals(64, dificil.totalCartas, "Difícil deve ser 8x8 (64 cartas)");
    }


}