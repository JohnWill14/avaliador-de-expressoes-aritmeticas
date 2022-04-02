package algorithm;

import br.com.paradigma.algorithm.ShuntingYard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


@DisplayName("Test method tokens from Shunting-yard algorithm")
public class GenerateTokensShutingYardTest {
    @Test
    @DisplayName("Test function tokens")
    void testtokens(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens1 = algorithm.generateTokens("31 * (4 + 10)");
        List<String> tokens2 = algorithm.generateTokens("1 + 3");
        List<String> tokens3 = algorithm.generateTokens("1 + 2 + 3 * 4");
        List<String> tokens4 = algorithm.generateTokens("1 + -2 + 3 * 4");
        List<String> tokens5 = algorithm.generateTokens("4 / 2 + 7");

        Assertions.assertThat(tokens1)
                .isEqualTo(List.of("31", "*", "(", "4", "+", "10", ")"));

        Assertions.assertThat(tokens2)
                .isEqualTo(List.of("1", "+", "3"));

        Assertions.assertThat(tokens3)
                .isEqualTo(List.of("1", "+", "2", "+", "3", "*", "4"));

        Assertions.assertThat(tokens4)
                .isEqualTo(List.of("1", "+", "-2", "+", "3", "*", "4"));

        Assertions.assertThat(tokens5)
                .isEqualTo(List.of("4", "/", "2", "+", "7"));
    }
}
