// Uncomment this block to pass the first stage
// import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static String[] builtinCommands = {
            "exit", "type", "echo"
    };

    static String env = System.getenv("PATH");

    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("exit 0")) {
                break;
            }

            if (input.startsWith("echo ")) {
                System.out.println(input.substring(4).trim());
            } else if (input.startsWith("type ")) {
                String typeToCheck = input.substring(4).trim();
                boolean isBuiltin = Arrays.asList(builtinCommands).contains(typeToCheck);
                if (isBuiltin) {
                    System.out.println(typeToCheck + " is a shell builtin");
                } else {
                    String path;
                    if ((path = getCommandLocation(typeToCheck)) != null) {
                        System.out.println(typeToCheck + "is" + path);
                    } else {
                        System.out.println(input + ": not found");
                    }
                }
            } else if (getCommandLocation(input.split(" ")[0]) != null) {
                ProcessBuilder pb = new ProcessBuilder(input).inheritIO();
                Process p = pb.start();
                p.waitFor();
            } else {
                System.out.println(input + ": command not found");
            }
        }
    }

    static String getCommandLocation(String typeToCheck) {
        for (String path : env.split(":")) {
            File file = new File(path, typeToCheck);
            if (file.exists()) {
                return file.getPath();
            }
        }
        return null;
    }
}
