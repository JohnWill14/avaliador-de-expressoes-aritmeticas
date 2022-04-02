package algorithm;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


@DisplayName("Test method parser from Shunting-yard algorithm")
class ParserShuntingYardTest {
    @Test
    @DisplayName("Test function parser example")
    void testParserExample(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens = algorithm.lexier("5 + 2 / (3 - 8) * 5 - 2");

        List<String> parser = algorithm.parser(tokens);



        Assertions.assertThat(parser)
                .isEqualTo(List.of("5", "2", "3", "8", "-", "/", "5", "*", "+", "2", "-"));
    }

    @Test
    @DisplayName("Test function parser 1")
    void testParser1(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens = algorithm.lexier("31 * 4 + 10");

        List<String> parser = algorithm.parser(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("31", "4", "*", "10", "+"));
    }

    @Test
    @DisplayName("Test function parser 2")
    void testParser2(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens = algorithm.lexier("31 * (4 + 10)");

        List<String> parser = algorithm.parser(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("31", "4", "10", "+", "*"));
    }

    @Test
    @DisplayName("Test function parser 3")
    void testParser3(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens = algorithm.lexier("1 + 3");

        List<String> parser = algorithm.parser(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "3", "+"));
    }

    @Test
    @DisplayName("Test function parser 4")
    void testParser4(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens = algorithm.lexier("1 + 2 * 3");

        List<String> parser = algorithm.parser(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "2", "3", "*", "+"));
    }

    @Test
    @DisplayName("Test function parser 5")
    void testParser5(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens = algorithm.lexier("4 / 2 + 7");

        List<String> parser = algorithm.parser(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("4", "2", "/", "7", "+"));
    }

    @Test
    @DisplayName("Test function parser 6")
    void testParser6(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens = algorithm.lexier("1 + 2 + 3 * 4");

        List<String> parser = algorithm.parser(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "2", "+", "3", "4", "*", "+"));
    }


    @Test
    @DisplayName("Test function parser 7")
    void testParser7(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens = algorithm.lexier("(1 + 2 + 3) * 4");

        List<String> parser = algorithm.parser(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "2", "+", "3", "+", "4", "*"));
    }


    @Test
    @DisplayName("Test function parser 8")
    void testParser8(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens = algorithm.lexier("(10 / 3 + 23) * (1 - 4)");

        List<String> parser = algorithm.parser(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("10", "3", "/", "23", "+", "1", "4", "-", "*"));
    }

    @Test
    @DisplayName("Test function parser 9")
    void testParser9(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens = algorithm.lexier("((1 + 3) * 8 + 1) / 3");

        List<String> parser = algorithm.parser(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "3", "+", "8", "*", "1", "+", "3", "/"));
    }


    @Test
    @DisplayName("Test function parser 10")
    void testParser10(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens = algorithm.lexier("10 * 20 + 3 * 7 + 2 * 3 + 10 / 3 * 4");

        List<String> parser = algorithm.parser(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("10", "20", "*", "3", "7", "*", "+", "2", "3", "*", "+", "10", "3", "/", "4", "*", "+"));
    }

    @Test
    @DisplayName("Test function parser 11")
    void testParser11(){
        ShuntingYard algorithm = new ShuntingYard();

        List<String> tokens = algorithm.lexier("1 + -2 * 3");

        List<String> parser = algorithm.parser(tokens);

        Assertions.assertThat(parser)
                .isEqualTo(List.of("1", "-2", "3", "*", "+"));
    }

}