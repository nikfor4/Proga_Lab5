package Commands;

import Collections.Ticket;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Команда для удаления билетов с ID меньше или равным указанному.
 * Удаляет билеты из коллекции, если их ID меньше или равен заданному значению.
 */
public class RemoveLowerTicketCommad implements Command {

    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);

    /**
     * Метод выполняется при вызове команды без аргументов.
     * Выводит информацию о том, что команда требует аргумент с ID билета.
     */
    @Override
    public void execute() {
        System.out.println("Команда remove_lower вводится с аргументом id");
    }

    /**
     * Метод выполняется при вызове команды с аргументами.
     * Ищет и удаляет билеты с ID, меньшими или равными указанному.
     * @param args массив строковых аргументов, где первый аргумент — это ID билета.
     */
    @Override
    public void execute(String[] args) {
        Iterator<Ticket> iterator = tickets.iterator();
        int id = Integer.parseInt(args[0]);  // Преобразуем аргумент в целое число (ID билета)
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();

            if (ticket.getId() <= id) {
                iterator.remove();  // Удаляем билеты с ID меньше или равным заданному
                System.out.println("Билет с id " + ticket.getId() + " удален");
            }
        }
        System.out.println(
                "██████╗░███████╗███╗░░░███╗██╗░░░░░░█████╗░░██╗░░░░░░░██╗\n" +
                        "██╔══██╗██╔════╝████╗░████║██║░░░░░██╔══██╗░██║░░██╗░░██║\n" +
                        "██████╔╝█████╗░░██╔████╔██║██║░░░░░██║░░██║░╚██╗████╗██╔╝\n" +
                        "██╔══██╗██╔══╝░░██║╚██╔╝██║██║░░░░░██║░░██║░░████╔═████║░\n" +
                        "██║░░██║███████╗██║░╚═╝░██║███████╗╚█████╔╝░░╚██╔╝░╚██╔╝░\n" +
                        "╚═╝░░╚═╝╚══════╝╚═╝░░░░░╚═╝╚══════╝░╚════╝░░░░╚═╝░░░╚═╝░░");
    }

    /**
     * Метод выводит информацию о команде.
     * Информирует пользователя о том, что команда удаляет билеты с ID, меньшими или равными заданному.
     */
    @Override
    public void PrintInfo() {
        System.out.println("remove_lower id - команда удаляет билеты с id, равными или меньшими введенного id");
    }
}
