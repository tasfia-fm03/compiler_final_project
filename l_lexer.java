
import java.util.ArrayList;
import java.util.List;

public class l_lexer {
    private final String code;
    private int position;
    private int line;
    private final List<Token> tokenss;

    public l_lexer(String code) {
        this.code = code;
        this.position = 0;
        this.line = 1;
        this.tokenss = new ArrayList<>();
    }

    private Character getChar() {
        return (position < code.length()) ? code.charAt(position) : null;
    }

    private void advance() {
        if (position < code.length() && code.charAt(position) == '\n') {
            line++;
        }
        position++;
    }

    private boolean isBanglaLetter(char c) {
        return c >= '\u0980' && c <= '\u09FF';
    }

    public List<Token> tokenize() {
        while (true) {
            Character c = getChar();
            if (c == null) {
                break;
            }

            
            if (Character.isWhitespace(c)) {
                advance();
                continue;
            }

            
            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (c != null && Character.isDigit(c)) {
                    num.append(c);
                    advance();
                    c = getChar();
                }
                tokenss.add(new Token(banglalanguage.পূর্ণসংখ্যা, num.toString(), line));
                continue;
            }

    
            if (Character.isLetter(c) || c == '_' || isBanglaLetter(c)) {
                StringBuilder word = new StringBuilder();
                while (c != null && (Character.isLetterOrDigit(c) || c == '_' || isBanglaLetter(c))) {
                    word.append(c);
                    advance();
                    c = getChar();
                }
                String text = word.toString();

                switch (text) {
                  case "সংখ্যা" -> tokenss.add(new Token(banglalanguage.সংখ্যা, null, line));
                  case "দেখো" -> tokenss.add(new Token(banglalanguage.দেখো, null, line));
                  default -> tokenss.add(new Token(banglalanguage.পরিচায়ক, text, line));
}
                continue;
            }
            switch(c) {
                case ':'->
                    tokenss.add(new Token(banglalanguage.নির্ধারণ_করা, ":", line));
                case '+'->
                    tokenss.add(new Token(banglalanguage.যোগ, "+", line));
                case '-'->
                    tokenss.add(new Token(banglalanguage.বিয়োগ, "-", line));
                case '*'->
                    tokenss.add(new Token(banglalanguage.গুণ, "*", line));
                case '/'->
                    tokenss.add(new Token(banglalanguage.ভাগ, "/", line));
                    
                case ';'->
                    tokenss.add(new Token(banglalanguage.সেমিকোলন, ";", line));
                    
                case '('->
                    tokenss.add(new Token(banglalanguage.বাম_বন্ধনী, "(", line));
                    
                case ')'->
                    tokenss.add(new Token(banglalanguage.ডান_বন্ধনী, ")", line));
                    
                case '{'->
                    tokenss.add(new Token(banglalanguage.বাম_কার্লি_ব্র্যাকেট, "{", line));
                    
                case '}'->
                    tokenss.add(new Token(banglalanguage.ডান_কার্লি_ব্র্যাকেট, "}", line));
                    
                case '='->
                    tokenss.add(new Token(banglalanguage.সমান, "=", line));
                    
                default->{
                    System.err.println("লেক্সার ভুল অবৈধ অক্ষর '" + c + "' লাইন নং " + line);
                    tokenss.add(new Token(banglalanguage.অজানা, String.valueOf(c), line));
                }
            }
            advance();
        }
        return tokenss;
    }
}