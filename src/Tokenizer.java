import java.util.*;

public class Tokenizer {
    public static ArrayList<Token> tokenize(String s){
        ArrayList<Token> buffer = new ArrayList<>();


        /*
        * Java cannot have primitives in collections so we have to use Character.
        * And need to add each element one by one
        * */
        ArrayList<Character> charList = new ArrayList<>();
        for( char c: s.toCharArray() ){
            charList.add(c);
        }
        charList.add(' '); //add an extra space at the end cus the loop returns without checking the last element... odd

        StringBuilder tmp = new StringBuilder();
        boolean inANumberSequence = false;
        boolean inAnIdentifierSequence = false;

        while(!charList.isEmpty()){
            char c = charList.remove(0); //pop
            if ( inANumberSequence && !Character.isDigit(c) ){ //we were building a number but we encountered something else
                buffer.add(new Token( Token.TokenType.NUMBER, Double.parseDouble(tmp.toString()) ));
                tmp.setLength(0);
                inANumberSequence = false;
            } else if( inAnIdentifierSequence && !Character.isLetter(c) ){ //we were building a sequence but encountered something else
                buffer.add(new Token(Token.TokenType.IDENTIFIER, tmp.toString()));
                tmp.setLength(0);
                inAnIdentifierSequence = false;
            }

            if( Arrays.asList('+','-','*','/','^','(',')').contains(c) ){
                buffer.add(new Token(Token.TokenType.SYMBOL, Character.toString(c)));
            } else if (Character.isDigit(c)) {
                tmp.append(c);
                inANumberSequence = true;
            } else if(Character.isLetter(c)){
                tmp.append(c);
                inAnIdentifierSequence = true;
            }
        }


        return buffer;

    }
}
