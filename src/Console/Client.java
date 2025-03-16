package Console;

import java.util.Scanner;
import Commands.CommandProcessor;
import Util.QueueManager;

/**
 * Класс Client представляет собой клиентскую часть программы,
 * которая обрабатывает ввод пользователя и выполняет команды.
 */
public class Client {
    // Путь к файлу
    public static String Filename;

    /**
     * Метод userInput обрабатывает пользовательский ввод и выполняет команды,
     * добавляя их в очередь для дальнейшего выполнения.
     * @param filename путь к файлу, который будет использован для сохранения данных
     */
    public void userInput(String filename) {
        Filename = filename;
        QueueManager manager = new QueueManager(13); // Очередь команд (история)
        CommandProcessor commandProcessor = new CommandProcessor(manager);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();

            commandProcessor.CommandPut(command); // Выполняем команду
        }
    }

    /**
     * Метод getFilename возвращает путь к файлу.
     * @return путь к файлу
     */
    public static String getFilename() {
        return Filename;
    }
}
