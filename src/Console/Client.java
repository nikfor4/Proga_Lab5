package Console;

import java.util.Scanner;
import Commands.CommandProcessor;

public class Client {

    public void userInput() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        CommandProcessor commandProcessor = new CommandProcessor();

        /* Обращаемся к commandprocessor, берем метод put, передаем ему команду,
        которую ввел пользователь */
        commandProcessor.CommandPut(command);
    }
}