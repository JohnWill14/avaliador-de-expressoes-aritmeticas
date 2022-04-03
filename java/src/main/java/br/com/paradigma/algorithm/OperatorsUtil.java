package br.com.paradigma.algorithm;

import java.util.HashMap;
import java.util.Map;

public class OperatorsUtil {

    private static final Map<String, Integer> orderPrecedence;

    static{
        orderPrecedence = new HashMap<>();

        orderPrecedence.put("-", 1);
        orderPrecedence.put("+", 1);

        orderPrecedence.put("*", 2);
        orderPrecedence.put("/", 2);

        orderPrecedence.put("(", -1);
        orderPrecedence.put(")", -1);
    }

    public static int getPrecedence(String operator){
        return orderPrecedence.get(operator);
    }

    public static boolean isTokenAnOperation(String token){
        return token.matches("[+*\\-\\/]");
    }

    public static boolean isTokenAnNumber(String token){
        return token.matches("-?\\d+");
    }
}
