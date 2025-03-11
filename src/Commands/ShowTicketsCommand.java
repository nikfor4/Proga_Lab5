package Commands;

import Collections.Ticket;

import java.util.TreeSet;

/**
 * Команда для отображения всех билетов в коллекции.
 */
public class ShowTicketsCommand implements Command {
    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);

    @Override
    public void execute() {
        if (tickets.isEmpty()) {
            System.out.println("Коллекция билетов пуста.");
        } else {
            System.out.println("Список всех билетов:");
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        }
    }

    /**
     * Дополнительный метод `execute(String[] args)`, но в данной команде он не требуется.
     *
     * @param args Аргументы (не используются).
     */
    @Override
    public void execute(String[] args) {
        execute(); // Просто вызываем основную версию метода
    }

    @Override
    public void PrintInfo() {
        System.out.println("show - Команда для вывода всех билетов в коллекции.");
    }
}
