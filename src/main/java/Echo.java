/**
 * ClassName: Echo
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Henry
 * @Create 6/18/2024 7:28 PM
 * @Version 1.0
 */
public class Echo extends Command{
    public Echo() {
        this.name = "echo";
    }

    @Override
    public void execute(String[] path) {
        System.out.println(this.props);
    }
}
