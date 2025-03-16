package Commands;

import Collections.Ticket;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Класс {@code ClearTicketCommand} реализует команду очистки коллекции билетов.
 * Команда удаляет все элементы из коллекции {@code tickets}.
 */
public class ClearTicketCommand implements Command {
    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);

    /**
     * Выполняет команду с аргументами. В данной реализации команда {@code clear} не принимает аргументы.
     *
     * @param args аргументы команды (не используются).
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Команда clear вызывается без аргументов");
    }

    /**
     * Выполняет команду очистки коллекции билетов.
     * Удаляет все элементы из {@code tickets} и выводит соответствующие сообщения.
     */
    @Override
    public void execute() {
        Iterator<Ticket> iterator = tickets.iterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            iterator.remove();
            System.out.println("Билет с id " + ticket.getId() + " удален");
        }

        System.out.println(
                "░█████╗░██╗░░░░░███████╗░█████╗░██████╗░\n" +
                        "██╔══██╗██║░░░░░██╔════╝██╔══██╗██╔══██╗\n" +
                        "██║░░╚═╝██║░░░░░█████╗░░███████║██████╔╝\n" +
                        "██║░░██╗██║░░░░░██╔══╝░░██╔══██║██╔══██╗\n" +
                        "╚█████╔╝███████╗███████╗██║░░██║██║░░██║\n" +
                        "░╚════╝░╚══════╝╚══════╝╚═╝░░╚═╝╚═╝░░╚═╝"
        );
    }

    /**
     * Выводит информацию о команде {@code clear}.
     * Сообщает, что команда очищает коллекцию билетов в буфере.
     */
    @Override
    public void PrintInfo() {
        System.out.println("clear - команда очищает коллекцию в буфере");
    }
}
