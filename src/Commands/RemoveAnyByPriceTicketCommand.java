package Commands;

import Collections.Ticket;
import Validation.InputValidate;

import java.util.Iterator;
import java.util.TreeSet;

public class RemoveAnyByPriceTicketCommand implements Command{
    private static final TreeSet<Ticket> tickets = Ticket.getTicket(1);
    @Override
    public void execute() {

    }

    @Override
    public void execute(String[] args) {
        Iterator<Ticket> iterator = tickets.iterator();
        try{
        float price = InputValidate.getFloatInput(args[0]);
        if (price <= 0) {
            return;
        }
            while (iterator.hasNext()){
                Ticket ticket = iterator.next();

                if (ticket.getPrice() == price) {
                    iterator.remove();
                    System.out.println("Билет с ценной  " + price  + " удален\n██████╗░███████╗███╗░░░███╗░█████╗░██╗░░░██╗███████╗██████╗░\n" +
                            "██╔══██╗██╔════╝████╗░████║██╔══██╗██║░░░██║██╔════╝██╔══██╗\n" +
                            "██████╔╝█████╗░░██╔████╔██║██║░░██║╚██╗░██╔╝█████╗░░██║░░██║\n" +
                            "██╔══██╗██╔══╝░░██║╚██╔╝██║██║░░██║░╚████╔╝░██╔══╝░░██║░░██║\n" +
                            "██║░░██║███████╗██║░╚═╝░██║╚█████╔╝░░╚██╔╝░░███████╗██████╔╝\n" +
                            "╚═╝░░╚═╝╚══════╝╚═╝░░░░░╚═╝░╚════╝░░░░╚═╝░░░╚══════╝╚═════╝░");
                    return;
                }


            }
            System.out.println("Ошибка: билет с такой ценной не найден.");
        }
        catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }


    }

    @Override
    public void PrintInfo() {
        System.out.println("remove_any_by_price price - Команда удаление билета с введенной стоимостью и наименьшим id .");

    }
}
