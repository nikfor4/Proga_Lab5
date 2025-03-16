package Util;

import Collections.Ticket;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

/**
 * Класс CSVWriter используется для записи данных в CSV файл.
 * Он предоставляет методы для записи одиночных билетов или всей коллекции билетов в файл.
 */
public class CSVWriter implements AutoCloseable {
    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1); // Предполагаем, что этот метод возвращает TreeSet<Ticket>
    private final BufferedWriter writer;

    /**
     * Конструктор CSVWriter открывает файл для записи и проверяет, пуста ли коллекция билетов.
     * Если коллекция пуста, создается пустой файл, если нет — данные из коллекции записываются в файл.
     *
     * @param filename путь к файлу, в который будут записываться данные
     * @throws IOException если возникла ошибка при открытии файла или записи в него
     */
    public CSVWriter(String filename) throws IOException {
        // Открываем файл для записи, если файл существует, он будет перезаписан (очищен)
        this.writer = new BufferedWriter(new FileWriter(filename, false)); // false - очищаем файл

        // Проверка, пуста ли коллекция, если пуста - просто создаём пустой файл
        if (tickets.isEmpty()) {
            System.out.println("Коллекция пуста, создаём пустой файл.");
        } else {
            writeTickets(tickets); // Передаём TreeSet напрямую
        }
    }

    /**
     * Записывает один билет в файл.
     *
     * @param ticket объект Ticket, который нужно записать
     * @throws IOException если возникла ошибка при записи в файл
     */
    public void writeTicket(Ticket ticket) throws IOException {
        writer.write(ticket.toCSV());
        writer.newLine();
        writer.flush();
    }

    /**
     * Записывает всю коллекцию билетов в файл.
     *
     * @param tickets коллекция билетов, которую нужно записать в файл
     * @throws IOException если возникла ошибка при записи в файл
     */
    public void writeTickets(TreeSet<Ticket> tickets) throws IOException {
        for (Ticket ticket : tickets) {
            writeTicket(ticket);
        }
    }

    /**
     * Закрывает BufferedWriter и освобождает ресурсы.
     *
     * @throws IOException если возникла ошибка при закрытии ресурса
     */
    @Override
    public void close() throws IOException {
        writer.close();
    }
}
