/**
 * ClassName: Pwd
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Henry
 * @Create 6/18/2024 10:07 PM
 * @Version 1.0
 */
public class Pwd extends Command{

    public Pwd() {
        this.name = "pwd";
    }

    @Override
    public void execute(String[] path) {
        System.out.println(System.getProperty("user.dir"));
    }
}
