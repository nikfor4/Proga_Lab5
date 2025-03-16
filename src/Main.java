import Collections.*;
import Console.Session;
import Util.CSVReader;

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

        int totalLength = 35;
        int progress = 0;
        float progressPercentage = 0;
        Random random = new Random();

        // Печать прогресс-бара с случайными задержками
        while (progress <= totalLength) {
            StringBuilder progressBar = new StringBuilder("|");
            for (int i = 0; i < progress; i++) {
                progressBar.append("#");
            }

            for (int i = progress; i < totalLength; i++) {
                progressBar.append(" ");
            }
            progressPercentage = (float) progress / (float) totalLength * 100;
            String formatedProgressPercentage = ((String.format("%.1f", progressPercentage)));

            progressBar.append("|");
            System.out.print("\r" + progressBar.toString() + (formatedProgressPercentage) + "%");

            progress++;
            int delay = random.nextInt(91) + 10;

            Thread.sleep(delay);
        }

        System.out.println();

        // Чтение данных из CSV файла и обновление коллекции билетов
        try (CSVReader csvReader = new CSVReader(args[0])) {
            while (csvReader.hasNext()) {
                String[] row = csvReader.readLine();
                String[] row_splt_coo = row[2].split(";");
                Integer x = Integer.parseInt(row_splt_coo[0]);
                int y = Integer.parseInt(row_splt_coo[1]);

                String dateTimeString = row[3];
                if (dateTimeString.contains(".")) {
                    int dotIndex = dateTimeString.indexOf(".") + 4; // 3 цифры после точки
                    dateTimeString = dateTimeString.substring(0, Math.min(dotIndex, dateTimeString.length()));
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                Event event = Event.createEvent(row[6], Integer.parseInt(row[7]), EventType.valueOf(row[8]));
                try {
                    Ticket.updateTicket(Integer.parseInt(row[0]), row[1], new Coordinates(x, y), Float.parseFloat(row[4]), TicketType.valueOf(row[5]), event, dateTime);
                } catch (Exception e) {
                    System.out.println(e.getMessage() + " в id " + row[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
