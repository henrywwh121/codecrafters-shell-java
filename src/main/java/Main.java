// Uncomment this block to pass the first stage
// import java.util.Scanner;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static String[] builtinCommands = {
            "exit","type","echo"
    };

    public static void main(String[] args) throws Exception {

        while(true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if(input.equals("exit 0")) {
                break;
            }

            if(input.startsWith("echo ")) {
                System.out.println(input.substring(4).trim());
            }
            else if(input.startsWith("type ")) {
                String typeToCheck = input.substring(4).trim();
                boolean isBuiltin = Arrays.asList(builtinCommands).contains(typeToCheck);
                if(isBuiltin) {
                    System.out.println(typeToCheck + " is a shell builtin");
                }
                else {
                    System.out.println(typeToCheck + ": not found");
                }
            }
            else {
                System.out.println(input + ": command not found");
            }
        }
    }
}
