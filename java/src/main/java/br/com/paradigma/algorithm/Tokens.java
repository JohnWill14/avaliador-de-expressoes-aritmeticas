package br.com.paradigma.algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokens {
    private String regexTokenSeparator = "\\s?(-?\\d+|[+*\\-\\/()])\\s?";
    public List<String> generateTokens(String express){
        Pattern pattern = Pattern.compile(regexTokenSeparator);
        Matcher matcher = pattern.matcher(express);
        List<String> tokens = new LinkedList<>();

        while(matcher.find()){
            tokens.add(matcher.group().trim());
        }

        return tokens;
    }
}
