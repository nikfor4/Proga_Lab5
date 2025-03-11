package Console;

import java.util.Scanner;
import Commands.CommandProcessor;
import Util.QueueManager;

public class Client {
    /**
     *
     */
    public void userInput() {
        QueueManager manager = new QueueManager(13); // Очередь команд (история)
        CommandProcessor commandProcessor = new CommandProcessor(manager);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                System.out.println("Выход...\n███████████████████\n" +
                        "█▄─▄─▀█▄─█─▄█▄─▄▄─█\n" +
                        "██─▄─▀██▄─▄███─▄█▀█\n" +
                        "▀▄▄▄▄▀▀▀▄▄▄▀▀▄▄▄▄▄▀");
                System.exit(0);
            }

            commandProcessor.CommandPut(command); // Выполняем команду
        }
    }
}
