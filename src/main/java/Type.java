import java.io.File;
import java.util.HashMap;

/**
 * ClassName: Type
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Henry
 * @Create 6/18/2024 8:53 PM
 * @Version 1.0
 */
public class Type extends Command{
    private Command request;

    public Type() {
        this.name = "type";
        this.isBuiltin = true;
    }

    @Override
    public void execute(String[] path) {
        if(request != null) {
            System.out.println(request.name + " is a shell builtin");
            return;
        }

        try {
            File file = this.findFile(path, this.props);
            System.out.println(this.props + " is " + file.getPath());
        }
        catch (Exception e) {
            System.out.println(this.props + ": not found");
        }
    }

    @Override
    public void setProps(String props) {
        request = null;
        HashMap<String, Command> builtinCommand = CommandInit.BuiltinCommand;
        this.props = props;
        if(builtinCommand.containsKey(props)) {
            request = builtinCommand.get(props);
        }
    }
}
