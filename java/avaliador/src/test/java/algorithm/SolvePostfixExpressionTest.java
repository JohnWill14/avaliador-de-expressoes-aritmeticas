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
    @DisplayName("Test function Lexier")
    void testSolvePostfixExpression(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("31 * (4 + 10)");
        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);
        double value = algorithm.solvePostfixExpression(parser);

        Assertions.assertThat(value)
                .isEqualTo(434d);

    }

    @Test
    @DisplayName("Test function Lexier 1")
    void testSolvePostfixExpression1(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("5 + 2 / (3 - 8) * 5 - 2");
        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);
        double value = algorithm.solvePostfixExpression(parser);

        Assertions.assertThat(value)
                .isEqualTo(3d);

    }

    @Test
    @DisplayName("Test function Lexier 2")
    void testSolvePostfixExpression2(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("1 + 2 * 3");
        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);
        double value = algorithm.solvePostfixExpression(parser);

        Assertions.assertThat(value)
                .isEqualTo(7d);

    }

    @Test
    @DisplayName("Test function Lexier 3")
    void testSolvePostfixExpression3(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("4 / 2 + 7");
        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);
        double value = algorithm.solvePostfixExpression(parser);

        Assertions.assertThat(value)
                .isEqualTo(9d);

    }

    @Test
    @DisplayName("Test function Lexier 4")
    void testSolvePostfixExpression4(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("1 + 2 + 3 * 4");
        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);
        double value = algorithm.solvePostfixExpression(parser);

        Assertions.assertThat(value)
                .isEqualTo(15d);

    }

    @Test
    @DisplayName("Test function Lexier 5")
    void testSolvePostfixExpression5(){
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();

        List<String> tokens = tokensUtil.generateTokens("(1 + 2 + 3) * 4");
        List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);
        double value = algorithm.solvePostfixExpression(parser);

        Assertions.assertThat(value)
                .isEqualTo(24d);

    }
}
