import java.util.List;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShuntingYard<Object> algorithm = new ShuntingYard<>();
        while(sc.hasNext()){
            String expression = sc.nextLine();
            List<String> tokens = algorithm.lexier(expression);
            List<String> parser = algorithm.parser(tokens);
            double value = algorithm.evalStep(parser);

            System.out.println(expression +" => "+ String.format("%.2f", value));
        }

    }
}
