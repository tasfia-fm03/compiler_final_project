

public class Token {
    public final banglalanguage type;
    public final String value;
    public final int line;

    public Token(banglalanguage type, String value, int line) {
        this.type = type;
        this.value = value;
        this.line = line;
    }
    @Override
    public String toString() {
        return "Token(" + type + ", \"" + value + "\", line " + line + ")";    
    }
}

