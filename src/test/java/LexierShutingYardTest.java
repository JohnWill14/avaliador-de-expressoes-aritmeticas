
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


@DisplayName("Test method lexier from Shunting-yard algorithm")
public class LexierShutingYardTest {
    @Test
    @DisplayName("Test function Lexier")
    void testLexier(){
        ShuntingYard<Object> algorithm = new ShuntingYard<>();

        List<String> lexier1 = algorithm.lexier("31 * (4 + 10)");
        List<String> lexier2 = algorithm.lexier("1 + 3");
        List<String> lexier3 = algorithm.lexier("1 + 2 + 3 * 4");
        List<String> lexier4 = algorithm.lexier("1 + -2 + 3 * 4");
        List<String> lexier5 = algorithm.lexier("4 / 2 + 7");

        Assertions.assertThat(lexier1)
                .isEqualTo(List.of("31", "*", "(", "4", "+", "10", ")"));

        Assertions.assertThat(lexier2)
                .isEqualTo(List.of("1", "+", "3"));

        Assertions.assertThat(lexier3)
                .isEqualTo(List.of("1", "+", "2", "+", "3", "*", "4"));

        Assertions.assertThat(lexier4)
                .isEqualTo(List.of("1", "+", "-2", "+", "3", "*", "4"));

        Assertions.assertThat(lexier5)
                .isEqualTo(List.of("4", "/", "2", "+", "7"));
    }
}
