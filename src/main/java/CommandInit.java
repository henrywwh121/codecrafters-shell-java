import java.util.HashMap;

/**
 * ClassName: CommandInit
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Henry
 * @Create 6/18/2024 7:34 PM
 * @Version 1.0
 */
public class CommandInit {
    public static HashMap<String, Command> BuiltinCommand = new HashMap<>();

    public CommandInit() {
        BuiltinCommand.put("echo", new Echo());
        BuiltinCommand.put("exit", new Exit());
        BuiltinCommand.put("type", new Type());
        BuiltinCommand.put("pwd", new Pwd());
        BuiltinCommand.put("cd", new Cd());
    }

    public Command getCommand(String input) {
        String[] command = input.trim().split(" ", 2);
        String commandType = command[0];

        Command commandClass;
        if(BuiltinCommand.containsKey(commandType)) {
            commandClass = BuiltinCommand.get(commandType);
        }
        else {
            commandClass = new NotFound(commandType);
        }
        commandClass.setProps("");
        if(command.length > 1) commandClass.setProps(command[1].trim());
        return commandClass;
    }
}
