import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Classe de teste para {@link TelaRanking}.
 * Testa a persistência e ordenação dos dados no arquivo de ranking.
 */

public class TelaRankingTest {

    @Test
    public void testDadosJogadorNovoArquivo() throws IOException {
        Files.deleteIfExists(Paths.get("ranking.csv"));
        TelaRanking.dadosJogador("Jogador1", 100, 5);
        assertTrue(Files.exists(Paths.get("ranking.csv")), "Arquivo de ranking deve ser criado");
    }

    @Test
    public void testDadosJogadorOrdenacao() throws IOException {
        Files.deleteIfExists(Paths.get("ranking.csv"));

        TelaRanking.dadosJogador("Jogador3", 50, 10);
        TelaRanking.dadosJogador("Jogador1", 100, 5);
        TelaRanking.dadosJogador("Jogador2", 75, 8);

        List<String> linhas = Files.readAllLines(Paths.get("ranking.csv"));
        assertEquals(3, linhas.size());
        assertTrue(linhas.get(0).startsWith("Jogador1,100,5"));
        assertTrue(linhas.get(1).startsWith("Jogador2,75,8"));
        assertTrue(linhas.get(2).startsWith("Jogador3,50,10"));
    }

    @Test
    public void testDadosJogadorLimiteTop10() throws IOException {
        Files.deleteIfExists(Paths.get("ranking.csv"));

        for (int i = 1; i <= 15; i++) {
            TelaRanking.dadosJogador("Jogador" + i, 100 - i, 5 + i);
        }

        List<String> linhas = Files.readAllLines(Paths.get("ranking.csv"));
        assertEquals(10, linhas.size());
        assertTrue(linhas.get(0).startsWith("Jogador1,99,6"));
        assertTrue(linhas.get(9).startsWith("Jogador10,90,15"));
    }

    @Test
    public void testDadosJogadorEmpate() throws IOException {
        Files.deleteIfExists(Paths.get("ranking.csv"));

        TelaRanking.dadosJogador("Jogador1", 100, 10);
        TelaRanking.dadosJogador("Jogador2", 100, 5);
        TelaRanking.dadosJogador("Jogador3", 100, 8);

        List<String> linhas = Files.readAllLines(Paths.get("ranking.csv"));
        assertTrue(linhas.get(0).startsWith("Jogador2,100,5"));
        assertTrue(linhas.get(1).startsWith("Jogador3,100,8"));
        assertTrue(linhas.get(2).startsWith("Jogador1,100,10"));
    }
}