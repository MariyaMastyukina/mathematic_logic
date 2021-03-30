import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            ParseLine parseLine = new ParseLine();
            System.out.println(parseLine.parseLine(line.replaceAll(" ", "")));
        }
    }
}
