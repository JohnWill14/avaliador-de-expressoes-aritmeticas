package br.com.paradigma.algorithm;

import java.util.*;

import static br.com.paradigma.algorithm.OperatorsUtil.*;
import static br.com.paradigma.algorithm.OperatorsUtil.getPrecedence;

public class ArithmeticExpressionEvaluator {
    public  int solvePostfixExpression(List<String> tokens){
        Stack<String> pilha = new Stack<>();
        int cont = 0;
        double resp = 0;

        while(cont< tokens.size()){
            String token = tokens.get(cont);
            cont++;

            if (isTokenAnNumber(token)){
                pilha.push(token);
            }else{
                int valorDireita = Integer.parseInt(pilha.pop());
                int valorEsquerda = Integer.parseInt(pilha.pop());
                char operacao = token.charAt(0);

                int result = solveOperation(valorEsquerda, valorDireita, operacao);

                pilha.push(Integer.toString(result));

                List<String> link = new LinkedList<>();
                link.addAll(pilha.subList(0, pilha.size()));
                link.addAll(tokens.subList(cont, tokens.size()));

                System.out.println(convertExpressPostfixInInfix(link));
            }
        }

        return Integer.parseInt(pilha.pop());
    }

    private int solveOperation(int a, int b, char operation){
        int ans = 0;

        switch (operation){
            case '+':
                ans = a+b;
                break;
            case '-':
                ans = a-b;
                break;
            case '*':
                ans = a*b;
                break;
            case '/':
                ans = a/b;
                break;
        }

        return ans;
    }

    public  String convertExpressPostfixInInfix(List<String> tokens){
        StringBuilder sb ;
        Stack<String> stack = convertListOfTokensForStack(tokens);

        sb = visitToken(stack);

        return sb.toString();
    }

    private Stack<String> convertListOfTokensForStack(List<String> tokens){
        Stack<String> stack = new Stack<>();
        stack.addAll(tokens);
        return stack;
    }

    private StringBuilder visitToken(Stack<String> tokens){
        StringBuilder sb = new StringBuilder();
        String token = tokens.pop();

        if(isTokenAnOperation(token)) {
            String tokenDir = tokens.peek();
            StringBuilder filhoDireita = visitToken(tokens);
            
            String tokenEsq = tokens.peek();
            StringBuilder filhoEsquerda = visitToken(tokens);

            if(isTokenAnOperation(tokenEsq)
                    &&getPrecedence(tokenEsq)<getPrecedence(token)){
                sb.append("(");
                sb.append(filhoEsquerda);
                sb.append(")");
            }else{
                sb.append(filhoEsquerda);
            }

            sb.append(" ");
            sb.append(token);
            sb.append(" ");

            if(isTokenAnOperation(tokenDir)&&
                    getPrecedence(tokenDir)<getPrecedence(token)){
                sb.append("(");
                sb.append(filhoDireita);
                sb.append(")");
            }else {
                sb.append(filhoDireita);
            }
        }else{
            sb.append(token);
        }

        return sb;
    }
}
