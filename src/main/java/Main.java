// Uncomment this block to pass the first stage
// import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    static String[] builtinCommands = {
            "exit", "type", "echo", "pwd", "cd"
    };

    static final String SEPARATOR =  Pattern.quote(System.getProperty("file.separator"));

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
            } else if (input.equals("pwd")) {
                System.out.println(System.getProperty("user.dir"));
            } else if (input.startsWith("cd ")) {
                String original = input.substring(3);
                String dest = original;

                String[] currSegments = System.getProperty("user.dir").replaceAll(SEPARATOR, "/").split("/");
                List<String> currSegmentsList = Arrays.asList(currSegments);
                ArrayList<String> currSegmentsArrayList = new ArrayList<>(currSegmentsList);
                //relative path
                if(dest.startsWith(".")) {
                    //if(!dest.endsWith(SEPARATOR)) dest += SEPARATOR;
                    String[] destSegments = dest.split("/");

                    for(int i = 0; i < destSegments.length; i++){
                        if(destSegments[i].equals("..")){
                            currSegmentsArrayList.remove(currSegmentsArrayList.size() - 1);
                        }
                        else if(destSegments[i].equals(".")) {

                        }
                        else {
                            currSegmentsArrayList.add(destSegments[i]);
                        }
                        boolean isValid = checkLocationValid(String.join("\\", currSegmentsArrayList));
                        if(!isValid) {
                            System.out.println(dest + ": No such file or directory");
                            dest = original;
                        }
                    }

                    dest = String.join("/", currSegmentsArrayList);
                    System.setProperty("user.dir", dest);
                }
                else {
                    if (checkLocationValid(dest)) {
                        System.setProperty("user.dir", dest);
                    } else {
                        System.out.println(original + ": No such file or directory");
                    }
                }
            } else if (getCommandLocation(input.split(" ")[0]) != null
                    && !checkCommandInBuiltin(input.split(" ")[0])) {
                ProcessBuilder pb = new ProcessBuilder();
                for (String arg : input.split(" ")) {
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
