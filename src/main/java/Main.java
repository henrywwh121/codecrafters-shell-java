// Uncomment this block to pass the first stage
// import java.util.Scanner;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        
        while(true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if(Objects.equals(input, "exit 0")) {
                break;
            }
            System.out.println(input + ": command not found");
        }
    }
}
