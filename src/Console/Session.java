package Console;

public class Session {
    private static String filename;
    public void run(String filename) {
        Client client = new Client();
        client.userInput(filename);
    }
}