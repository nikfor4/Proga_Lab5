package Commands;

import java.util.Map;

public class HelpCommand implements Command {
    private String functionName = "help";
    private String information = "какая то информация helpa";
    private Map<String, Command> data = CommandProcessor.commands;
    @Override
    public void PrintInfo() {
        System.out.println(functionName + " - " +  information);
    }

    @Override
    public void execute() {
        for (Map.Entry<String, Command> entry : data.entrySet()) {
            data.get(entry.getKey()).PrintInfo();
        }
    }

    @Override
    public void execute(String[] args) {

    }
}
