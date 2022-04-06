package algorithm;

import br.com.paradigma.algorithm.ArithmeticExpressionEvaluator;
import br.com.paradigma.algorithm.ConvertToken;
import br.com.paradigma.algorithm.Tokens;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


@DisplayName("Test method parser from Shunting-yard algorithm")
class ConvertInfixExpressionToPostfixTest {
    private Tokens tokensUtil;
    private ConvertToken convertToken;
    
    @BeforeEach
    public void init(){
        tokensUtil = new Tokens();
        convertToken = new ConvertToken();
    }
    
    @Test
    void testConvertInfixExpressionToPostfixExample(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("5 + 2 / (3 - 8) * 5 - 2");

        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);



        Assertions.assertThat(parser)
                .isEqualTo(List.of("5", "2", "3", "8", "-", "/", "5", "*", "+", "2", "-"));
    }

    @Test
    void testConvertInfixExpressionToPostfix1(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("31 * 4 + 10");

        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("31", "4", "*", "10", "+"));
    }

    @Test
    void testConvertInfixExpressionToPostfix2(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("31 * (4 + 10)");

        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("31", "4", "10", "+", "*"));
    }

    @Test
    void testConvertInfixExpressionToPostfix3(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("1 + 3");

        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "3", "+"));
    }

    @Test
    void testConvertInfixExpressionToPostfix4(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("1 + 2 * 3");

        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "2", "3", "*", "+"));
    }

    @Test
    void testConvertInfixExpressionToPostfix5(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("4 / 2 + 7");

        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("4", "2", "/", "7", "+"));
    }

    @Test
    void testConvertInfixExpressionToPostfix6(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("1 + 2 + 3 * 4");

        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "2", "+", "3", "4", "*", "+"));
    }


    @Test
    void testConvertInfixExpressionToPostfix7(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("(1 + 2 + 3) * 4");

        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "2", "+", "3", "+", "4", "*"));
    }


    @Test
    void testConvertInfixExpressionToPostfix8(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("(10 / 3 + 23) * (1 - 4)");

        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("10", "3", "/", "23", "+", "1", "4", "-", "*"));
    }

    @Test
    void testConvertInfixExpressionToPostfix9(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("((1 + 3) * 8 + 1) / 3");

        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "3", "+", "8", "*", "1", "+", "3", "/"));
    }


    @Test
    void testConvertInfixExpressionToPostfix10(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("10 * 20 + 3 * 7 + 2 * 3 + 10 / 3 * 4");

        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("10", "20", "*", "3", "7", "*", "+", "2", "3", "*", "+", "10", "3", "/", "4", "*", "+"));
    }

    @Test
    void testConvertInfixExpressionToPostfix11(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("1 + -2 * 3");

        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "-2", "3", "*", "+"));
    }

}