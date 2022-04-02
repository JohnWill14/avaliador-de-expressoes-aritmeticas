package algorithm;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShuntingYard{
    private static final Map<String, Integer> operatorPrecedure;
    private String regexTokenSeparator = "\\s?(-?\\d+|[+*\\-\\/()])\\s?";

    static{
        operatorPrecedure = new HashMap<>();

        operatorPrecedure.put("-", 1);
        operatorPrecedure.put("+", 1);

        operatorPrecedure.put("*", 2);
        operatorPrecedure.put("/", 2);

        operatorPrecedure.put("(", -1);
        operatorPrecedure.put(")", -1);
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
        // url site: https://brilliant.org/wiki/shunting-yard-algorithm/
        Stack<String> pilhaOperacao = new Stack<>();
        Queue<String> filaSaida = new LinkedList<>();

        while(!tokens.isEmpty()){
            String token = tokens.remove(0);

            if(isTokenAnNumber(token)){
                filaSaida.add(token);
            }else if(isTokenAnOperation(token)){
                while (!pilhaOperacao.isEmpty()
                        &&operatorPrecedure.get(token) <= operatorPrecedure.get(pilhaOperacao.peek())
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

    public  double evalStep(List<String> tokens){
        Stack<String> pilha = new Stack<>();
        int cont = 0;

        double resp = 0;

        while(cont< tokens.size()){
            String token = tokens.get(cont);
            cont++;

            if (isTokenAnNumber(token)){
                pilha.push(token);
            }else{
                int valor1 = Integer.parseInt(pilha.pop());
                int valor2 = Integer.parseInt(pilha.pop());
                char operation = token.charAt(0);

                int result = resolveOperation(valor2, valor1, operation);

                pilha.push(Integer.toString(result));
                List link = new LinkedList<String>();
                link.addAll(pilha.subList(0, pilha.size()));
                link.addAll(tokens.subList(cont, tokens.size()));

                System.out.println(toString(link));
            }
        }

        return Double.parseDouble(pilha.pop());
    }

    public int resolveOperation(int a, int b, char operation){
        int result = 0;

        switch (operation){
            case '+':
                result = a+b;
                break;
            case '-':
                result = a-b;
                break;
            case '*':
                result = a*b;
                break;
            case '/':
                result = a/b;
                break;
        }

        return result;
    }

    public  String toString(List<String> tokens){
        StringBuffer sb ;
        Stack<String> stack = new Stack<>();

        stack.addAll(tokens);

        sb = visitToken( stack);

        return sb.toString();
    }

    private StringBuffer visitToken(Stack<String> tokens){
        if(tokens.empty())
            return new StringBuffer();

        StringBuffer sb = new StringBuffer();
        String token = tokens.pop();

        if(token.matches("[+*\\-\\/]")) {
            String tokenDir = tokens.peek();
            StringBuffer filhoDireita = visitToken(tokens);
            String tokenEsq = tokens.peek();
            StringBuffer filhoEsquerda = visitToken(tokens);

            if(isTokenAnOperation(tokenEsq)
                    &&operatorPrecedure.get(tokenEsq)<operatorPrecedure.get(token)){
                sb.append("(");
                sb.append(filhoEsquerda);
                sb.append(")");
            }else{
                sb.append(filhoEsquerda);
                sb.append(" ");
            }

            sb.append(token);

            if(isTokenAnOperation(tokenDir)&&
                    operatorPrecedure.get(tokenDir)<operatorPrecedure.get(token)){
                sb.append(" (");
                sb.append(filhoDireita);
                sb.append(")");
            }else {
                sb.append(" ");
                sb.append(filhoDireita);
            }

        }else{
            sb.append(token);
            sb.append(" ");
        }

        return sb;
    }

    public boolean isTokenAnOperation(String token){
        return token.matches("[+*\\-\\/]");
    }

    public boolean isTokenAnNumber(String token){
        return token.matches("-?\\d+");
    }
}
