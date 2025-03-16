package Commands;

import Collections.Ticket;
import Collections.TicketType;
import Validation.InputValidate;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class СountLessThanTypeCommand implements Command {
    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);
    private Integer count = 0;
    @Override
    public void execute() {

    }

    @Override
    public void execute(String[] args) {
        Iterator<Ticket> iterator = tickets.iterator();
        TicketType type = InputValidate.getValidTicketTypeInput(args[0]);
        if (type != null) {
            try {

                while (iterator.hasNext()) {
                    Ticket ticket = iterator.next();

                    if (ticket.getType().name().length() < type.name().length()) {
                        count++;
                    }


                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
            System.out.println("Билетов со значением поля меньше " + type + " " + count + " штук.");
        }

    }

    @Override
    public void PrintInfo() {
        System.out.println();
    }
}
