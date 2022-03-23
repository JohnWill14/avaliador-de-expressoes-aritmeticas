import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Test method lexier from Shunting-yard algorithm")
public class EvalStepTest {
    @Test
    @DisplayName("Test function Lexier")
    void testEvalStep(){
        ShuntingYard<Object> algorithm = new ShuntingYard<>();

        List<String> tokens = algorithm.lexier("31 * (4 + 10)");
        List<String> parser = algorithm.parser(tokens);
        double value = algorithm.evalStep(parser);

        Assertions.assertThat(value)
                .isEqualTo(434d);

    }

    @Test
    @DisplayName("Test function Lexier 1")
    void testEvalStep1(){
        ShuntingYard<Object> algorithm = new ShuntingYard<>();

        List<String> tokens = algorithm.lexier("5 + 2 / (3 - 8) * 5 - 2");
        List<String> parser = algorithm.parser(tokens);
        double value = algorithm.evalStep(parser);

        Assertions.assertThat(value)
                .isEqualTo(1d);

    }

    @Test
    @DisplayName("Test function Lexier 2")
    void testEvalStep2(){
        ShuntingYard<Object> algorithm = new ShuntingYard<>();

        List<String> tokens = algorithm.lexier("1 + 2 * 3");
        List<String> parser = algorithm.parser(tokens);
        double value = algorithm.evalStep(parser);

        Assertions.assertThat(value)
                .isEqualTo(7d);

    }

    @Test
    @DisplayName("Test function Lexier 3")
    void testEvalStep3(){
        ShuntingYard<Object> algorithm = new ShuntingYard<>();

        List<String> tokens = algorithm.lexier("4 / 2 + 7");
        List<String> parser = algorithm.parser(tokens);
        double value = algorithm.evalStep(parser);

        Assertions.assertThat(value)
                .isEqualTo(9d);

    }

    @Test
    @DisplayName("Test function Lexier 4")
    void testEvalStep4(){
        ShuntingYard<Object> algorithm = new ShuntingYard<>();

        List<String> tokens = algorithm.lexier("1 + 2 + 3 * 4");
        List<String> parser = algorithm.parser(tokens);
        double value = algorithm.evalStep(parser);

        Assertions.assertThat(value)
                .isEqualTo(15d);

    }

    @Test
    @DisplayName("Test function Lexier 5")
    void testEvalStep5(){
        ShuntingYard<Object> algorithm = new ShuntingYard<>();

        List<String> tokens = algorithm.lexier("(1 + 2 + 3) * 4");
        List<String> parser = algorithm.parser(tokens);
        double value = algorithm.evalStep(parser);

        Assertions.assertThat(value)
                .isEqualTo(24d);

    }
}
