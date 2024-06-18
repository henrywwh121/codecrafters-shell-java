import java.io.File;

/**
 * ClassName: NotFound
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Henry
 * @Create 6/18/2024 7:54 PM
 * @Version 1.0
 */
public class NotFound extends Command{

    public NotFound(String name) {
        this.name = name;
    }

    @Override
    public void execute(String[] paths) {
        try {
            File file = this.findFile(paths, this.name);
            ProcessBuilder pb = new ProcessBuilder(file.getAbsolutePath(), this.props);
            Process p = pb.start();
            p.waitFor();
            p.getInputStream().transferTo(System.out);
        }
        catch (Exception e) {
            System.out.println(this.name + " : " + "Command Not Found");
        }
    }
}
