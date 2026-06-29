import java.util.Scanner;

public class MainLoop {
    static String header = """
            +===============================================+
            |                  CALCULATOR!                  |
            +-----------------------------------------------+
            |    it's... a calculator. use +,-,*,/ and ^.   |
            |      use sqrt() to take the square root.      |
            |           Press enter to calculate            |
            +===============================================+
            """;
    public static void main(String[] args) {
        Parser parser = new Parser();
        Scanner scan = new Scanner(System.in); // Reading from System.in
        System.out.println( header );

        while(true){
            System.out.print("> ");
            String input = scan.next();
            double output = parser.parse(input);
            System.out.println(output);
        }
    }
}
