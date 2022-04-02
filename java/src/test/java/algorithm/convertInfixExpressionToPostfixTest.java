package algorithm;

import br.com.paradigma.algorithm.ArithmeticExpressionEvaluator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


@DisplayName("Test method parser from Shunting-yard algorithm")
class convertInfixExpressionToPostfixTest {
    @Test
    @DisplayName("Test function parser example")
    void testConvertInfixExpressionToPostfixExample(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = algorithm.generateTokens("5 + 2 / (3 - 8) * 5 - 2");

        List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);



        Assertions.assertThat(parser)
                .isEqualTo(List.of("5", "2", "3", "8", "-", "/", "5", "*", "+", "2", "-"));
    }

    @Test
    @DisplayName("Test function ConvertInfixExpressionToPostfix 1")
    void testConvertInfixExpressionToPostfix1(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = algorithm.generateTokens("31 * 4 + 10");

        List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("31", "4", "*", "10", "+"));
    }

    @Test
    @DisplayName("Test function parser 2")
    void testConvertInfixExpressionToPostfix2(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = algorithm.generateTokens("31 * (4 + 10)");

        List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("31", "4", "10", "+", "*"));
    }

    @Test
    @DisplayName("Test function parser 3")
    void testConvertInfixExpressionToPostfix3(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = algorithm.generateTokens("1 + 3");

        List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "3", "+"));
    }

    @Test
    @DisplayName("Test function parser 4")
    void testConvertInfixExpressionToPostfix4(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = algorithm.generateTokens("1 + 2 * 3");

        List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "2", "3", "*", "+"));
    }

    @Test
    @DisplayName("Test function parser 5")
    void testConvertInfixExpressionToPostfix5(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = algorithm.generateTokens("4 / 2 + 7");

        List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("4", "2", "/", "7", "+"));
    }

    @Test
    @DisplayName("Test function parser 6")
    void testConvertInfixExpressionToPostfix6(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = algorithm.generateTokens("1 + 2 + 3 * 4");

        List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "2", "+", "3", "4", "*", "+"));
    }


    @Test
    @DisplayName("Test function parser 7")
    void testConvertInfixExpressionToPostfix7(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = algorithm.generateTokens("(1 + 2 + 3) * 4");

        List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "2", "+", "3", "+", "4", "*"));
    }


    @Test
    @DisplayName("Test function parser 8")
    void testConvertInfixExpressionToPostfix8(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = algorithm.generateTokens("(10 / 3 + 23) * (1 - 4)");

        List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("10", "3", "/", "23", "+", "1", "4", "-", "*"));
    }

    @Test
    @DisplayName("Test function parser 9")
    void testConvertInfixExpressionToPostfix9(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = algorithm.generateTokens("((1 + 3) * 8 + 1) / 3");

        List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "3", "+", "8", "*", "1", "+", "3", "/"));
    }


    @Test
    @DisplayName("Test function parser 10")
    void testConvertInfixExpressionToPostfix10(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = algorithm.generateTokens("10 * 20 + 3 * 7 + 2 * 3 + 10 / 3 * 4");

        List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("10", "20", "*", "3", "7", "*", "+", "2", "3", "*", "+", "10", "3", "/", "4", "*", "+"));
    }

    @Test
    @DisplayName("Test function parser 11")
    void testConvertInfixExpressionToPostfix11(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = algorithm.generateTokens("1 + -2 * 3");

        List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "-2", "3", "*", "+"));
    }

}