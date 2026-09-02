
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpretor {
    private final List<Token> tokens;
    private int ptr = 0;
    private final Map<String, Integer> memory = new HashMap<>();

    public Interpretor(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token peek() {
        return ptr < tokens.size() ? tokens.get(ptr) : null;
    }

    private Token consume(banglalanguage expectedType) {
        Token t = peek();
        if (t != null && t.type == expectedType) {
            ptr++;
            return t;
        }
        throw new RuntimeException("Syntax Error" + expectedType + " at line " + (t != null ? t.line : "unknown"));
    }

    public void interpret() {
        while (peek() != null) {
            Token current = peek();
            
            switch (current.type) {
                case সংখ্যা -> handleVariableDeclaration();
                case দেখো -> handlePrintStatement();
                default -> ptr++;
            }
        }
    }

    private void handleVariableDeclaration() {
        consume(banglalanguage.সংখ্যা);
        Token varToken = consume(banglalanguage.পরিচায়ক);
        consume(banglalanguage.নির্ধারণ_করা);
        
        int value = evaluateExpression();
        consume(banglalanguage.সেমিকোলন);
        
        memory.put(varToken.value, value);
    }


    private void handlePrintStatement() {
        consume(banglalanguage.দেখো);
        int result = evaluateExpression();
        consume(banglalanguage.সেমিকোলন);
        System.out.println("Output akane dekacche: " + result);
    }

    private int evaluateExpression() {
        int value = getPrimaryValue();
        
        while (peek() != null && peek().type == banglalanguage.যোগ) {
            consume(banglalanguage.যোগ);
            value += getPrimaryValue();
        }
        return value;
    }

    private int getPrimaryValue() {
        Token t = peek();
        if (t != null && t.type == banglalanguage.পূর্ণসংখ্যা) {
            consume(banglalanguage.পূর্ণসংখ্যা);
            return Integer.parseInt(t.value);
        } else if (t != null && t.type == banglalanguage.পরিচায়ক) {
            consume(banglalanguage.পরিচায়ক);
            if (!memory.containsKey(t.value)) {
                throw new RuntimeException("Error: Variable '" + t.value + "' is not declared.");
            }
            return memory.get(t.value);
        }
        throw new RuntimeException("Syntax Error");
    }
}
