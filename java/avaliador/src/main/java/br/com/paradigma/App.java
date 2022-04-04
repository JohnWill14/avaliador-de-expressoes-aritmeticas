package br.com.paradigma;

import br.com.paradigma.algorithm.ArithmeticExpressionEvaluator;
import br.com.paradigma.algorithm.ConvertToken;
import br.com.paradigma.algorithm.Tokens;
import br.com.paradigma.util.FastReader;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();
        Tokens tokensUtil = new Tokens();
        ConvertToken convertToken = new ConvertToken();

        while(true){
            System.out.print(">>> ");
            String expression = fr.nextLine();

            if(expression.equals("exit")){
                break;
            }

            List<String> tokens = tokensUtil.generateTokens(expression);
            List<String> parser = convertToken.convertInfixExpressionToPostfix(tokens);

            algorithm.solvePostfixExpression(parser);
        }

    }
}
