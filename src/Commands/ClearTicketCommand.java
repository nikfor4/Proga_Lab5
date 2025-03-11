package Commands;

import Collections.Ticket;

import java.util.Iterator;
import java.util.TreeSet;

public class ClearTicketCommand implements Command {
    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);
    @Override
    public void execute(String[] args) {

    }
    @Override
    public void execute() {
        Iterator<Ticket> iterator = tickets.iterator();
        while (iterator.hasNext()){
            Ticket ticket = iterator.next();
                iterator.remove();
                System.out.println("Билет с id " + ticket.getId() + " удален");


        }
        System.out.println("███████████████████████████████\n" +
                "█─▄▄▄─█▄─▄███▄─▄▄─██▀▄─██▄─▄▄▀█\n" +
                "█─███▀██─██▀██─▄█▀██─▀─███─▄─▄█\n" +
                "▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▀▄▄▀▄▄▀▄▄▀");

    }
    @Override
    public void PrintInfo() {

    }
}



