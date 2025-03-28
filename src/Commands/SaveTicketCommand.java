package Commands;

import Console.Client;
import Console.Session;
import Util.CSVWriter;
import java.io.IOException;

/**
 * Команда для сохранения коллекции билетов в CSV файл.
 * Сохраняет текущие данные в файл, путь к которому определяется в клиенте.
 */
public class SaveTicketCommand implements Command {

    // Путь к файлу, который извлекается через метод getFilename() в классе Client.
    private static final String fileputh = Client.getFilename();

    /**
     * Метод выполняется при вызове команды без аргументов.
     * Сохраняет коллекцию билетов в CSV файл, путь к которому указан в классе Client.
     * При возникновении ошибки при сохранении файла выбрасывает исключение.
     */
    @Override
    public void execute() {
        try {
            new CSVWriter(fileputh);  // Создание объекта CSVWriter для записи в файл
        } catch (IOException e) {
            throw new RuntimeException(e);  // В случае ошибки выбрасывается исключение
        }
        System.out.println("" +
                "██╗░░░██╗██████╗░██╗░░░░░░█████╗░░█████╗░██████╗░███████╗██████╗░\n" +
                "██║░░░██║██╔══██╗██║░░░░░██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔══██╗\n" +
                "██║░░░██║██████╔╝██║░░░░░██║░░██║███████║██║░░██║█████╗░░██║░░██║\n" +
                "██║░░░██║██╔═══╝░██║░░░░░██║░░██║██╔══██║██║░░██║██╔══╝░░██║░░██║\n" +
                "╚██████╔╝██║░░░░░███████╗╚█████╔╝██║░░██║██████╔╝███████╗██████╔╝\n" +
                "░╚═════╝░╚═╝░░░░░╚══════╝░╚════╝░╚═╝░░╚═╝╚═════╝░╚══════╝╚═════╝░");
    }

    /**
     * Метод выполняется при вызове команды с аргументами.
     * В данном случае команда не принимает аргументы, и метод выводит сообщение об этом.
     * @param args массив строковых аргументов (не используется).
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Команда save вводится без аргументов");
    }

    /**
     * Метод выводит информацию о команде.
     * Информирует пользователя о том, что команда сохраняет коллекцию билетов в CSV файл.
     */
    @Override
    public void PrintInfo() {
        System.out.println("save - сохраняет коллекцию билетов в csv файл");
    }
}
