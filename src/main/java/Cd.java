/**
 * ClassName: Cd
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Henry
 * @Create 6/18/2024 11:01 PM
 * @Version 1.0
 */
public class Cd extends Command{

    public Cd() {
        this.name = "cd";
    }

    @Override
    public void execute(String[] path) {
        if(this.props.startsWith(".")) {

        }
        else if(this.props.equals("~")) {

        }
        else {

        }
    }
}
