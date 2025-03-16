package Commands;
import Console.Client;
import Console.Session;
import Util.CSVWriter;
import com.sun.tools.javac.Main;

import java.io.IOException;

public class SaveTicketCommand implements Command {
    private static final String fileputh = Client.getFilename();
    @Override
    public void execute() {
        try {
            new CSVWriter(fileputh);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("██╗░░░██╗██████╗░██╗░░░░░░█████╗░░█████╗░██████╗░███████╗██████╗░\n" +
                "██║░░░██║██╔══██╗██║░░░░░██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔══██╗\n" +
                "██║░░░██║██████╔╝██║░░░░░██║░░██║███████║██║░░██║█████╗░░██║░░██║\n" +
                "██║░░░██║██╔═══╝░██║░░░░░██║░░██║██╔══██║██║░░██║██╔══╝░░██║░░██║\n" +
                "╚██████╔╝██║░░░░░███████╗╚█████╔╝██║░░██║██████╔╝███████╗██████╔╝\n" +
                "░╚═════╝░╚═╝░░░░░╚══════╝░╚════╝░╚═╝░░╚═╝╚═════╝░╚══════╝╚═════╝░");
    }

    @Override
    public void execute(String[] args) {

    }

    @Override
    public void PrintInfo() {

    }
}
