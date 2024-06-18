import java.io.File;
import java.io.FileNotFoundException;

/**
 * ClassName: Command
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Henry
 * @Create 6/18/2024 7:09 PM
 * @Version 1.0
 */
public abstract class Command {
    protected String name;
    protected String props;
    protected boolean isBuiltin;
    public abstract void execute(String[] path);
    public void setProps(String props) {
        this.props = props;
    };

    protected File findFile(String[] paths, String fileName) throws FileNotFoundException {
        for (String path : paths) {
            File file = new File(path, fileName);
            if (file.exists()) {
                return file;
            }
        }
        throw new FileNotFoundException();
    }
}
