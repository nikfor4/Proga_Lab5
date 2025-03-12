package Commands;

import Collections.Ticket;

import java.util.Iterator;
import java.util.TreeSet;

public class RemoveLowerTicketCommad implements Command {
    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);

    @Override
    public void execute() {
        System.out.println("Введите id билета");
    }

    @Override
    public void execute(String[] args) {
        Iterator<Ticket> iterator = tickets.iterator();
        int id = Integer.parseInt(args[0]);
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();

            if (ticket.getId() <= id) {
                iterator.remove();
                System.out.println("Билет с id " + id + " удален");
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

    @Override
    public void PrintInfo() {
        System.out.println("remove_lower - команда удаляющая билеты с id равным и больше введеного id");
    }
}
