/**
 * ClassName: Exit
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Henry
 * @Create 6/18/2024 8:02 PM
 * @Version 1.0
 */
public class Exit extends Command{
    public Exit() {
        this.name = "exit";
        this.isBuiltin = true;
    }

    @Override
    public void execute(String[] path) {
        System.exit(0);
    }
}
