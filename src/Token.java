public class Token {
    @Override
    public String toString() {
        return "Token{" + '\n' +
                '\t' + "type=" + type + '\n' +
                '\t' + ", number_value=" + number_value + '\n' +
                '\t' + ", string_value='" + string_value + '\'' + '\n' +
                '}' + '\n';
    }

    public enum TokenType{
        SYMBOL, NUMBER, IDENTIFIER, TERMINATOR
    }

    private TokenType type;
    private double number_value; // only for numbers
    private String string_value; // used for rest

    public Token(TokenType type, double number_value) {
        this.type = type;
        this.number_value = number_value;
    }

    public Token(TokenType type, String string_value) {
        this.type = type;
        this.string_value = string_value;
    }

    public Token(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return type;
    }

    public double getNumber_value() {
        return number_value;
    }

    public String getString_value() {
        return string_value;
    }
}
