// Uncomment this block to pass the first stage
// import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static String[] builtinCommands = {
            "exit", "type", "echo", "pwd", "cd"
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
                boolean isBuiltin = checkCommandInBuiltin(typeToCheck);
                if (isBuiltin) {
                    System.out.println(typeToCheck + " is a shell builtin");
                } else {
                    String path;
                    if ((path = getCommandLocation(typeToCheck)) != null) {
                        System.out.println(typeToCheck + " is " + path);
                    } else {
                        System.out.println(typeToCheck + ": not found");
                    }
                }
            } else if (input.equals("pwd")){
                System.out.println(System.getProperty("user.dir"));
            }
            else if(input.startsWith("cd ")) {
                String dest = input.substring(3);
                if(checkLocationValid(dest)) {
                    System.setProperty("user.dir", dest);
                }
                else {
                    System.out.println(dest + ": No such file or directory");
                }
            }
            else if (getCommandLocation(input.split(" ")[0]) != null
                    && !checkCommandInBuiltin(input.split(" ")[0])) {
                ProcessBuilder pb = new ProcessBuilder();
                for(String arg: input.split(" ")) {
                    pb.command().add(arg);
                }
                Process p = pb.start();
                p.waitFor();
                p.getInputStream().transferTo(System.out);
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

    static boolean checkCommandInBuiltin(String command) {
        return Arrays.asList(builtinCommands).contains(command);
    }

    static boolean checkLocationValid(String location) {
        File file = new File(location);
        return file.exists();
    }
}
