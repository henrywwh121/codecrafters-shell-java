import java.io.File;
import java.util.Scanner;

/**
 * ClassName: Shell
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Henry
 * @Create 6/18/2024 7:34 PM
 * @Version 1.0
 */
public class Shell_OOP {
    static String[] paths = System.getenv("PATH").split(File.pathSeparator);
    public static void main(String[] args) {
        CommandInit init = new CommandInit();
        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            Command command = init.getCommand(input);
            command.execute(paths);
        }
    }
}
