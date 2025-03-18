package Util;

import Collections.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс {@code CSVDownloader} отвечает за загрузку данных из CSV-файла
 * и обновление коллекции билетов.
 *
 */
public class CSVDownloader {
    /**
     * Главный метод приложения.
     *
     * @param args аргументы командной строки, где args[0] — путь к CSV-файлу.
     */
    public static void main(String[] args) {
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
    }
}
