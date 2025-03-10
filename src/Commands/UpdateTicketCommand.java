package Commands;

import Collections.Ticket;
import Collections.Event;
import Collections.EventType;
import Collections.TicketType;
import Collections.Coordinates;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Команда для обновления существующего билета по его ID.
 */
public class UpdateTicketCommand implements Command {
    private static final TreeSet<Ticket> tickets = new TreeSet<>();

    @Override
    public void execute() {
        System.out.println("Ошибка: требуется ID и аргументы для обновления билета.");
    }

    /**
     * Обновляет билет по ID.
     *
     * @param args Массив аргументов (id, name, x, y, price, eventTime, ticketType, eventType).
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 8) {
            try {
                int id = Integer.parseInt(args[0].trim());
                String name = args[1].trim();
                Coordinates coordinates = new Coordinates((int) Double.parseDouble(args[2]), (int) Double.parseDouble(args[3]));
                float price = Float.parseFloat(args[4].trim());
                LocalDateTime eventTime = LocalDateTime.parse(args[5].trim());
                TicketType ticketType = TicketType.valueOf(args[6].trim().toUpperCase());
                EventType eventType = EventType.valueOf(args[7].trim().toUpperCase());
                Event event = new Event("Updated Event", eventTime, eventType);

                Iterator<Ticket> iterator = tickets.iterator();
                while (iterator.hasNext()) {
                    Ticket ticket = iterator.next();
                    if (ticket.getId() == id) {
                        iterator.remove(); // Удаляем старый билет
                        Ticket updatedTicket = Ticket.createTicket(name, coordinates, price, ticketType, event);
                        tickets.add(updatedTicket);
                        System.out.println("Билет с ID " + id + " обновлен: " + updatedTicket);
                        return;
                    }
                }
                System.out.println("Ошибка: билет с ID " + id + " не найден.");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: неверный формат ID, координат или цены.");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка: неверный формат времени. Ожидается формат yyyy-MM-ddTHH:mm:ss");
            }
        } else {
            System.out.println("Ошибка: неверный формат данных. Ожидается: id, name, x, y, price, eventTime, ticketType, eventType.");
        }
    }

    @Override
    public void PrintInfo() {
        System.out.println("Команда для обновления билета по ID.");
    }
}
