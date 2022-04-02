package algorithm;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShuntingYard{
    private static final Map<String, Integer> orderPrecedence;
    private String regexTokenSeparator = "\\s?(-?\\d+|[+*\\-\\/()])\\s?";

    static{
        orderPrecedence = new HashMap<>();

        orderPrecedence.put("-", 1);
        orderPrecedence.put("+", 1);

        orderPrecedence.put("*", 2);
        orderPrecedence.put("/", 2);

        orderPrecedence.put("(", -1);
        orderPrecedence.put(")", -1);
    }

    public List<String> generateTokens(String express){
        Pattern pattern = Pattern.compile(regexTokenSeparator);
        Matcher matcher = pattern.matcher(express);
        List<String> tokens = new LinkedList<>();

        while(matcher.find()){
            tokens.add(matcher.group().trim());
        }

        return tokens;
    }

    public  List<String> convertInfixExpressionToPostfix(List<String> tokens){
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
                        && orderPrecedence.get(token) <= orderPrecedence.get(pilhaOperacao.peek())
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

    public boolean hasLeftAssociation(String operation){
        if(operation.equals("+")
                ||operation.equals("*")
                ||operation.equals("-")
                ||operation.equals("/")
                ||operation.equals("^")){
            return true;
        }
        return false;
    }

    public  double solvePostfixExpression(List<String> tokens){
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

                System.out.println(toString(link));
            }
        }

        return Double.parseDouble(pilha.pop());
    }

    public int solveOperation(int a, int b, char operation){
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

    public  String toString(List<String> tokens){
        StringBuilder sb ;
        Stack<String> stack = convertListOfTokensForStack(tokens);

        sb = visitToken(stack);

        return sb.toString();
    }

    public Stack<String> convertListOfTokensForStack(List<String> tokens){
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

            if(isAddParents(tokenEsq, token)){
                sb = addInStringBuilderAnParenthesesAndExpression(sb, filhoEsquerda);
            }else{
                sb = addInStringBuilderAnExpression(sb, filhoEsquerda);
            }

            sb.append(token);

            if(isAddParents(tokenDir, token)){
                sb = addInStringBuilderAnParenthesesAndExpression(sb, filhoDireita);
            }else {
                sb = addInStringBuilderAnExpression(sb, filhoDireita);
            }

        }else{
            sb = addInStringBuilderAnExpression(sb, token);
        }

        return sb;
    }
    
    public StringBuilder addInStringBuilderAnParenthesesAndExpression(StringBuilder sb, StringBuilder filho){
            sb.append("(");
            sb.append(filho);
            sb.append(")");

        return sb;
    }

    public StringBuilder addInStringBuilderAnExpression(StringBuilder sb, StringBuilder filho){
        sb.append(filho);
        sb.append(" ");

        return sb;
    }

    public StringBuilder addInStringBuilderAnExpression(StringBuilder sb, String filho){
        sb.append(filho);
        sb.append(" ");

        return sb;
    }

    public boolean isAddParents(String tokenFilho, String token){
        return isTokenAnOperation(tokenFilho)&&
                orderPrecedence.get(tokenFilho)< orderPrecedence.get(token);
    }

    public boolean isTokenAnOperation(String token){
        return token.matches("[+*\\-\\/]");
    }

    public boolean isTokenAnNumber(String token){
        return token.matches("-?\\d+");
    }
}
