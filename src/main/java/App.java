import algorithm.ShuntingYard;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShuntingYard algorithm = new ShuntingYard();
        while(sc.hasNext()){
            String expression = sc.nextLine();
            List<String> tokens = algorithm.generateTokens(expression);
            List<String> parser = algorithm.convertInfixExpressionToPostfix(tokens);
            double value = algorithm.evalStep(parser);

            System.out.println(expression +" => "+ String.format("%.2f", value));
        }

    }
}
