package Commands;

import Collections.Ticket;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Команда для удаления билета по его ID.
 * Удаляет билет из коллекции, если его ID соответствует указанному значению.
 */
public class RemovebyidTicketCommand implements Command {

    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);

    /**
     * Метод выполняется при вызове команды без аргументов.
     * Выводит информацию о том, что команда требует аргумент с ID билета.
     */
    @Override
    public void execute() {
        System.out.println("Команда remove_by_id вводится с аргументом id");
    }

    /**
     * Метод выполняется при вызове команды с аргументами.
     * Ищет и удаляет билет с указанным ID. Если билет с таким ID найден, он удаляется.
     * @param args массив строковых аргументов, где первый аргумент — это ID билета.
     */
    @Override
    public void execute(String[] args) {
        Iterator<Ticket> iterator = tickets.iterator();
        int id = Integer.parseInt(args[0]);  // Преобразуем аргумент в целое число (ID билета)
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();

            if (ticket.getId() == id) {
                iterator.remove();  // Удаляем билет с указанным ID
                System.out.println("Билет с ID " + id + " удален\n" +
                        "██████╗░███████╗███╗░░░███╗░█████╗░██╗░░░██╗███████╗██████╗░\n" +
                        "██╔══██╗██╔════╝████╗░████║██╔══██╗██║░░░██║██╔════╝██╔══██╗\n" +
                        "██████╔╝█████╗░░██╔████╔██║██║░░██║╚██╗░██╔╝█████╗░░██║░░██║\n" +
                        "██╔══██╗██╔══╝░░██║╚██╔╝██║██║░░██║░╚████╔╝░██╔══╝░░██║░░██║\n" +
                        "██║░░██║███████╗██║░╚═╝░██║╚█████╔╝░░╚██╔╝░░███████╗██████╔╝\n" +
                        "╚═╝░░╚═╝╚══════╝╚═╝░░░░░╚═╝░╚════╝░░░░╚═╝░░░╚══════╝╚═════╝░");
                return;
            }
        }
        System.out.println("Ошибка: билет с ID " + id + " не найден.");
    }

    /**
     * Метод выводит информацию о команде.
     * Информирует пользователя о том, что команда удаляет билет с указанным ID.
     */
    @Override
    public void PrintInfo() {
        System.out.println("remove_by_id id - Команда удаляет билет по ID.");
    }
}
