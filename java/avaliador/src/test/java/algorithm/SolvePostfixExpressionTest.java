package algorithm;

import br.com.paradigma.algorithm.ArithmeticExpressionEvaluator;
import br.com.paradigma.algorithm.ConvertToken;
import br.com.paradigma.algorithm.Tokens;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Test method lexier from Shunting-yard algorithm")
public class SolvePostfixExpressionTest {
    private Tokens tokensUtil;
    private ConvertToken convertToken;

    @BeforeEach
    public void init(){
        tokensUtil = new Tokens();
        convertToken = new ConvertToken();
    }

    @Test
    void testSolvePostfixExpression(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokens = tokensUtil.generateTokens("31 * (4 + 10)");
        List<String> parser = parser = parser = convertToken.convertInfixExpressionToPostfix(tokens);
        int value = algorithm.solvePostfixExpression(parser);

        Assertions.assertThat(value)
                .isEqualTo(434);

    }

    @Test
    void testSolvePostfixExpression1(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokens = tokensUtil.generateTokens("5 + 2 / (3 - 8) * 5 - 2");
        List<String> parser = parser = parser = convertToken.convertInfixExpressionToPostfix(tokens);
        int value = algorithm.solvePostfixExpression(parser);

        Assertions.assertThat(value)
                .isEqualTo(3);

    }

    @Test
    void testSolvePostfixExpression2(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokens = tokensUtil.generateTokens("1 + 2 * 3");
        List<String> parser = parser = parser = convertToken.convertInfixExpressionToPostfix(tokens);
        int value = algorithm.solvePostfixExpression(parser);

        Assertions.assertThat(value)
                .isEqualTo(7);

    }

    @Test
    void testSolvePostfixExpression3(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokens = tokensUtil.generateTokens("4 / 2 + 7");
        List<String> parser = parser = parser = convertToken.convertInfixExpressionToPostfix(tokens);
        int value = algorithm.solvePostfixExpression(parser);

        Assertions.assertThat(value)
                .isEqualTo(9);

    }

    @Test
    void testSolvePostfixExpression4(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokens = tokensUtil.generateTokens("1 + 2 + 3 * 4");
        List<String> parser = parser = parser = convertToken.convertInfixExpressionToPostfix(tokens);
        int value = algorithm.solvePostfixExpression(parser);

        Assertions.assertThat(value)
                .isEqualTo(15);

    }

    @Test
    void testSolvePostfixExpression5(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokens = tokensUtil.generateTokens("(1 + 2 + 3) * 4");
        List<String> parser = parser = parser = convertToken.convertInfixExpressionToPostfix(tokens);
        int value = algorithm.solvePostfixExpression(parser);

        Assertions.assertThat(value)
                .isEqualTo(24);

    }

    @Test
    void testConvertExpressPostfixInInfix(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();
        List<String> tokens = null;
        List<String> parser = null;
        String result = null;
        String express = null;

        express = "(1 + 2 + 3) * 4";
        tokens = tokensUtil.generateTokens(express);
        parser = parser = convertToken.convertInfixExpressionToPostfix(tokens);
        result = algorithm.convertExpressPostfixInInfix(parser);

        Assertions.assertThat(result).isEqualTo(express);

        express = "31 * (4 + 10)";
        tokens = tokensUtil.generateTokens(express);
        parser = parser = convertToken.convertInfixExpressionToPostfix(tokens);
        result = algorithm.convertExpressPostfixInInfix(parser);

        Assertions.assertThat(result).isEqualTo(express);

        express = "5 + 2 / (3 - 8) * 5 - 2";
        tokens = tokensUtil.generateTokens(express);
        parser = parser = convertToken.convertInfixExpressionToPostfix(tokens);
        result = algorithm.convertExpressPostfixInInfix(parser);

        Assertions.assertThat(result).isEqualTo(express);

        express = "1 + 2 * 3";
        tokens = tokensUtil.generateTokens(express);
        parser = parser = convertToken.convertInfixExpressionToPostfix(tokens);
        result = algorithm.convertExpressPostfixInInfix(parser);

        Assertions.assertThat(result).isEqualTo(express);

        express = "4 / 2 + 7";
        tokens = tokensUtil.generateTokens(express);
        parser = parser = convertToken.convertInfixExpressionToPostfix(tokens);
        result = algorithm.convertExpressPostfixInInfix(parser);

        Assertions.assertThat(result).isEqualTo(express);

        express = "(1 + 2 + 3) * 4";
        tokens = tokensUtil.generateTokens(express);
        parser = parser = convertToken.convertInfixExpressionToPostfix(tokens);
        result = algorithm.convertExpressPostfixInInfix(parser);

        Assertions.assertThat(result).isEqualTo(express);

    }
}
