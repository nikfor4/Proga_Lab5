package Console;

import java.util.Scanner;
import Commands.CommandProcessor;
import Util.QueueManager;

public class Client {
    public static String Filename;
    /**
     *
     */
    public void userInput(String filename) {
        Filename = filename;
        QueueManager manager = new QueueManager(13); // Очередь команд (история)
        CommandProcessor commandProcessor = new CommandProcessor(manager);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                System.out.println(
                        "██████╗░██╗░░░██╗███████╗\n" +
                        "██╔══██╗╚██╗░██╔╝██╔════╝\n" +
                        "██████╦╝░╚████╔╝░█████╗░░\n" +
                        "██╔══██╗░░╚██╔╝░░██╔══╝░░\n" +
                        "██████╦╝░░░██║░░░███████╗\n" +
                        "╚═════╝░░░░╚═╝░░░╚══════╝");
                System.exit(0);
            }

            commandProcessor.CommandPut(command); // Выполняем команду
        }
    }
    public static String getFilename() {
        return Filename;
    }

}
