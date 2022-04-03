package br.com.paradigma;

import br.com.paradigma.algorithm.ArithmeticExpressionEvaluator;
import br.com.paradigma.algorithm.ConvertToken;
import br.com.paradigma.algorithm.Tokens;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();
        Tokens tokensUtil = new Tokens();
        ConvertToken convertToken = new ConvertToken();

        while(sc.hasNext()){
            System.out.print(">>> ");
            String expression = sc.nextLine();
            List<String> tokens = tokensUtil.generateTokens(expression);
            List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);
            int value = algorithm.solvePostfixExpression(parser);
        }

    }
}
