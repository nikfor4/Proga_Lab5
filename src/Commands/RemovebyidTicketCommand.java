package Commands;

import Collections.Ticket;

import java.util.Iterator;
import java.util.TreeSet;

public class RemovebyidTicketCommand implements Command {
    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);
    @Override
    public void execute() {
        System.out.println("Введите id билета, который надо удалить как аргумент");
    }

    @Override
    public void execute(String[] args) {
        Iterator<Ticket> iterator = tickets.iterator();
        int id = Integer.parseInt(args[0]);
        while (iterator.hasNext()){
            Ticket ticket = iterator.next();

            if (ticket.getId() == id) {
                iterator.remove();
                System.out.println("Билет с id " + id + " удален\n████████████████████████████████████████████\n" +
                        "█▄─▄▄▀█▄─▄▄─█▄─▀█▀─▄█─▄▄─█▄─█─▄█▄─▄▄─█▄─▄▄▀█\n" +
                        "██─▄─▄██─▄█▀██─█▄█─██─██─██▄▀▄███─▄█▀██─██─█\n" +
                        "▀▄▄▀▄▄▀▄▄▄▄▄▀▄▄▄▀▄▄▄▀▄▄▄▄▀▀▀▄▀▀▀▄▄▄▄▄▀▄▄▄▄▀▀");
                return;
            }


        }
        System.out.println("Ошибка: билет с ID " + id + " не найден.");

    }

    @Override
    public void PrintInfo() {
        System.out.println("remove_by_id - Команда удаление билета по ID.");

    }
}
