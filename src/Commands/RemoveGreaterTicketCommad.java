package Commands;

import Collections.Ticket;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Команда для удаления билетов с ID больше или равным указанному.
 * Удаляет билеты из коллекции, если их ID больше или равен заданному значению.
 */
public class RemoveGreaterTicketCommad implements Command {

    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);

    /**
     * Метод выполняется при вызове команды без аргументов.
     * Выводит информацию о том, что команда требует аргумент с ID билета.
     */
    @Override
    public void execute() {
        System.out.println("Команда remove_greater вводится с аргументом id");
    }

    /**
     * Метод выполняется при вызове команды с аргументами.
     * Ищет и удаляет билеты с ID, большими или равными указанному.
     * @param args массив строковых аргументов, где первый аргумент — это ID билета.
     */
    @Override
    public void execute(String[] args) {
        Iterator<Ticket> iterator = tickets.iterator();
        int id = Integer.parseInt(args[0]);  // Преобразуем аргумент в целое число (ID билета)
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();

            if (ticket.getId() >= id) {
                iterator.remove();  // Удаляем билеты с ID больше или равным заданному
                System.out.println("Билет с id " + id + " удален");
            }
        }
        System.out.println(
                "██████╗░███████╗███╗░░░███╗░██████╗░██████╗░███████╗\n" +
                        "██╔══██╗██╔════╝████╗░████║██╔════╝░██╔══██╗██╔════╝\n" +
                        "██████╔╝█████╗░░██╔████╔██║██║░░██╗░██████╔╝█████╗░░\n" +
                        "██╔══██╗██╔══╝░░██║╚██╔╝██║██║░░╚██╗██╔══██╗██╔══╝░░\n" +
                        "██║░░██║███████╗██║░╚═╝░██║╚██████╔╝██║░░██║███████╗\n" +
                        "╚═╝░░╚═╝╚══════╝╚═╝░░░░░╚═╝░╚═════╝░╚═╝░░╚═╝╚══════╝");
    }

    /**
     * Метод выводит информацию о команде.
     * Информирует пользователя о том, что команда удаляет билеты с ID больше или равным заданному.
     */
    @Override
    public void PrintInfo() {
        System.out.println("remove_greater id - команда удаляет билеты с id, равным или большим введенного id");
    }
}
