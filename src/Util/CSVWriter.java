package Util;

import Collections.Ticket;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

public class CSVWriter implements AutoCloseable {
    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1); // Предполагаем, что этот метод возвращает TreeSet<Ticket>
    private final BufferedWriter writer;

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

    public void writeTicket(Ticket ticket) throws IOException {
        writer.write(ticket.toCSV());
        writer.newLine();
        writer.flush();
    }

    public void writeTickets(TreeSet<Ticket> tickets) throws IOException {
        for (Ticket ticket : tickets) {
            writeTicket(ticket);
        }
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
