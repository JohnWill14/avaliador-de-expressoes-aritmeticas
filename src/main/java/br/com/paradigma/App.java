package br.com.paradigma;

import br.com.paradigma.algorithm.ArithmeticExpressionEvaluator;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArithmeticExpressionEvaluator algorithm = new ArithmeticExpressionEvaluator();
        while(sc.hasNext()){
            String expression = sc.nextLine();
            List<String> tokens = algorithm.generateTokens(expression);
            List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);
            int value = algorithm.solvePostfixExpression(parser);

            System.out.println(expression +" => "+ value);
        }

    }
}
