

import java.util.List;
public class Main {
    public static void main(String[] args) {
        String source = """
            সংখ্যা x : ১০;
            সংখ্যা y : ১০;
            দেখো x + y;
            """;

        l_lexer lexer = new l_lexer(source);
        List<Token> tokens = lexer.tokenize();
        System.out.println("--- Run Code ---");
        Interpretor interpreter = new Interpretor(tokens);
        interpreter.interpret();
    }
}
