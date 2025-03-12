import Console.Session;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        if (args.length == 0) {
            System.out.println("you need to write filename");
            System.exit(0);
        }
        if (args.length > 1) {
            System.out.println("you need to write only filename, don't write many arguments");
            System.exit(0);
        }
        Session session = new Session();
        session.run();
    }
}