package br.com.paradigma.algorithm;

import java.util.*;
import static br.com.paradigma.algorithm.OperatorsUtil.*;

public class ConvertToken {
    public List<String> convertInfixExpressionToPostfix(List<String> tokens){
        /* url site para consulta:
         *   - https://brilliant.org/wiki/shunting-yard-algorithm/
         *   - https://www.geeksforgeeks.org/java-program-to-implement-shunting-yard-algorithm/
         */

        Stack<String> pilhaOperacao = new Stack<>();
        Queue<String> filaSaida = new LinkedList<>();

        while(!tokens.isEmpty()){
            String token = tokens.remove(0);

            if(isTokenAnNumber(token)){
                filaSaida.add(token);
            }else if(isTokenAnOperation(token)){
                while (!pilhaOperacao.isEmpty()
                        && getPrecedence(token) <= getPrecedence(pilhaOperacao.peek())
                        &&hasLeftAssociation(token)) {
                    filaSaida.add(pilhaOperacao.pop());
                }
                pilhaOperacao.push(token);

            }else if(token.equals("(")){
                pilhaOperacao.push(token);
            }else if(token.equals(")")){
                while (!pilhaOperacao.isEmpty()) {
                    if(pilhaOperacao.peek().equals("(")){
                        break;
                    }
                    filaSaida.add(pilhaOperacao.pop());
                }
                pilhaOperacao.pop();
            }
        }

        while(!pilhaOperacao.isEmpty()){
            filaSaida.add(pilhaOperacao.pop());
        }

        return new ArrayList<>(filaSaida);
    }

    private boolean hasLeftAssociation(String operation){
        if(operation.equals("+")
                ||operation.equals("*")
                ||operation.equals("-")
                ||operation.equals("/")
                ||operation.equals("^")){
            return true;
        }
        return false;
    }


}

