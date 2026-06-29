import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    private enum functions{
        NONE,SQRT
    }

    public double parse(String s){
        ArrayList<Token> tokens = Tokenizer.tokenize(s);
        tokens.add(new Token(Token.TokenType.TERMINATOR)); //we need a terminator
        /*TODO: add syntax checking*/
        double result = parse_expression(tokens);
        return result;
    }
    private double parse_expression(ArrayList<Token> tokens){
        double result = parse_term(tokens);
        Token t = tokens.get(0);
        while( t.getType() == Token.TokenType.SYMBOL && Arrays.asList("+","-").contains(t.getString_value()) ){
            tokens.remove(0);
            double rightHandSide = parse_term(tokens);
            switch (t.getString_value()){
                case "+":{
                    result += rightHandSide;
                    break;
                }
                case "-":{
                    result -= rightHandSide;
                    break;
                }
            }
            t = tokens.get(0);
        }

        return result;
    }

    private double parse_term(ArrayList<Token> tokens){
        double result = parse_factor(tokens);
        Token t = tokens.get(0);
        while( t.getType() == Token.TokenType.SYMBOL && Arrays.asList("*","/").contains(t.getString_value()) ){
            tokens.remove(0);
            double rightHandSide = parse_factor(tokens);
            switch (t.getString_value()){
                case "*":{
                    result *= rightHandSide;
                    break;
                }
                case "/":{
                    result /= rightHandSide;
                    break;
                }
            }
            t = tokens.get(0);
        }

        return result;
    }

    private double parse_factor(ArrayList<Token> tokens){
        Token t = tokens.get(0);
        int sign = ( ( t.getType() == Token.TokenType.SYMBOL && t.getString_value().equals("-") )?-1:+1 );
        if( ( t.getType() == Token.TokenType.SYMBOL && t.getString_value().equals("+") ) || sign < 0 ){
            tokens.remove(0);
        }
        double result = parse_item(tokens);

        while( tokens.get(0).getType() == Token.TokenType.SYMBOL && tokens.get(0).getString_value().equals("^") ){
            tokens.remove(0);
            double rightHandSide = parse_factor(tokens);
            result = Math.pow(result, rightHandSide);
        }

        return sign * result;
    }

    private double parse_item(ArrayList<Token> tokens){
        functions functionToApply = functions.NONE;
        Token t = tokens.get(0);
        tokens.remove(0);
        if( t.getType() == Token.TokenType.NUMBER ){
            return t.getNumber_value();
        }
        if( t.getType() == Token.TokenType.IDENTIFIER ){
            /*TODO: add support for variables*/
            if(t.getString_value().equalsIgnoreCase("sqrt")){ //a bit HACK y
                functionToApply = functions.SQRT;

                t = tokens.get(0);
                tokens.remove(0);
            }
        }
        if( !( t.getType() == Token.TokenType.SYMBOL && t.getString_value().equals("(") ) ){
            System.out.println("Expected number, function, or '('!");
        }
        double expression = parse_expression(tokens);
        if( !( tokens.get(0).getType() == Token.TokenType.SYMBOL && tokens.get(0).getString_value().equals(")") ) ){
            System.out.println("Expected operator or ')'!");
        }
        tokens.remove(0);
        switch (functionToApply){
            case SQRT -> {
                return Math.sqrt(expression);
            }
        }
        return expression;
    }
}
