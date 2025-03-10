package Commands;

import Collections.Ticket;
import Collections.Event;
import Collections.EventType;
import Collections.TicketType;
import Collections.Coordinates;

import java.time.LocalDateTime;
import java.util.TreeSet;

/**
 * Команда для добавления нового билета.
 */
public class AddTicketCommand implements Command {
    private static final TreeSet<Ticket> tickets = new TreeSet<>();

    @Override
    public void execute() {
        System.out.println("Ошибка: требуется аргумент для создания билета.");
    }

    /**
     * Создает и добавляет новый билет в коллекцию.
     *
     * @param args Массив аргументов (name, x, y, price, eventTime, ticketType, eventType).
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 7) {
            try {
                String name = args[0].trim();
                Coordinates coordinates = new Coordinates((int) Double.parseDouble(args[1]), (int) Double.parseDouble(args[2]));
                float price = Float.parseFloat(args[3].trim());
                LocalDateTime eventTime = LocalDateTime.parse(args[4].trim());
                TicketType ticketType = TicketType.valueOf(args[5].trim().toUpperCase());
                EventType eventType = EventType.valueOf(args[6].trim().toUpperCase());
                Event event = new Event("Default Event", eventTime, eventType);

                Ticket ticket = Ticket.createTicket(name, coordinates, price, ticketType, event);
                if (tickets.add(ticket)) {
                    System.out.println("Добавлен билет: " + ticket);
                } else {
                    System.out.println("Билет уже существует: " + ticket);
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        } else {
            System.out.println("Ошибка: неверный формат данных.");
        }
    }

    @Override
    public void PrintInfo() {
        System.out.println("Команда для добавления билета.");
    }
}
