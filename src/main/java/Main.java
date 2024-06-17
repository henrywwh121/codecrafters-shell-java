// Uncomment this block to pass the first stage
// import java.util.Scanner;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static String[] builtinCommands = {
            "exit","type","echo"
    };

    static String env = System.getenv("PATH");

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
                    String response = getExecutePath(typeToCheck);
                    System.out.println(response);
                }
            }
            else {
                System.out.println(input + ": command not found");
            }
        }
    }

    private static String getExecutePath(String typeToCheck) {
        String response = typeToCheck + ": not found";

        String[] paths = env.split(";");
        for (String path : paths) {
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.getName().equals(typeToCheck)) {
                        response = typeToCheck + " is " + path;
                        break;
                    }
                }
            }
        }
        return response;
    }
}
