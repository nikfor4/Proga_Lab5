import Collections.*;
import Console.Session;
import Util.CSVDownloader;
import Util.CSVReader;
import Util.RunStroke;

import java.io.IOException;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Главный класс программы, запускающий процесс обработки CSV файла, загрузки и обновления данных.
 * Также выводит прогресс-бар и после завершения запускает сессию команд.
 */
public class Main {

    private static String filename;

    /**
     * Главный метод программы. Принимает аргумент командной строки (filename) для чтения данных,
     * выводит прогресс-бар и затем загружает данные из CSV файла, обновляя коллекцию билетов.
     * После завершения работы выводится приветственное сообщение и запускается сессия.
     *
     * @param args аргументы командной строки (должен быть передан только filename)
     * @throws InterruptedException исключение, которое может возникнуть при задержках в процессе
     * @throws IOException исключение при работе с файлами
     */
    public static void main(String[] args) throws InterruptedException, IOException {

        // Проверка на наличие аргумента командной строки
        filename = args[0];
        System.setProperty("file.encoding", "UTF-8");
        if (args.length == 0) {
            System.out.println("надо написать filename");
            System.exit(0);
        }
        if (args.length > 1) {
            System.out.println("нужно написать только filename");
            System.exit(0);
        }

        RunStroke.main();

        CSVDownloader.main(args);

        // Вывод приветственного сообщения
        System.out.println("\n" +
                "██████╗░░█████╗░░██╗░░░░░░░██╗███╗░░██╗██╗░░░░░░█████╗░░█████╗░██████╗░███████╗██████╗░\n" +
                "██╔══██╗██╔══██╗░██║░░██╗░░██║████╗░██║██║░░░░░██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔══██╗\n" +
                "██║░░██║██║░░██║░╚██╗████╗██╔╝██╔██╗██║██║░░░░░██║░░██║███████║██║░░██║█████╗░░██║░░██║\n" +
                "██║░░██║██║░░██║░░████╔═████║░██║╚████║██║░░░░░██║░░██║██╔══██║██║░░██║██╔══╝░░██║░░██║\n" +
                "██████╔╝╚█████╔╝░░╚██╔╝░╚██╔╝░██║░╚███║███████╗╚█████╔╝██║░░██║██████╔╝███████╗██████╔╝\n" +
                "╚═════╝░░╚════╝░░░░╚═╝░░░╚═╝░░╚═╝░░╚══╝╚══════╝░╚════╝░╚═╝░░╚═╝╚═════╝░╚══════╝╚═════╝░");

        // Запуск сессии
        Session session = new Session();
        session.run(filename);
    }
}
