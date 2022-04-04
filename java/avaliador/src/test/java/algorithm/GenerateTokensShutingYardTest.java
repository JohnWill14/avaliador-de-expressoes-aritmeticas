package algorithm;

import br.com.paradigma.algorithm.Tokens;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


@DisplayName("Test method tokens from Shunting-yard algorithm")
public class GenerateTokensShutingYardTest {
    private Tokens tokensUtil;

    @BeforeEach
    public void init(){
        tokensUtil = new Tokens();
    }

    @Test
    @DisplayName("Test function tokens")
    void testtokens(){
        Tokens tokenUtil = new Tokens();

        List<String> tokens1 = tokenUtil.generateTokens("31 * (4 + 10)");
        List<String> tokens2 = tokenUtil.generateTokens("1 + 3");
        List<String> tokens3 = tokenUtil.generateTokens("1 + 2 + 3 * 4");
        List<String> tokens4 = tokenUtil.generateTokens("1 + -2 + 3 * 4");
        List<String> tokens5 = tokenUtil.generateTokens("4 / 2 + 7");

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
